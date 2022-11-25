package webProject.SIProject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import webProject.SIProject.dto.PalletItem_DTO;
import webProject.SIProject.dto.Reservation_DTO;
import webProject.SIProject.dto.SelectPalletItem_DTO;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class FunctionController {
    PalletItem_DTO palletItem1 = new PalletItem_DTO("표준형","11A형","1,100mm X 1,100mm X 150mm","19.5kg","HDPE","적재하중 1Ton/4방 차입형 편면 사용형","http://ajnetworks.co.kr/assets/uploads/product/product_11A_1.png","A11");
    PalletItem_DTO palletItem2 = new PalletItem_DTO("표준형","12A형","1,200mm X 1,000mm X 150mm","19.5kg","HDPE","적재하중 1Ton/4방 차입형 편면 사용형","http://ajnetworks.co.kr/assets/uploads/product/product_12A_1.png","A12");
    PalletItem_DTO palletItem3 = new PalletItem_DTO("표준형","11B형","1,100mm X 1,100mm X 150mm","26.2kg","HDPE","적재하중 1Ton/4방 차입형 편면 사용형","http://ajnetworks.co.kr/assets/uploads/product/product_11B_1.png","B11");

    ArrayList<PalletItem_DTO> palletItem_list = new ArrayList<PalletItem_DTO>(){{
        add(palletItem1);
        add(palletItem2);
        add(palletItem3);
    }};
    @GetMapping("/publish_req")
    public String publish_req(Model model) throws UnsupportedEncodingException {


        model.addAttribute("palletItem_list",palletItem_list);
        return "PalletRequest";
    }


    @PostMapping("/request_detail")
    public String request_detail(@ModelAttribute SelectPalletItem_DTO select_pallet,Model model){
        log.info(select_pallet.getSelect_pallet());
        ArrayList<PalletItem_DTO> palletItem_send_list = new ArrayList<PalletItem_DTO>();
        if(select_pallet.getSelect_pallet().equals("-")){
            model.addAttribute("palletItem_list",palletItem_send_list);
            return "PalletRequestSend";
        }

        List<String> input_list = new ArrayList<>(List.of(select_pallet.getSelect_pallet().split(",")));
        for(int i = 0; i < palletItem_list.size(); i++){
            PalletItem_DTO input_palletItem = palletItem_list.get(i);
            if(input_list.contains(palletItem_list.get(i).getFront_type())){
                palletItem_send_list.add(input_palletItem);
            }
        }

        model.addAttribute("palletItem_list",palletItem_send_list);
        return "PalletRequestSend";
    }

    @PostMapping("/request_send")
    public String request_send(@ModelAttribute Reservation_DTO reservation_list, Model model){
        log.info(reservation_list.toString());
        return "main";
    }

    @GetMapping("/publish_resp")
    public String publish_resp(){
        return "PalletRespond";
    }


    @GetMapping("/QnA")
    public String QnA(){
        return "QnA";
    }

    @GetMapping("/FS")
    public String FS(){
        return "FSapi";
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
