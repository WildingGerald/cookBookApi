package at.myrecipes.cookBookApi.repository;

import at.myrecipes.cookBookApi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
