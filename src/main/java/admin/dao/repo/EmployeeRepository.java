package admin.dao.repo;

import admin.dao.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee getById(Long id);
    Employee findByTel(String tel);
    List<Employee> findByName(String name);
    List<Employee> findByGender(String gender);
    List<Employee> findByDepartment(String department);
    List<Employee> findByIsOnTheJob(Boolean isOnTheJob);
    List<Employee> findByCurrentCompany(String currentCompany);
    List<Employee> findByAge(Integer age);

    /**
     * find employees with age between lower and upper
     * @param lower
     * @param upper
     * @return a list of employees that satisfy the requirements
     */
    List<Employee> findByAgeBetween(Integer lower, Integer upper);
    List<Employee> findAll();
}
