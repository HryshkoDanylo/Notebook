package Notebook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<NoteModel, Long> {
    List<NoteModel> findByUser(UserModel user);
    List<NoteModel> findByLastName(String lastName);
    List<NoteModel> findByBirthDate(java.time.LocalDate birthDate);
    List<NoteModel> findByPhoneNumber(String phoneNumber);
    List<NoteModel> findByAddress(String address);
    List<NoteModel> findByJobPlace(String jobPlace);
    List<NoteModel> findByEmail(String email);
}
