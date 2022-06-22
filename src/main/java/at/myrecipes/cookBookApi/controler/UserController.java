package at.myrecipes.cookBookApi.controler;

import at.myrecipes.cookBookApi.dto.SocialDTO;
import at.myrecipes.cookBookApi.dto.UserDTO;
import at.myrecipes.cookBookApi.entity.User;
import at.myrecipes.cookBookApi.repository.UserRepository;
import at.myrecipes.cookBookApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/getUser")
    public ResponseEntity<UserDTO> getUser(@RequestBody String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
        return ResponseEntity.ok(new UserDTO(user.getUsername(), user.getEmail(), user.getFirstname(), user.getLastname(), user.getDescription()));
    }

    @GetMapping("/getSelf")
    public ResponseEntity<UserDTO> getSelf() {
        User user = userService.getSelf();
        return ResponseEntity.ok(new UserDTO(user.getUsername(), user.getEmail(), user.getFirstname(), user.getLastname(), user.getDescription()));
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UserDTO userDTO){
        User user = userService.updateUser(userDTO);
        return ResponseEntity.ok(new UserDTO(user.getUsername(), user.getEmail(), user.getFirstname(), user.getLastname(), user.getDescription()));
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(){
        userService.deleteUser();
        return ResponseEntity.ok("User deleted");
    }

    @PostMapping("/setSocial")
    public ResponseEntity<String> setSocial(@Valid @RequestBody SocialDTO socialDTO) {
        userService.setSocial(socialDTO);
        return ResponseEntity.ok("Social added");
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
