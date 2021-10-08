package za.co.nwu.accountsystemapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.nwu.accountsystemapi.exception.ApiRequestException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        User userById = userRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("User with id " + id + " was not found"));

        return userById;
    }

    public User createUser(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());

        if(userByEmail.isPresent()) {
            throw new ApiRequestException("User already exists");
        }

        return userRepository.save(user);
    }

    public User updateUser(int userId, User user) {
        User currentUser = userRepository.findById(userId).orElseThrow(()-> new IllegalStateException("User not found"));

        if(!Objects.equals(user.getEmail(), currentUser.getEmail())) {
            currentUser.setEmail(user.getEmail());
        }

        if(!Objects.equals(user.getFirstName(), currentUser.getFirstName())) {
            currentUser.setFirstName(user.getFirstName());
        }

        if(!Objects.equals(user.getLastName(), currentUser.getLastName())) {
            currentUser.setLastName(user.getLastName());
        }

        return userRepository.save(currentUser);
    }

    public void removeUserWithId(int id) {
        userRepository.deleteById(id);
    }
}
