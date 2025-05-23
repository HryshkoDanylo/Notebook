package Notebook;

import jakarta.persistence.*;
import java.util.List;
import jakarta.persistence.ManyToOne;

@Entity
public class UserModel {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<NoteModel> notes;

    // Геттери та сеттери

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<NoteModel> getNotes() { return notes; }
    public void setNotes(List<NoteModel> notes) { this.notes = notes; }
}
