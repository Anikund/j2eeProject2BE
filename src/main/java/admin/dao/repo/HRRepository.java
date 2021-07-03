package admin.dao.repo;

import admin.dao.domain.HR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HRRepository extends JpaRepository<HR, Long> {
    Optional<HR> findByUsername(String username);
    List<HR> findAll();
}
