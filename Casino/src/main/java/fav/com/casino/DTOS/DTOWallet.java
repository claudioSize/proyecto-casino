package fav.com.casino.DTOS;

import fav.com.casino.Entitys.TransaccionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DTOWallet {
    private double saldo;
    private double saldoBloqueado;
    private String moneda;
    private List<TransaccionEntity> transaccionEntityList;
}
