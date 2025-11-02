package fav.com.casino.Entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaccion")
public class TransaccionEntity {
    @Id

    private int id;
    @Column(name = "tipo")
    private String tipoTrasaccion;
    private double monto;
    private double balanceAnterior;
    private double balancePosterior;
    private String descripcion;
    private Date createdAt;
    private long billeteraId;
}
