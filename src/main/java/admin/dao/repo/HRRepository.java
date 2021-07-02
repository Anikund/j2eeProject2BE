package admin.dao.repo;

import admin.dao.domain.HR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HRRepository extends JpaRepository<HR, Long> {
    HR findByUsername(String username);
    List<HR> findAll();
}
