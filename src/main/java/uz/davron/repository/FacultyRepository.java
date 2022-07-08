package uz.davron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import uz.davron.entity.Faculty;
import uz.davron.response.GroupStudentCount;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer>, JpaSpecificationExecutor<Faculty> {

    @Query(nativeQuery = true , value = "select g.name  , count(s) from faculty f inner join groups g on f.id = g.faculty_id\n" +
        "                        inner join student s on g.id = s.group_id  where f.id = :id group by g.name  ")
    List<GroupStudentCount> findByIdFaculty(@PathVariable("id") int id);
}
