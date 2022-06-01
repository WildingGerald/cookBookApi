package at.myrecipes.cookBookApi;

import at.myrecipes.cookBookApi.entity.Profile;
import at.myrecipes.cookBookApi.repository.ProfileRepository;
import at.myrecipes.cookBookApi.service.ProfileUserDetailService;
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
    private ProfileRepository profileRepository;

    @PostConstruct
    public void initTestUser() {
        List<Profile> profiles = Stream.of(
                new Profile("hanes", "wurst")
        ).collect(Collectors.toList());
        profileRepository.saveAll(profiles);
    }

    public static void main(String[] args) {
        SpringApplication.run(CookBookApiApplication.class, args);
    }

}
