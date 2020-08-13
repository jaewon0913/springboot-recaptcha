package com.example.recaptcha.service;

import com.example.recaptcha.model.Member;

public interface MemberService {

	Member login(String loginId, String loginPw);
}
