package com.woo.cookiesession.memberForm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static final Map<Long, Member> members = new HashMap<>();
    private static long sequence = 0L;

    public void save(Member member){
        member.setId(++sequence);
        log.info("save member = {}",member);
        members.put(member.getId(), member);
    }

    public Optional<Member> findByLongId(String loginId) {
        return findAll().stream().filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    public List<Member> findAll(){
        return new ArrayList<>(members.values());
    }

}
