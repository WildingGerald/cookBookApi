package at.myrecipes.cookBookApi.controler;

import at.myrecipes.cookBookApi.dto.SocialDTO;
import at.myrecipes.cookBookApi.entity.User;
import at.myrecipes.cookBookApi.repository.UserRepository;
import at.myrecipes.cookBookApi.service.AuthService;
import at.myrecipes.cookBookApi.util.SocialEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum socials{


}
@RestController
@RequestMapping("/user")
public class UserController {
    public UserRepository userRepository;

    AuthService authService;

    @PostMapping("/setSocial")
    public ResponseEntity<String> setSocial(@Valid @RequestBody SocialDTO socialDTO){
        try {
            SocialEnum socialMedia = SocialEnum.fromString(socialDTO.socialMedia());
            authService.getCurrentUser().setSocials(socialMedia.toString(), socialDTO.profileLink());
            return ResponseEntity.ok("Social added");
        }catch (IllegalArgumentException e){
            return ResponseEntity.internalServerError();
        } catch (Exception e) {
            return ResponseEntity.internalServerError();
        }
    }

    @GetMapping("/test")
    public List<User> index() {
        return userRepository.findAll();
    }

}
