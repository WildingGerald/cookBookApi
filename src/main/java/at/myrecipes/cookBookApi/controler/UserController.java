package at.myrecipes.cookBookApi.controler;

import at.myrecipes.cookBookApi.dto.SocialDTO;
import at.myrecipes.cookBookApi.repository.UserRepository;
import at.myrecipes.cookBookApi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    UserRepository userRepository;

    @Autowired
    AuthService authService;

    @PostMapping("/setSocial")
    public ResponseEntity<String> setSocial(@Valid @RequestBody SocialDTO socialDTO) {
        try {
            authService.getCurrentUser().setSocials(socialDTO.socialMedia(), socialDTO.profileLink());
            return ResponseEntity.ok("Social added");
        } catch (Exception e) {
            return (ResponseEntity<String>) ResponseEntity.notFound();
        }
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
