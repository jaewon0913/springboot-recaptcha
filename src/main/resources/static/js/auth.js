$("#loginBtn").on('click', function(){
   if($("#loginBtn").hasClass('disabled-btn')){
       alert("recaptcha 인증 후 진행이 가능합니다.");
   } else {
       var memberId = $("#loginId").val();
       var memberPw = $("#loginPw").val();
       var recaptcha = $("#g-recaptcha-response").val();
       
       var params = {
    		   "member.memberId" : memberId,
    		   "member.memberPw" : memberPw,
    		   "recaptcha" : recaptcha
       }

       $.ajax({
           type: "get",
           contentType: "application/json",
           url: "/auth/login",
           data: params,
           dataType: "json",
           success: function(data){
        	   
        	   alert(data.status);
        	   
               if(data.status == 0){
            	   var memberParams = data.member;
            	   
                   location.href = "/afterLogin?"+$.param(memberParams);
               } else if(data.status == 300){
            	   resetCallback();
            	   alert("reCAPTCHA 인증 실패. 재인증 후 진행해주세요.");
               } else if(data.status == 200){
            	   alert("reCAPTCHA 인증 오류. 서버 관리자에게 문의하세요.");
            	   location.href = "/";
               } else {
            	   resetCallback();
                   alert("회원 정보가 없습니다. 재 인증 후 진행해주세요.");
               }
           },
           error: function(err){
        	   alert(err.status);
               alert("에러" + err.toString());
           }
       });

   }
});