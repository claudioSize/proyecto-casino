package fav.com.casino.Repository;

import fav.com.casino.Entitys.JuegoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<JuegoEntity, Integer> {
    JuegoEntity findByJuegoName(String name);
}
