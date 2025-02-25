package Grofila.com.backend.service;

import Grofila.com.backend.model.Customer;
import Grofila.com.backend.model.Shopkeeper;
import Grofila.com.backend.model.User;
import Grofila.com.backend.repository.CustomerRepository;
import Grofila.com.backend.repository.ShopkeeperRepository;
import Grofila.com.backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
    public User loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user.get();
        }
        throw new RuntimeException("Invalid credentials");
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // **GET USER BY ID**
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // **UPDATE USER INFO**
    public User updateUser(Long id, User newUserDetails) {
        return userRepository.findById(id).map(user -> {
            user.setName(newUserDetails.getName());
            user.setEmail(newUserDetails.getEmail());

            // Keep old password if new password is null
            if (newUserDetails.getPassword() != null && !newUserDetails.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(newUserDetails.getPassword()));
            }
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // **DELETE USER**
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User does not exist");
        }
        userRepository.deleteById(id);
    }
}