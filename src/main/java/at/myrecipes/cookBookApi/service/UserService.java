package at.myrecipes.cookBookApi.service;

import at.myrecipes.cookBookApi.dto.SocialDTO;
import at.myrecipes.cookBookApi.dto.UserDTO;
import at.myrecipes.cookBookApi.entity.User;
import at.myrecipes.cookBookApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Service
public class UserService {
    @Autowired
    AuthService authService;

    @Autowired
    UserRepository userRepository;

    public User getCurrentUser() throws ResponseStatusException {
        return authService.getCurrentUser();
    }

    public User getSelf() {
        return getCurrentUser();
    }

    @Transactional
    public User updateUser(UserDTO userDTO) {
        User user = getCurrentUser();
        userRepository.findByUsername(userDTO.username()).ifPresent(newUsername -> {
            if (newUsername != user) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New username already exists");
            }
        });

        user.setUsername(userDTO.username());
        user.setFirstname(userDTO.firstname());
        user.setLastname(userDTO.lastname());
        user.setEmail(userDTO.email());
        user.setDescription(userDTO.description());

        userRepository.save(user);
        return user;
    }

    @Transactional
    public void deleteUser() {
        User user = authService.getCurrentUser();
        userRepository.delete(user);
    }

    @Transactional
    public void setSocial(@Valid @RequestBody SocialDTO socialDTO) {
        authService.getCurrentUser().setSocials(socialDTO.socialMedia(), socialDTO.profileLink());
    }
}
