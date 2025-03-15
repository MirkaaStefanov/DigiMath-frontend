package com.example.DigiMath_frontend.controllers;


import com.example.DigiMath_frontend.clients.AuthenticationClient;
import com.example.DigiMath_frontend.clients.UserClient;
import com.example.DigiMath_frontend.dtos.AuthenticationRequest;
import com.example.DigiMath_frontend.dtos.AuthenticationResponse;
import com.example.DigiMath_frontend.dtos.RegisterRequest;
import com.example.DigiMath_frontend.dtos.UserDTO;
import com.example.DigiMath_frontend.models.User;
import com.example.DigiMath_frontend.session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationClient authenticationClient;
    private final SessionManager sessionManager;
    private static final String REDIRECTTXT = "redirect:/";
    private final UserClient userClient;


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(RegisterRequest request, Model model, HttpServletRequest httpServletRequest) {
        try {
            AuthenticationResponse authenticationResponse = authenticationClient.register(request);
            sessionManager.setSessionToken(httpServletRequest, authenticationResponse.getAccessToken(), authenticationResponse.getUser().getRole().toString());
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Потребител с този имейл вече съществева!");
            return "register";
        }
    }

    @GetMapping("/login")
    public String login(Model model, AuthenticationRequest authenticationRequest) {
        model.addAttribute("user", new UserDTO());
        return "login";
    }


    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("sessionToken");
        authenticationClient.logout(token);
        sessionManager.invalidateSession(request);
        return new ModelAndView(REDIRECTTXT);
    }

    @PostMapping("/login")
    public ModelAndView login(AuthenticationRequest authenticationRequest, HttpServletRequest httpServletRequest) {
        try {
            AuthenticationResponse authenticationResponse = authenticationClient.authenticate(authenticationRequest);
            sessionManager.setSessionToken(httpServletRequest, authenticationResponse.getAccessToken(), authenticationResponse.getUser().getRole().toString());
            return new ModelAndView(REDIRECTTXT);
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            modelAndView.addObject("error", "Невалидно име или парола");
            return modelAndView;
        }
    }

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password"; // Name of the Thymeleaf template
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam("email") String email, Model model) {
        try {
            ResponseEntity<String> response = authenticationClient.forgotPassword(email);
            model.addAttribute("message", response.getBody());
            return "forgot-password-success"; // Name of the success Thymeleaf template
        } catch (Exception e) {
            String errorMessage = (e.getCause() != null && e.getCause().getMessage() != null)
                    ? e.getCause().getMessage()
                    : e.getMessage();
            model.addAttribute("error", errorMessage);
            return "forgot-password"; // Redirect back to the form with error message
        }
    }

    @GetMapping("/forgotten-password")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        model.addAttribute("token", token);
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("token") String token, @RequestParam("password") String password, Model model) {
        try {
            authenticationClient.resetPassword(token, password);
            model.addAttribute("message", "Password successfully updated.");
            return "reset-password-success"; // Name of the success Thymeleaf template
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "reset-password"; // Return to the form with an error message
        }
    }
}
