package spring.rest.bookmark.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.rest.bookmark.models.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Optional<Account> findByUsername(String username);

}
