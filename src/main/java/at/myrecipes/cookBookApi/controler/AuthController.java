package at.myrecipes.cookBookApi.controler;

import at.myrecipes.cookBookApi.dto.AuthenticationResponse;
import at.myrecipes.cookBookApi.dto.LoginDTO;
import at.myrecipes.cookBookApi.dto.RegistrationDTO;
import at.myrecipes.cookBookApi.service.AuthService;
import at.myrecipes.cookBookApi.service.ThisUserDetailService;
import at.myrecipes.cookBookApi.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    ThisUserDetailService userDetailService;
    @Autowired
    JwtUtil jwtUtil;


    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegistrationDTO registrationDTO) {
        UserDetails userDetails = authService.register(registrationDTO);
        return ResponseEntity.ok(new AuthenticationResponse(jwtUtil.generateToken(userDetails)));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginDTO loginDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.userName(), loginDTO.password()));
        UserDetails userDetails = userDetailService.loadUserByUsername(loginDTO.userName());
        return ResponseEntity.ok(new AuthenticationResponse(jwtUtil.generateToken(userDetails)));
    }
}
