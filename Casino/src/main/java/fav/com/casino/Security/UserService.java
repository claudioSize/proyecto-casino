package fav.com.casino.Security;

import fav.com.casino.DTOS.DTOCliente;
import fav.com.casino.DTOS.DTOUserLogin;
import fav.com.casino.Entitys.ClienteEntity;
import fav.com.casino.Entitys.LoginEntity;
import fav.com.casino.Entitys.UserEntity;
import fav.com.casino.Entitys.WalletEntity;
import fav.com.casino.Repository.ClientRepository;
import fav.com.casino.Repository.LoginRepository;
import fav.com.casino.Repository.UserRepository;
import fav.com.casino.Repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    Jwt jwt;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    LoginRepository loginRepository;
    //Se crea login y devuelve token desde la clase jwt
    public String loginUser(DTOUserLogin user){
        UserEntity userEntity = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("not found"));
        if (passwordEncoder.matches(user.getPassword(), userEntity.getPassword())){
            loginRepository.save(LoginEntity.builder().createdAt(new Date()).autenticacionId(userEntity.getId()).build());
            return jwt.buildToken(userEntity);
        }else {
            throw new RuntimeException("error");
        }
    }
    //Creo user para autenticacion de spring security y cliente ademas de billetera
    public void createUser(DTOCliente dto){
        try {
            userRepository.save(UserEntity.builder().username(dto.getCorreoElectronico())
                    .rol("client").accountStatus("ok").password(passwordEncoder.encode(dto.getPassword())).build());

            ClienteEntity clienteEntity = ClienteEntity.builder().nombre(dto.getNombre()).apellido(dto.getApellido())
                    .correoElectronico(dto.getCorreoElectronico()).pais(dto.getPais())
                    .accountStatus("ACTIVO").adminId(1).createdAt(new Date()).build();
            clientRepository.save(clienteEntity);
            ClienteEntity clienteEntity1 = clientRepository.findClienteEntityByCorreoElectronico(dto.getCorreoElectronico());
            walletRepository.save(WalletEntity.builder().saldo(0).saldoBloqueado(0).moneda("CLP")
                    .clienteId(clienteEntity1.getId()).build());
        }catch (Exception e){
            throw new RuntimeException(String.valueOf(HttpStatus.BAD_REQUEST));
        }
    }
    //Cambia contraseña de la cuenta de cliente y recupera
    public void newPassword(DTOUserLogin dto){
        UserEntity userEntity = userRepository.findByUsername(dto.getUsername()).orElseThrow(() -> new UsernameNotFoundException("not found"));

        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(userEntity);

    }
}



