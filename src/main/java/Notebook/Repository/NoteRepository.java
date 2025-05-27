package Notebook.Repository;

import Notebook.Model.NoteModel;
import Notebook.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<NoteModel, Long> {
    List<NoteModel> findByUser(UserModel user);
}
