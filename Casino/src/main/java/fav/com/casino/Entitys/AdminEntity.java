package fav.com.casino.Entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.Date;
@Entity
@Table(name = "administrador")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminEntity  {
    @Id
    private int id;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String pais;
    private String accountStatus;
    private Date createdAt;


}
