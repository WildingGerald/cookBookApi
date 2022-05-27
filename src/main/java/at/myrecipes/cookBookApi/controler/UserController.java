package at.myrecipes.cookBookApi.controler;

import at.myrecipes.cookBookApi.entity.User;
import at.myrecipes.cookBookApi.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    public UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<User> index(){
        return userRepository.findAll();
    }
}
