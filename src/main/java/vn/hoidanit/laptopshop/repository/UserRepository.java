package vn.hoidanit.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hoidanit.laptopshop.domain.User;
import java.util.List;

//crud: create, read, update, delete
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @SuppressWarnings({ "null", "unchecked" })
    User save(@SuppressWarnings("null") User eric);

    List<User> findOneByEmail(String email);

    @SuppressWarnings("null")
    List<User> findAll();
}
