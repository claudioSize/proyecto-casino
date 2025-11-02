package fav.com.casino.Controllers;

import fav.com.casino.DTOS.DTOClientSend;
import fav.com.casino.DTOS.DTONewDeposit;
import fav.com.casino.DTOS.DTOWallet;
import fav.com.casino.Security.Jwt;
import fav.com.casino.Service.TransactionService;
import fav.com.casino.Service.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class WalletController {
    @Autowired
    Jwt jwt;
    @Autowired
    WalletService walletService;
    @Autowired
    TransactionService depositService;

    @PostMapping("/wallet/")
    ResponseEntity<DTOWallet> viewWallet(Authentication authentication){
        return ResponseEntity.ok(walletService.getWallet(authentication));
    }
    @PostMapping("/deposit/")
    ResponseEntity<ResponseEntity<HttpStatus>> newDeposit(Authentication authentication,@Valid @RequestBody DTONewDeposit dto){
        return ResponseEntity.ok(depositService.doDeposit(authentication,dto));
    }
    @PostMapping("/withdra/")
    ResponseEntity<ResponseEntity<HttpStatus>> newWhitDra(Authentication authentication,@Valid @RequestBody DTONewDeposit dto){
        return ResponseEntity.ok(depositService.doWithdra(authentication,dto));
    }
    @PostMapping("/sendMoney/")
    ResponseEntity<ResponseEntity<HttpStatus>> senMoney(Authentication authentication,@Valid @RequestBody DTOClientSend dto){
        return ResponseEntity.ok(depositService.sentMoneyToClient(authentication,dto));
    }
}
