package fav.com.casino.Repository;

import fav.com.casino.Entitys.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClienteEntity ,Integer> {
    ClienteEntity findClienteEntityByCorreoElectronico(String correo);
}
