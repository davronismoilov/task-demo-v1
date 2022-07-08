package uz.davron.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.davron.entity.Student;
import uz.davron.entity.Subject;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {
    List<Student> findByFirstNameIsLikeIgnoreCaseAllIgnoreCase(String firstName);

    @Query(nativeQuery = true, value =
        "select s.name from  subject  s,  student st , journal j , journal_subjects js\n" +
            "where  st.group_id = j.group_id and s.id = js.subjects_id and j.id = js.journal_id and st.id =:id")
    List<String> findByIdSubjects(@Param("id") int id);
}
