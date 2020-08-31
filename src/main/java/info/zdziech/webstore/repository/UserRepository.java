package info.zdziech.webstore.repository;


import info.zdziech.webstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

      User findByUserMail(String userMail);

      User findByUsernameAndPassword(String username, String password);

}
