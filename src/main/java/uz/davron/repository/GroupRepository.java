package uz.davron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import uz.davron.entity.Group;
import uz.davron.response.GroupStudentsMark;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer>, JpaSpecificationExecutor<Group> {
   @Query(nativeQuery = true,
   value = "select s.first_name as \"name\", avg(m.mark)\n" +
       "from student s\n" +
       "         inner join groups g on g.id = s.group_id\n" +
       "         full join mark m on s.id = m.student_id\n" +
       "where g.id = :id\n" +
       "group by s.first_name\n" +
       "order by avg(m.mark) desc ")
    List<GroupStudentsMark> groupStudentsMark(@PathVariable("id") int id);


}
