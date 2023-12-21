package api.gamestore.controller;

import api.gamestore.model.User;
import lombok.extern.slf4j.Slf4j;
import api.gamestore.service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api-gamestore/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> find() {

        if (userService.find().isEmpty()) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userService.find());
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete() {

        try {
            userService.delete();
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Erro: Falha ao deletar Users.", e);
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

            log.error("Os campos JSON não são analisáveis. ", e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @PutMapping(path = "/{cpf}", produces = { "application/json" })
    public ResponseEntity<User> update(@PathVariable("cpf") String cpf, @RequestBody JSONObject user) {

        try {

            if (userService.isJSONValid((user.toString()))) {

                User userToUpdate = userService.findByCpf(cpf);
                if (userToUpdate == null) {

                    log.error("User não encontrado.");
                    return ResponseEntity.notFound().build();
                } else {

                    User userUpdate = userService.update(userToUpdate, user);
                    return ResponseEntity.ok(userUpdate);
                }
            } else {

                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {

            log.error("Os campos JSON não são analisáveis. ", e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }
}
