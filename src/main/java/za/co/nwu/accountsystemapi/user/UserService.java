package za.co.nwu.accountsystemapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.nwu.accountsystemapi.exception.ApiRequestException;
import za.co.nwu.accountsystemapi.milesaccount.MilesAccount;
import za.co.nwu.accountsystemapi.milesaccount.MilesAccountRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MilesAccountRepository milesAccountRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("User with id " + id + " was not found"));
    }

    @Transactional
    public User createUser(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());

        if(userByEmail.isPresent()) {
            throw new ApiRequestException("User already exists");
        }

        User newUser =  userRepository.save(user);

        MilesAccount milesAccount = new MilesAccount(newUser.getId(), 0);

        MilesAccount newMilesAccount = milesAccountRepository.save(milesAccount);

        newUser.setUserMilesAccount(newMilesAccount);

        updateUser(newUser.getId(), newUser);

        return newUser;
    }

    @Transactional
    public User updateUser(int userId, User user) {
        User currentUser = userRepository.findById(userId)
                .orElseThrow(()-> new ApiRequestException("User not found"));

        if(!Objects.equals(user.getEmail(), currentUser.getEmail())) {
            currentUser.setEmail(user.getEmail());
        }

        if(!Objects.equals(user.getFirstName(), currentUser.getFirstName())) {
            currentUser.setFirstName(user.getFirstName());
        }

        if(!Objects.equals(user.getLastName(), currentUser.getLastName())) {
            currentUser.setLastName(user.getLastName());
        }

        if(!Objects.equals(user.getUserMilesAccount(), currentUser.getUserMilesAccount())) {
            currentUser.setUserMilesAccount(user.getUserMilesAccount());
        }

        return userRepository.save(currentUser);
    }

    @Transactional
    public void removeUserWithId(int id) {
        Optional<User> foundUser = userRepository.findById(id);

        if(foundUser.isEmpty()) {
            throw new ApiRequestException("User not found");
        }

        userRepository.deleteById(id);
    }
}
