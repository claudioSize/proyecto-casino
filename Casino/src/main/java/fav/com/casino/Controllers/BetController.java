package fav.com.casino.Controllers;

import fav.com.casino.DTOS.DTOApuesta;
import fav.com.casino.DTOS.DTOCliente;
import fav.com.casino.Service.ApuestaService;
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
public class BetController {
    @Autowired
    ApuestaService apuestaService;

    @PostMapping("/bet/")
    ResponseEntity<HttpStatus> bet(Authentication authentication,@RequestBody DTOApuesta dto){
        apuestaService.doBet(authentication,dto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
