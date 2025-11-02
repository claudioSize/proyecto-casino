package fav.com.casino.Entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "billetera")
public class WalletEntity {
    @Id
    private int id;
    private double saldo;
    private double saldoBloqueado;
    private String moneda;
    @Column(name = "cliente_id")
    private int clienteId;

}
