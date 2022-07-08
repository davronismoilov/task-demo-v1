package uz.davron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uz.davron.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer>, JpaSpecificationExecutor<University> {
}
