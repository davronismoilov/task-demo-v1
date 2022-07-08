package uz.davron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uz.davron.entity.Mark;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Integer>, JpaSpecificationExecutor<Mark> {
}
