package cz.edu.upce.fei.datamanager.data.repository;

import cz.edu.upce.fei.datamanager.data.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);
}