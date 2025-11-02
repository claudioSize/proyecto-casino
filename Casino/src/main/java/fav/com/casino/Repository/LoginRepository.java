package fav.com.casino.Repository;

import fav.com.casino.Entitys.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginEntity, Integer> {
}
