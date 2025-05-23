package Notebook;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class NoteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lastName;
    private LocalDate birthDate;
    private String phoneNumber;
    private String address;
    private String jobPlace;
    private String email;

    public NoteModel() {
    }

    public NoteModel(String lastName, LocalDate birthDate, String phoneNumber, String address, String jobPlace, String email) {
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.jobPlace = jobPlace;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getJobPlace() {
        return jobPlace;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setJobPlace(String jobPlace) {
        this.jobPlace = jobPlace;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
