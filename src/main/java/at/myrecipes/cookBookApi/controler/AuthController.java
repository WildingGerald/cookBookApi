package at.myrecipes.cookBookApi.controler;

import at.myrecipes.cookBookApi.dto.AuthenticationResponse;
import at.myrecipes.cookBookApi.dto.LoginDTO;
import at.myrecipes.cookBookApi.service.ProfileUserDetailService;
import at.myrecipes.cookBookApi.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    AuthenticationManager authenticationManager;
    @Autowired
    ProfileUserDetailService userDetailService;
    @Autowired
    JwtUtil jwtUtil;

   @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginDTO loginDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.userName(), loginDTO.password()));
        UserDetails userDetails = userDetailService.loadUserByUsername(loginDTO.userName());
        return ResponseEntity.ok(new AuthenticationResponse(jwtUtil.generateToken(userDetails)));
    }
}
