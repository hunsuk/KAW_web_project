package webProject.SIProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import webProject.SIProject.domain.OrderList;
import webProject.SIProject.domain.PalletItem;
import webProject.SIProject.domain.Reservation;
import webProject.SIProject.domain.User;
import webProject.SIProject.dto.*;
import webProject.SIProject.repository.PalletItemRepository;
import webProject.SIProject.repository.UserRepository;
import webProject.SIProject.service.OrderServices;
import webProject.SIProject.service.PalletItemService;
import webProject.SIProject.service.ReservationServices;
import webProject.SIProject.service.UserService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@Slf4j
public class FunctionController {
    private final PalletItemService palletItemService;
    private final OrderServices orderServices;
    private final ReservationServices reservationServices;
    List<PalletItem> palletItemList = new ArrayList<PalletItem>();

    @GetMapping("/publish_req")
    public String publish_req(@AuthenticationPrincipal User user,Model model) throws UnsupportedEncodingException {
        palletItemList = palletItemService.getPallet();
        model.addAttribute("userAuth",user.getAuth());
        model.addAttribute("palletItem_list",palletItemList);
        return "PalletRequest_V2";
    }

    @PostMapping("/request_detail")
    public String request_detail(@AuthenticationPrincipal User user,@ModelAttribute SelectPalletItem_DTO select_pallet,Model model){
        ArrayList<PalletItem> palletItem_send_list = new ArrayList<PalletItem>();

        if(select_pallet.getSelect_pallet().equals("-")){
            model.addAttribute("palletItem_list",palletItem_send_list);
            return "PalletRequestSend";
        }

        List<String> input_list = new ArrayList<>(List.of(select_pallet.getSelect_pallet().split(",")));
        for(int i = 0; i < palletItemList.size(); i++){
            PalletItem input_palletItem = palletItemList.get(i);
            if(input_list.contains(palletItemList.get(i).getFront_type())){
                palletItem_send_list.add(input_palletItem);
            }
        }

        model.addAttribute("palletItem_list",palletItem_send_list);
        return "PalletRequestSend_V3";
    }

    @PostMapping("/request_send")
    public String request_send(@AuthenticationPrincipal User user, @ModelAttribute Reservation_DTO reservation_list, Model model){
        log.info(reservation_list.toString());
        log.info(user.getEmail());
        OrderList orderList = orderServices.read(user.getEmail(),"ing");
        reservationServices.save(user.getEmail(), orderList, reservation_list,"처리중");
        model.addAttribute("userAuth",user.getAuth());

        return "redirect:/";
    }

    @GetMapping("/publish_resp")
    public String publish_resp(){
        return "PalletRespond";
    }

    @GetMapping("/prdicRequest")
    public String prdicRequest(){
        return "PrdicRequest";
    }

    @PostMapping("/prdicRequest_send")
    public String predicRequest_send(){
        return "PrdicRequestReturn";
    }

    @GetMapping("/setBuket")
    public void setBuket(@AuthenticationPrincipal User user,@RequestParam(value = "items") String items){
        orderServices.save(user.getEmail(),items);
        log.info("장바구니 정보 받기");
    }

    @GetMapping("/myPage")
    public String myPage(@AuthenticationPrincipal User user ,Model model){
        model.addAttribute("user_info",user);
        return "Myinfo";
    }


    @GetMapping("/check_my_request")
    public String check_my_request(@AuthenticationPrincipal User user, Model model){


//        OrderList orderList = orderServices.read(user.getEmail(),"sent");
        List<OrderList> orderList = orderServices.read(user.getEmail());
        List<Reservation_add_User_DTO> reservationAddUser = new ArrayList<>();

        log.info(String.valueOf(orderList.size()));

        for(int i = 0; i < orderList.size(); i++){
            List<Reservation> reservations= reservationServices.read(orderList.get(i).getId());
            for(int j = 0; j < reservations.size(); j++){
                reservationAddUser.add(Reservation_add_User_DTO.builder()
                        .id(orderList.get(i).getReservations().get(j).getId())
                        .count(orderList.get(i).getReservations().get(j).getCount())
                        .rent_day(orderList.get(i).getReservations().get(j).getRent_day())
                        .standardPallet(orderList.get(i).getReservations().get(j).getStandardPallet())
                        .status(orderList.get(i).getReservations().get(j).getStatus())
                        .request(orderList.get(i).getRequest())
                        .palletItem(palletItemService.loadPalletItemByStandard(orderList.get(i).getReservations().get(j).getStandardPallet()))
                        .build());
            }
        }
        model.addAttribute("reservations",reservationAddUser);
        return "Check_my_request";
    }

    @PostMapping("/request_del")
    public String request_del(@AuthenticationPrincipal User user , Model model, @ModelAttribute SelectDelReservation_DTO selectDelReservationDto){
        String[] id = selectDelReservationDto.getSelected().toString().split(",");

        for(int i=0; i< id.length; i++){
            reservationServices.delete(Long.parseLong(id[i]));
        }

        List<OrderList> orderList = orderServices.read(user.getEmail());
        log.info(String.valueOf(orderList.size()));
        List<PalletItem> palletItems = new ArrayList<PalletItem>();
        List<Reservation> allReservations = new ArrayList<Reservation>(100);
        for(int i = 0; i < orderList.size(); i++){
            List<Reservation> reservations= reservationServices.read(orderList.get(i).getId());
            for(int j = 0; j < reservations.size(); j++){
                allReservations.add(reservations.get(j));
            }
        }
        model.addAttribute("reservations",allReservations);

        if(user.getAuth().equals("ROLE_USER")){
            return "redirect:/check_my_request";
        }else{
            return "redirect:/control_request";
        }
    }
    @PostMapping("/request_process")
    public String request_process(@AuthenticationPrincipal User user, Model model ,@ModelAttribute SelectDelReservation_DTO selectDelReservationDto){
        String[] id = selectDelReservationDto.getSelected().toString().split(",");

        for(int i=0; i< id.length; i++){
            Optional<Reservation> reservation = reservationServices.readbyID(Long.parseLong(id[i]));
            reservationServices.delete(Long.parseLong(id[i]));
            reservation.get().setStatus("배송준비중");
            reservationServices.save2(reservation);
        }
        return "redirect:/control_request";
    }

        @GetMapping("/dashboard_admin")
    public String dashboard_admin(){
        return "admin_dashboard";
    }

    @GetMapping("/dashboard_admin_atration")
    public String dashboard_admin_atration(){
        return "admin_dashboard_atration";
    }

    @GetMapping("/control_request")
    public String control_request(@AuthenticationPrincipal User user, Model model){
        List<User> users = new ArrayList<>();
        List<OrderList> orderList = orderServices.allRead();
        List<Reservation_add_User_DTO> reservationAddUser = new ArrayList<>();
        log.info(String.valueOf(orderList.size()));
        List<PalletItem> palletItems = new ArrayList<PalletItem>();
        List<Reservation> allReservations = new ArrayList<Reservation>();
        for(int i = 0; i < orderList.size(); i++){
            List<Reservation> reservations= reservationServices.read(orderList.get(i).getId());
            for(int j = 0; j < orderList.get(i).getReservations().size(); j++){

                reservationAddUser.add(Reservation_add_User_DTO.builder()
                                .id(orderList.get(i).getReservations().get(j).getId())
                                        .count(orderList.get(i).getReservations().get(j).getCount())
                                                .rent_day(orderList.get(i).getReservations().get(j).getRent_day())
                                                        .standardPallet(orderList.get(i).getReservations().get(j).getStandardPallet())
                                                                .status(orderList.get(i).getReservations().get(j).getStatus())
                                                                        .request(orderList.get(i).getRequest())
                                                                                .user(orderList.get(i).getUser())
                                                                                    .palletItem(palletItemService.loadPalletItemByStandard(orderList.get(i).getReservations().get(j).getStandardPallet()))

                                                                                    .build());

            }
        }

        model.addAttribute("reservations",reservationAddUser);
        return "Control_request";
    }

    @GetMapping("/request_popup")
    public String request_popup(){
        return "RequestPopup";
    }
    @GetMapping("/request_check_popup")
    public String request_check_popup(){
        return "Request_Check_Popup";
    }

    @GetMapping("/request_tracking_popup")
    public String request_tracking_popup(){
        return "Request_Tracking_Popup";
    }

    @GetMapping("/request_map_popup")
    public String request_map_popup(){
        return "Request_map_Popup";
    }

    @GetMapping("/prediction_user")
    public String prediction_user(){
        return "redirect:http://127.0.0.1:5000/user";
    }
    @GetMapping("/prediction_admin")
    public String prediction_admin(){
        return "redirect:http://127.0.0.1:5000/admin";
    }

}
