package api.gamestore.controller;

import api.gamestore.model.Game;
import api.gamestore.model.User;
import api.gamestore.service.GameService;
import api.gamestore.service.UserService;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api-gamestore/users")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> find() {

        if (userService.find().isEmpty()) {

            return ResponseEntity.notFound().build();
        }
        logger.info(userService.find());
        return ResponseEntity.ok(userService.find());
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete() {

        try {
            userService.delete();
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<User> create(@RequestBody JSONObject user) {

        try {

            if (userService.isJSONValid(user.toString())) {

                User userCreated = userService.create(user);
                var uri = ServletUriComponentsBuilder.fromCurrentRequest().path(userCreated.getCpf()).build().toUri();

                userService.add(userCreated);
                return ResponseEntity.created(uri).body(null);
            } else {

                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {

            logger.error("Os campos JSON não são analisáveis. " + e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @PutMapping(path = "/{id}", produces = { "application/json" })
    public ResponseEntity<User> update(@PathVariable("cpf") String cpf, @RequestBody JSONObject user) {

        try {

            if (userService.isJSONValid((user.toString()))) {

                User userToUpdate = userService.findByCpf(cpf);
                if (userToUpdate == null) {

                    logger.error("User não encontrado.");
                    return ResponseEntity.notFound().build();
                } else {

                    User userUpdate = userService.update(userToUpdate, user);
                    return ResponseEntity.ok(userUpdate);
                }
            } else {

                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {

            logger.error("Os campos JSON não são analisáveis. " + e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }
}
