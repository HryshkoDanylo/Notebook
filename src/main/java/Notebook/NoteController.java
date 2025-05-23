package Notebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    // Показати список нотаток лише для поточного користувача
    @GetMapping
    public String listNotes(HttpSession session, Model model) {
        UserModel user = (UserModel) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        List<NoteModel> userNotes = noteRepository.findByUser(user);
        model.addAttribute("notes", userNotes);
        return "list";
    }

    // Форма створення нотатки
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("note", new NoteModel());
        return "create";
    }

    // Обробка створення через AJAX — встановлюємо користувача
    @PostMapping("/create-ajax")
    @ResponseBody
    public NoteModel createNoteAjax(@RequestBody NoteModel note, HttpSession session) {
        UserModel user = (UserModel) session.getAttribute("user");
        if (user == null) {
            return null;
        }

        note.setUser(user);
        return noteRepository.save(note);
    }

    // Форма редагування (перевірка не обов'язкова, але бажана)
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
        Optional<NoteModel> optionalNote = noteRepository.findById(id);
        UserModel user = (UserModel) session.getAttribute("user");

        if (optionalNote.isPresent() && optionalNote.get().getUser().getId().equals(user.getId())) {
            model.addAttribute("note", optionalNote.get());
            return "edit";
        }

        return "redirect:/notes";
    }

    // Редагування нотатки
    @PostMapping("/edit/{id}")
    public String editNote(@PathVariable Long id, @ModelAttribute NoteModel updatedNote, HttpSession session) {
        UserModel user = (UserModel) session.getAttribute("user");
        Optional<NoteModel> existingNote = noteRepository.findById(id);

        if (existingNote.isPresent() && existingNote.get().getUser().getId().equals(user.getId())) {
            updatedNote.setId(id);
            updatedNote.setUser(user);  // Не забуваємо прив'язку
            noteRepository.save(updatedNote);
        }

        return "redirect:/notes";
    }

    // Видалення нотатки (через посилання)
    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id, HttpSession session) {
        UserModel user = (UserModel) session.getAttribute("user");
        Optional<NoteModel> existingNote = noteRepository.findById(id);

        if (existingNote.isPresent() && existingNote.get().getUser().getId().equals(user.getId())) {
            noteRepository.deleteById(id);
        }

        return "redirect:/notes";
    }

    // === AJAX методи ===

    @PostMapping("/edit-ajax/{id}")
    @ResponseBody
    public String editNoteAjax(@PathVariable Long id, @RequestBody NoteModel updatedNote, HttpSession session) {
        UserModel user = (UserModel) session.getAttribute("user");
        Optional<NoteModel> existingNote = noteRepository.findById(id);

        if (existingNote.isPresent() && existingNote.get().getUser().getId().equals(user.getId())) {
            updatedNote.setId(id);
            updatedNote.setUser(user);
            noteRepository.save(updatedNote);
            return "OK";
        }

        return "ERROR";
    }

    @PostMapping("/delete-ajax/{id}")
    @ResponseBody
    public String deleteNoteAjax(@PathVariable Long id, HttpSession session) {
        UserModel user = (UserModel) session.getAttribute("user");
        Optional<NoteModel> existingNote = noteRepository.findById(id);

        if (existingNote.isPresent() && existingNote.get().getUser().getId().equals(user.getId())) {
            noteRepository.deleteById(id);
            return "OK";
        }

        return "ERROR";
    }
}
