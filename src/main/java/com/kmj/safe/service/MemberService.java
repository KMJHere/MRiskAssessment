package com.kmj.safe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kmj.safe.model.Member;
import com.kmj.safe.repository.MemberMP;

@Service
@Transactional
public class MemberService {
    @Autowired
	private MemberMP memberMP;

    public List<Member> getMemberList() {
        return memberMP.getMemberList();
    }

    public Member getMember(String USER_ID) {
        return memberMP.getMember(USER_ID);
    }

    public int createMember(Member member) {
        return memberMP.createMember(member);
    }

    public int updateMember(Member member) {
        return memberMP.updateMember(member);
    }

    public int deleteMember(int id) { 
        return memberMP.deleteMember(id);
    }
}
