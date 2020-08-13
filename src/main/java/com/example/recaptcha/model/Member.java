package com.example.recaptcha.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
	
	private String memberId;
	private String memberPw;
	private String name;
	private String gender;
	private int age;
	private String job;
	
	public Member() {}
	
	@java.beans.ConstructorProperties({"memberId", "memberPw", "name", "gender", "age", "job"})
	public Member(String memberId, String memberPw, String name, String gender, int age, String job) {
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.job = job;
	}
}
