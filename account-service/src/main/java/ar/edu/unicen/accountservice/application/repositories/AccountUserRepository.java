package ar.edu.unicen.accountservice.application.repositories;

import ar.edu.unicen.accountservice.domain.entities.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountUserRepository extends JpaRepository<AccountUser, Long> {
}
