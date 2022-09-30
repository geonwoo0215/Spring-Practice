package springsecurity.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springsecurity.security.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);
}
