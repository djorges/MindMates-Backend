package MindMates.NoCountry.auth;

import MindMates.NoCountry.user.UserAuthService;
import MindMates.NoCountry.user.UserEntity;
import MindMates.NoCountry.user.UserRepository;
import MindMates.NoCountry.user.UserRolesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserAuthService authService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody UserEntity usuarioRequest) {

        authService.registrar(usuarioRequest);

        return ResponseEntity.ok("Usuario creado exitosamente");
    }

    @PostMapping("/autenticar")
    public ResponseEntity<AuthenticationResponse> autenticar(@RequestBody UserEntity usuarioRequest) {
        return ResponseEntity.ok(authService.autenticar(usuarioRequest));
    }
}
