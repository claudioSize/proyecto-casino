package fav.com.casino.Controllers;

import fav.com.casino.DTOS.DTOCliente;
import fav.com.casino.DTOS.DTOUserLogin;
import fav.com.casino.Security.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class LoginControllers {
    @Autowired
    UserService userService;

    @PostMapping("/create/client/")
    ResponseEntity<HttpStatus> createUser(@Valid @RequestBody DTOCliente dto){
        userService.createUser(dto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
    @PostMapping("/login/client/")
    ResponseEntity<String> loginUser(@Valid @RequestBody DTOUserLogin dto){

        return ResponseEntity.ok(userService.loginUser(dto));
    }
    @PostMapping("/update/password/")
    ResponseEntity<HttpStatus> updatePass(@Valid @RequestBody DTOUserLogin dto){
        userService.newPassword(dto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
