package fav.com.casino.Repository;

import fav.com.casino.Entitys.TransaccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransaccionRepository extends JpaRepository<TransaccionEntity, Integer> {

    List<TransaccionEntity> findByBilleteraId(Integer integer);
}
