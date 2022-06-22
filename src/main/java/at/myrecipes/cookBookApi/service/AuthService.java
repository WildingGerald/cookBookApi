package at.myrecipes.cookBookApi.service;

import at.myrecipes.cookBookApi.dto.RegistrationDTO;
import at.myrecipes.cookBookApi.entity.User;
import at.myrecipes.cookBookApi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public class AuthService {
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ThisUserDetailService userDetailService;

    @Transactional
    public UserDetails register(RegistrationDTO registrationDTO) {
        try {
            User user = new User();
            user.setUsername(registrationDTO.username());
            user.setPassword(bCryptPasswordEncoder.encode(registrationDTO.password()));
            user.setEmail(registrationDTO.email());

            userRepository.save(user);
            return userDetailService.loadUserByUsername(registrationDTO.username());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or email exists already");
        }
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() throws ResponseStatusException {
        return userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
    }
}
