package apap.tutorial.gopud.repository;

import apap.tutorial.gopud.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDB extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);

    UserModel findById(String id);
}
