package fav.com.casino.Entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "apuesta")
public class ApuestaEntity {
    @Id
    private int id;
    @Column(name = "monto")
    private double saldo;
    private String resultado;
    @Column(name = "created_at")
    private Date createAt;
    private int juegoId;
    private int clienteId;

}

