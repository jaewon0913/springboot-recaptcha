package com.example.recaptcha.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.recaptcha.model.Member;

@Service(value="memberService")
public class MemberServiceImpl implements MemberService {
	
	private List<Member> members = new ArrayList<>();
	{
		members.add(new Member("testId@test.com", "test1234", "Jack", "male", 32, "Programmer"));
		members.add(new Member("adminId@admin.com", "admin1234", "Scarlett", "female", 28, "Movie Actor"));
		members.add(new Member("userId@user.com", "user1234", "Hanson", "male", 22, "College Student"));
	}

	@Override
	public Member login(String loginId, String loginPw) {
		
		Member selectMember = members.stream()
				.filter(m -> m.getMemberId().equals(loginId) && m.getMemberPw().contentEquals(loginPw))
				.findFirst().orElse(null);
		
		return selectMember;
	}

}
