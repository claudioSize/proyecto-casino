package fav.com.casino.Repository;

import fav.com.casino.Entitys.ApuestaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<ApuestaEntity, Integer> {
}
