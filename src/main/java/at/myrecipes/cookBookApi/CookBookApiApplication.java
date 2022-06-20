package at.myrecipes.cookBookApi;

import at.myrecipes.cookBookApi.entity.User;
import at.myrecipes.cookBookApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class CookBookApiApplication {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initTestUser() {
        List<User> users = Stream.of(
                new User("hanes", "$2a$10$jcl7kengZL8NOeGI/lp09O/oyHc/ncIdLZg.Qnl9c6JMnNRvSe.Ma")
        ).collect(Collectors.toList());
        userRepository.saveAll(users);
    }

    public static void main(String[] args) {
        SpringApplication.run(CookBookApiApplication.class, args);
    }

}
