package dev.young.jpa;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(
            @Autowired UserService userService,
            @Autowired Gson gson
    ){
        this.userService = userService;
        logger.info(gson.toString());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDto dto){
        this.userService.createUser(dto);
    }

    @GetMapping("{id}")
    public UserDto readUser(@PathVariable("id") String id){
        return this.userService.readUser(id);
    }

    @GetMapping("")
    public List<UserDto> readUserAll(){
        return this.userService.readUserAll();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUser(@PathVariable("id") String id, @RequestBody UserDto dto){
        this.userService.updateUser(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUser(@PathVariable("id") String id){
        this.userService.deleteUser(id);
    }

}
