package com.example.recaptcha.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthForm {
	
	private static final long serialVersionUID = 1L;

    private String recaptcha;

    private Member member;

    private String status;
}
