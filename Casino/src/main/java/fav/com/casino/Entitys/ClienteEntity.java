package fav.com.casino.Entitys;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "cliente")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntity {
    @Id
    private int id;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String pais;
    private String accountStatus;
    private Date createdAt;
    @Column(name = "administrador_id")
    private int adminId;




}
