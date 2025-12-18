package com.stylezone.service;

import com.stylezone.model.User;
import com.stylezone.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user) {

        // 🔐 Encode password hanya jika masih plaintext
        if (user.getPassword() != null && !user.getPassword().startsWith("$2")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return repo.save(user);
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    public User findById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public List<User> search(String name, String role, String email) {

        if (email != null) {
            return List.of(
                    repo.findByEmail(email)
                            .orElseThrow(() -> new RuntimeException("User not found"))
            );
        }

        if (name != null && role != null) {
            return repo.findAll().stream()
                    .filter(u -> u.getName() != null &&
                            u.getName().toLowerCase().contains(name.toLowerCase()))
                    .filter(u -> u.getRole() != null &&
                            u.getRole().equalsIgnoreCase(role))
                    .toList();
        }

        if (name != null) {
            return repo.findByNameContainingIgnoreCase(name);
        }

        if (role != null) {
            return repo.findByRole(role);
        }

        return repo.findAll();
    }
}
