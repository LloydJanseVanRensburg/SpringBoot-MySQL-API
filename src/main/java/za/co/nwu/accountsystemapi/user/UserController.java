package za.co.nwu.accountsystemapi.user;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/users/")
public class UserController {

    // Controller Logger
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/")
    @ApiOperation(
            value="Get ALL Users",
            notes="No data required",
            response= User.class,
            responseContainer = "List"
    )
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    @ApiOperation(
            value="Get User By Id",
            notes="User ID param required",
            response= User.class
    )
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping("/")
    @ApiOperation(
            value="Register New User",
            notes="Requires User Model Data except Id",
            response= User.class
    )
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    @ApiOperation(
            value="Update a User by Id",
            notes="Requires User Model Data except Id in request body and user id as path variable",
            response= User.class
    )
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
            value="Delete a User By Id",
            notes="Requires user id as path variable"
    )
    public void deleteUser(@PathVariable int id) {
        userService.removeUserWithId(id);
    }
}
