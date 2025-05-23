package Notebook;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserModel());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserModel user, HttpSession session, Model model) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Користувач з таким іменем вже існує.");
            return "registration";
        }
        UserModel savedUser = userRepository.save(user);
        session.setAttribute("user", savedUser);  // Автоматичний логін
        return "redirect:/notes";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        UserModel user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            return "redirect:/notes";
        } else {
            model.addAttribute("error", "Невірне ім’я користувача або пароль.");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/user")
    public String userProfile(HttpSession session, Model model) {
        UserModel user = (UserModel) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        model.addAttribute("user", user);
        return "user";
    }
}
