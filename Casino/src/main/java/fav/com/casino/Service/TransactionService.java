package fav.com.casino.Service;

import fav.com.casino.DTOS.DTOClientSend;
import fav.com.casino.DTOS.DTONewDeposit;
import fav.com.casino.Entitys.ClienteEntity;
import fav.com.casino.Entitys.TransaccionEntity;
import fav.com.casino.Entitys.WalletEntity;
import fav.com.casino.Repository.ClientRepository;
import fav.com.casino.Repository.TransaccionRepository;
import fav.com.casino.Repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransactionService {
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    TransaccionRepository transaccionRepository;
    //Se hace deposito y ademas se actualiza el saldo de la billetera
    public ResponseEntity<HttpStatus> doDeposit(Authentication authentication, DTONewDeposit dto){
        WalletEntity wallet =
                walletRepository.findByClienteId(clientRepository
                        .findClienteEntityByCorreoElectronico(authentication.getName()).getId());
        double newAmount = dto.getMonto()+wallet.getSaldo();
        transaccionRepository.save(TransaccionEntity.builder().billeteraId(wallet.getId()).monto(dto.getMonto())
                .tipoTrasaccion("Deposito").balanceAnterior(wallet.getSaldo())
                .balancePosterior(newAmount).descripcion(dto.getDescripcion()).createdAt(new Date()).build());
        wallet.setSaldo(newAmount);
        walletRepository.save(wallet);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
    //Retirar dinero de la billetera
    public ResponseEntity<HttpStatus> doWithdra(Authentication authentication, DTONewDeposit dto){
        WalletEntity wallet =
                walletRepository.findByClienteId(clientRepository
                        .findClienteEntityByCorreoElectronico(authentication.getName()).getId());

        if (wallet.getSaldo() <= dto.getMonto()) return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        double newAmount = wallet.getSaldo()-dto.getMonto();

        transaccionRepository.save(TransaccionEntity.builder().billeteraId(wallet.getId()).monto(dto.getMonto())
                .tipoTrasaccion("Retiro").balanceAnterior(wallet.getSaldo())
                .balancePosterior(newAmount).descripcion(dto.getDescripcion()).createdAt(new Date()).build());
        wallet.setSaldo(newAmount);
        walletRepository.save(wallet);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
    public ResponseEntity<HttpStatus> sentMoneyToClient(Authentication authentication, DTOClientSend dto){
        transaction(authentication,dto);
        saveFinalClient(dto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    public ResponseEntity<HttpStatus> transaction(Authentication authentication, DTOClientSend dto){
        ClienteEntity clienteEntity = clientRepository.findClienteEntityByCorreoElectronico(authentication.getName());
        WalletEntity wallet =
                walletRepository.findByClienteId(clienteEntity.getId());

        if (wallet.getSaldo() <= dto.getAmount()) return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);

        TransaccionEntity transaccionEntity = new TransaccionEntity();

        transaccionEntity.setMonto(dto.getAmount());
        transaccionEntity.setBilleteraId(wallet.getId());
        transaccionEntity.setBalanceAnterior(wallet.getSaldo());
        transaccionEntity.setBalancePosterior(wallet.getSaldo()-dto.getAmount());
        transaccionEntity.setDescripcion("Transferencia");
        transaccionEntity.setCreatedAt(new Date());
        transaccionEntity.setTipoTrasaccion("Transferencia");

        wallet.setSaldo(wallet.getSaldo()-dto.getAmount());
        walletRepository.save(wallet);
        transaccionRepository.save(transaccionEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    public void saveFinalClient(DTOClientSend dto){
        ClienteEntity clienteEntity = clientRepository.findClienteEntityByCorreoElectronico(dto.getEmail());
        WalletEntity wallet =
                walletRepository.findByClienteId(clienteEntity.getId());

        TransaccionEntity transaccionEntity = new TransaccionEntity();

        transaccionEntity.setMonto(dto.getAmount());
        transaccionEntity.setBilleteraId(wallet.getId());
        transaccionEntity.setBalanceAnterior(wallet.getSaldo());
        transaccionEntity.setBalancePosterior(dto.getAmount()+wallet.getSaldo());
        transaccionEntity.setDescripcion("Transferencia");
        transaccionEntity.setCreatedAt(new Date());
        transaccionEntity.setTipoTrasaccion("Transferencia");
        wallet.setSaldo(dto.getAmount()+wallet.getSaldo());
        walletRepository.save(wallet);
        transaccionRepository.save(transaccionEntity);
    }
}
