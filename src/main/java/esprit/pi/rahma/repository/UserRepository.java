package esprit.pi.rahma.repository;

import esprit.pi.rahma.entities.User;
import esprit.pi.rahma.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
  //  User findById(long id);

    //List<User> findByStatus(int status);
    @Query("SELECT COUNT(u) FROM User u WHERE u.role = :role")
    int countUsersByRole(@Param("role") Role role);





}
