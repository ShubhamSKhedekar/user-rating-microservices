package main.java.com.microservice.user_service.controllers;

@RestController
@RequestMapping("/user-service")
public class UserServiceController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public User fetchUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user/email/{email}")
    public User fetchUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);   
    }

    @PostMapping("/user/save")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);  
    }

    @PutMapping("/user/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUserById(@PathVariable String id) {
        userService.deleteUserById(id); 
    }
}
