package at.myrecipes.cookBookApi.repository;

import at.myrecipes.cookBookApi.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
