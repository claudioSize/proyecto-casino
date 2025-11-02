package fav.com.casino.Configs;

import fav.com.casino.DTOS.DTOClaim;
import fav.com.casino.Repository.UserRepository;
import fav.com.casino.Security.Jwt;
import fav.com.casino.Security.UserDtailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Component
public class Filter extends OncePerRequestFilter {
    @Autowired
    Jwt jwt;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private RequestContextFilter requestContextFilter;
    @Autowired
    private UserDtailsService userDtailsService;

    //Creo un filter sirve para ingresar al contexto de spring security la info del usuario para asi ocuparla en otros metodos
    //sin tener que estar validandolo en cada controller
    //Toma el token que viene en el header antes de llegar al controller y revisa con los service de jwt
    //y si estan correctos los sube al contexto y los ocupo en mi controller
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException, IOException {
        String header = request.getHeader("Authorization");


        if (header != null && header.startsWith("Bearer ")) {
            String token = header.replace("Bearer ", "");
            if (!jwt.isTokenValid(token)) throw new ResponseStatusException(HttpStatus.CONFLICT,"Token invalido");
            DTOClaim claims = jwt.getClaims(token);
            UserDetails userDetails = userDtailsService.loadUserByUsername(claims.getUserName());
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(),null,userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
    }
}