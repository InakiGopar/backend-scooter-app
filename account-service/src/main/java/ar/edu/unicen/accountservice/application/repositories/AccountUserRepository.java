package ar.edu.unicen.accountservice.application.repositories;

import ar.edu.unicen.accountservice.domain.entities.AccountUser;
import ar.edu.unicen.accountservice.domain.entities.AccountUserID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountUserRepository extends JpaRepository<AccountUser, AccountUserID> {

    @Query ("SELECT au FROM AccountUser au WHERE au.id.accountId = :accountID")
     List<AccountUser> findByIdAccountId(Long accountID);
}
