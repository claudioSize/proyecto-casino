package fav.com.casino.DTOS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DTOApuesta {
    private double saldo;
    private String resultado;
    private String juegoNombre;
}
