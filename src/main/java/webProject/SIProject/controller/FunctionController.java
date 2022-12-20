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
import webProject.SIProject.dto.PalletItem_DTO;
import webProject.SIProject.dto.Reservation_DTO;
import webProject.SIProject.dto.SelectPalletItem_DTO;
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

        reservationServices.save(user.getEmail(), orderList, reservation_list);
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


    @GetMapping("/prediction_user")
    public String prediction_user(){
        return "redirect:http://127.0.0.1:5000/user";
    }
    @GetMapping("/prediction_admin")
    public String prediction_admin(){
        return "redirect:http://127.0.0.1:5000/admin";
    }

}
