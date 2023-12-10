package me.mujyan.springbootdeveloper.service;

import me.mujyan.springbootdeveloper.dto.Member;
import me.mujyan.springbootdeveloper.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final MemberRepository memberRepository;

    @Autowired
    public UserService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Boolean checkID(String id) {
        return memberRepository.existsById(id);
    }

    public Member savePerson(Member member) {
        return memberRepository.save(member);
    }

}