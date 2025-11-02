package fav.com.casino.Service;

import fav.com.casino.DTOS.DTOWallet;
import fav.com.casino.Entitys.ClienteEntity;
import fav.com.casino.Entitys.TransaccionEntity;
import fav.com.casino.Entitys.WalletEntity;
import fav.com.casino.Repository.ClientRepository;
import fav.com.casino.Repository.TransaccionRepository;
import fav.com.casino.Repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    TransaccionRepository transaccionRepository;

    //Verr billetera y sus transacciones
    public DTOWallet getWallet(Authentication authentication){
        ClienteEntity clientRepository1 = clientRepository.findClienteEntityByCorreoElectronico(authentication.getName());
        WalletEntity wallet = walletRepository.findByClienteId(clientRepository1.getId());

        List<TransaccionEntity> list = transaccionRepository.findByBilleteraId(wallet.getId());
        return DTOWallet.builder().saldo(wallet.getSaldo())
                .saldoBloqueado(wallet.getSaldoBloqueado()).moneda(wallet.getMoneda()).transaccionEntityList(list).build();
    }

}
