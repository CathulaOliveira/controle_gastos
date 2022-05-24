package br.edu.utfpr.pb.server.controller;

import br.edu.utfpr.pb.server.model.User;
import br.edu.utfpr.pb.server.security.AuthUserService;
import br.edu.utfpr.pb.server.security.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private AuthUserService authUserService;

    @GetMapping("user-info")
    public UserDTO getUserInfo(Principal principal) {
        return new UserDTO( (User)
                authUserService.loadUserByUsername(principal.getName()));
    }
}
