package phattrienungdungvoi2ee.bai4_qlsp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phattrienungdungvoi2ee.bai4_qlsp.Model.User;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}