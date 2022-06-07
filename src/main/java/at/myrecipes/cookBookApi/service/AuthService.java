package at.myrecipes.cookBookApi.service;

import at.myrecipes.cookBookApi.entity.User;
import at.myrecipes.cookBookApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getCurrentUser() throws Exception{
        return userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new Exception());
    }
}
