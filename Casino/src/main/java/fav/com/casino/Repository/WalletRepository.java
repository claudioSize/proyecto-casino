package fav.com.casino.Repository;


import fav.com.casino.Entitys.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<WalletEntity, Integer> {
    WalletEntity findByClienteId(Integer integer);
}
