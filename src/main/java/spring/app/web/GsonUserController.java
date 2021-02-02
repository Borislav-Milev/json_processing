package spring.app.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.app.domain.User;
import spring.app.service.contract.UserService;

@RestController
@RequestMapping("/api/gson/users")
@Slf4j
public class GsonUserController {

    private final UserService userService;
    private final Gson gson =  new GsonBuilder()
            //.excludeFieldsWithoutExposeAnnotation()
            .setPrettyPrinting()
            .create();

    @Autowired
    public GsonUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUsers(){
        return gson.toJson(userService.getAllUsers());
    }

    @GetMapping(path = "/{id}", produces = "application/gson")
    public String getUsers(@PathVariable("id") Long id){
        return gson.toJson(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody String body){
        log.info("Body received: {}", body);
        User user = gson.fromJson(body, User.class);
        log.info("User: {}", user);
        User created = userService.addUser(user);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment("{id}").buildAndExpand(created.getId().toString()).toUri()
        ).body(gson.toJson(created));
    }
}
