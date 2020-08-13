package com.example.recaptcha.controller;

import com.example.recaptcha.config.RecaptchaConfig;
import com.example.recaptcha.model.AuthForm;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.example.recaptcha.model.Member;
import com.example.recaptcha.service.MemberServiceImpl;

@RestController
@RequestMapping("auth")
public class AuthController {
	
	@Autowired
	MemberServiceImpl memberService;

	@GetMapping("/login")
	public Object login(AuthForm form) throws Exception {
    	
		try {
			if(RecaptchaConfig.verify(form.getRecaptcha())) {
												
				Member loginMember = memberService.login(form.getMember().getMemberId(), form.getMember().getMemberPw());

				if(loginMember != null) {
					form.setMember(loginMember);
					form.setStatus("0");	//	로그인 성공
				} else {
					form.setStatus("1");	//	로그인 실패(회원정보 X)
				}
			} else {
				form.setStatus("300");		//	reCAPTCHA 인증 실패
			}
		} catch (IOException e) {
			form.setStatus("200");			//	reCAPTCHA 인증 오류
			return form;
		}

		return form;
	}
}
