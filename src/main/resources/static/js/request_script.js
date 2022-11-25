$(".button_addcart").click(function(){

	if(getCookie("cart").indexOf(",37,")<0){
		$(this).addClass("active");

		var ids = getCookie("cart") + ",37,";
		setCookie("cart" , ids, 1);


    var optionss = getCookie("options").split(",,");
    var option_text='';

    var ch = 0;
    for(i=0; i < optionss.length; i++){
      var seqs = optionss[i].split("::");
      if(seqs[0] == "37"){
        option_text += "37::"+$("#option_text").val()+",,";
      }else{
        ch = 1;
        option_text += optionss[i]+",,";
      }
    }
    if (ch == 1) {
      option_text += "37::"+$("#option_text").val()+",,";
    }
    setCookie("options" , option_text, 1);

		alert("제품을 담았습니다.");
    $(this).html("담기취소");

	}else{
		var ids = getCookie("cart").replace(",37,","");
		setCookie("cart" , ids, 1);

    var optionss = getCookie("options").split(",,");
    var option_text='';
    for(i=0; i < optionss.length; i++){
      var seqs = optionss[i].split("::");
      if(seqs[0] == "37"){

      }else{
        option_text += optionss[i]+",,";
      }
    }
    setCookie("options" , option_text, 1);
    $("#option_list").html("");
    $("#option_text").val("");
		alert("담기를 취소했습니다.");
    $(this).html("제품담기");
	}

  var c = ids.split(",,");
  $(".cart-list").css("display","inline-block");
  $(".cart-list .red").html(c.length);

	if(getCookie("cart") == ''){
		$(".cart-list").css("display","none");
		$(".cart-list .red").html(0);
	}
});

var cart = getCookie("cart");
if( cart!='' ){
	var c = cart.split(",,");
	if( cart.indexOf(",37,") >= 0 ){
		$(".button_addcart").addClass("active");
    $(".button_addcart").html("담기취소");
	}
	$(".cart-list").css("display","inline-block");
	$(".cart-list .red").html(c.length);
}

var optionss = getCookie("options").split(",,");
var option_text='';
for(i=0; i < optionss.length; i++){
  var seqs = optionss[i].split("::");
  if(seqs[0] == "37"){
    var txts = seqs[1].split("||");
    for(j=0; j < txts.length; j++){
      if(txts[j]!=''){
        $("#option_list").append('<li class="li_optionlist w-clearfix"><div class="text_optionlist">'+txts[j]+'</div><a href="javascript:;" class="button_optionclose w-button"></a></li>');
      }
    }
    $("#option_text").val(seqs[1]);
  }
}

$(".block_productdetailtop01left a").click(function(){
	$(".block_productdetailtop01left a img").removeClass("active");
	$(this).find("img").addClass("active");
	$(".block_productdetailimage img").attr("src",$(this).find("img").attr("src"));
});

$("#options").change(function(){
  var txt = $(this).val();
  if(txt != ''){
    if( $("#option_text").val().indexOf(txt+"||") >= 0 ){
      alert("이미 선택하신 옵션입니다.");
    }else{
      $("#option_text").val($("#option_text").val()+txt+"||");
      option_list = '<li class="li_optionlist w-clearfix"><div class="text_optionlist">'+txt+'</div><a href="javascript:;" class="button_optionclose w-button"></a></li>';
      $("#option_list").append(option_list);
    }
  }
});
$(document).on("click",".button_optionclose",function(){
  var txt = $(this).prev().html();
  if( $("#option_text").val().indexOf(txt+"||") >= 0 ){
    $("#option_text").val($("#option_text").val().replace(txt+"||",""));
    $(this).parent().remove();
  }
});