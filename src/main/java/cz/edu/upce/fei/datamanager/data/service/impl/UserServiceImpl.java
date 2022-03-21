package cz.edu.upce.fei.datamanager.data.service.impl;

import cz.edu.upce.fei.datamanager.data.entity.User;
import java.util.Optional;
import java.util.UUID;

import cz.edu.upce.fei.datamanager.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository repository;

    public Optional<User> getUser(long id) {
        return repository.findById(id);
    }

    public User updateUser(User entity) {
        return repository.save(entity);
    }

    public void deleteUser(long id) {
        repository.deleteById(id);
    }

    public Page<User> listUsers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public int countUsers() {
        return (int) repository.count();
    }
}
