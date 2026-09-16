package io.github.patrickbg.libraryapi.controller;

import io.github.patrickbg.libraryapi.security.CustomAuthentication;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Tag(name = "Login Viewer")
public class LoginViewController {

    @GetMapping("/login")
    public String paginaLogin() {
        return "login";
    }

    @GetMapping("/")
    @ResponseBody
    public String paginaHome(Authentication authentication) {

        if (authentication instanceof CustomAuthentication customAuth) {
            System.out.println(customAuth.getUsuario());
        }
        return "Olá " + authentication.getName();
    }

    @GetMapping("/authorized")
    @ResponseBody
    @Operation(summary = "Autorizar", description = "Ver código de autorização")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso na verificação do código."),
            @ApiResponse(responseCode = "404", description = "Código de autorização não encontrado.")
    })
    public String getAuthorizationCode(@RequestParam("code") String code) {
        return "Seu authorization code " + code;
    }
}

