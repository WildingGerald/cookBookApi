package at.myrecipes.cookBookApi.controler;

import at.myrecipes.cookBookApi.entity.Profile;
import at.myrecipes.cookBookApi.repository.ProfileRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class ProfileController {
    public ProfileRepository profileRepository;

    public ProfileController(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }
    public List<Profile> index(){
        return profileRepository.findAll();
    }
}
