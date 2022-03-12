package cz.edu.upce.fei.datamanager.data.service;

import cz.edu.upce.fei.datamanager.data.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Optional<User> getUser(UUID id);

    User updateUser(User entity);

    void deleteUser(UUID id);

    Page<User> listUsers(Pageable pageable);

    int countUsers();
}
