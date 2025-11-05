package ar.edu.unicen.walletservice.application.repositories;

import ar.edu.unicen.walletservice.domain.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findByUserIdAndAccountId(Long userId, Long accountId);
}
