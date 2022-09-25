package springsecurity.security.config.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import springsecurity.security.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findByUsername(String username);
}
