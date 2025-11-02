package fav.com.casino.Service;

import fav.com.casino.DTOS.DTOApuesta;
import fav.com.casino.Entitys.*;
import fav.com.casino.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ApuestaService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    TransaccionRepository transaccionRepository;
    @Autowired
    BetRepository betRepository;
    @Autowired
    GameRepository gameRepository;

    public ResponseEntity<HttpStatus> doBet(Authentication authentication, DTOApuesta dto){
        ClienteEntity clienteEntity = clientRepository.findClienteEntityByCorreoElectronico(authentication.getName());
        TransaccionEntity transaccionEntity = transaccion(dto,dto.getResultado(),clienteEntity.getId());
        JuegoEntity juegoEntity = gameRepository.findByJuegoName(dto.getJuegoNombre());
        TransaccionEntity transaccion2 = transaccionRepository.save(transaccionEntity);
        ApuestaEntity apuestaEntity = new ApuestaEntity();
        apuestaEntity.setJuegoId(juegoEntity.getId());
        apuestaEntity.setClienteId(clienteEntity.getId());
        apuestaEntity.setSaldo(dto.getSaldo());
        apuestaEntity.setResultado(dto.getResultado());
        apuestaEntity.setCreateAt(new Date());
        betRepository.save(apuestaEntity);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }
    public TransaccionEntity transaccion(DTOApuesta dtoApuesta, String resultado, int id){
        TransaccionEntity transaccionEntity = new TransaccionEntity();
        transaccionEntity.setTipoTrasaccion("APUESTA");
        transaccionEntity.setMonto(dtoApuesta.getSaldo());
        transaccionEntity.setCreatedAt(new Date());
        WalletEntity wallet = walletRepository.findByClienteId(id);
        if (resultado.equals("win")){
            transaccionEntity.setDescripcion("APUESTA GANADA");
            transaccionEntity.setBalanceAnterior(wallet.getSaldo());
            transaccionEntity.setBalancePosterior(wallet.getSaldo()+dtoApuesta.getSaldo());
            wallet.setSaldo(wallet.getSaldo()+dtoApuesta.getSaldo());
            walletRepository.save(wallet);
            transaccionEntity.setBilleteraId(wallet.getId());
            return transaccionEntity;
        }else {
            transaccionEntity.setDescripcion("APUESTA PERDIDA");
            transaccionEntity.setBalanceAnterior(walletRepository.findByClienteId(id).getSaldo());
            transaccionEntity.setBalancePosterior(walletRepository.findByClienteId(id).getSaldo()-dtoApuesta.getSaldo());
            wallet.setSaldo(wallet.getSaldo()-dtoApuesta.getSaldo());
            walletRepository.save(wallet);
            transaccionEntity.setBilleteraId(wallet.getId());
            return transaccionEntity;
        }

    }


}
