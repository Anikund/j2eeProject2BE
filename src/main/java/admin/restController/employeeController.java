package admin.restController;

import admin.dao.domain.Employee;
import admin.dao.domain.HR;
import admin.dao.repo.HRRepository;
import admin.service.EmployeeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/employee", produces = "application/json")
@CrossOrigin(origins = "*")
public class employeeController {
    private EmployeeService employeeService;
    @Autowired
    private HRRepository hrRepository;
    @Autowired
    void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/query/{conditions}/{values}")
    public List<Employee> queryEmployees(@PathVariable("conditions") String[] conditions,
                                         @PathVariable("values") String[] values) {
        return employeeService.findByConditions(conditions, values);
    }

    @GetMapping("/all/{cop}")
    public List<Employee> getAllEmployeesByCorporation(@PathVariable("cop") String corporation) {

        return employeeService.findByCurrentCompany(corporation);
    }

    @GetMapping("/allemployee")
    public List<Employee> getAllEmployees() {

        return employeeService.findAll();
    }

    @PostMapping("/new")
    public ResponseEntity<Employee> newEmployee(@RequestBody Employee employee) {
        boolean success = employeeService.addEmployee(employee);
        if (!success) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/update")
    @PatchMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee origin = employeeService.findById(employee.getId());
        if (origin.getName() != employee.getName()) {
            employeeService.updateEmployeeName(origin, employee.getName());
        } else if (origin.getAge() != employee.getAge()) {
            employeeService.updateEmployeeAge(origin, employee.getAge());
        } else if (origin.getTel() != employee.getTel()) {
            employeeService.updateEmployeeTel(origin, employee.getTel());
        } else if (origin.getDepartment() != employee.getDepartment()) {
            employeeService.updateEmployeeDepartment(origin, employee.getDepartment());
        } else if (origin.getGender() != employee.getGender()) {
            employeeService.updateEmployeeGender(origin, employee.getGender());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/delete/{eid}/{hrid}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("eid") Long eid, @PathVariable("hrid") Long hrid) {
        Optional<HR> hr = hrRepository.findById(hrid);
        if(hr.isPresent()){
            Employee em = employeeService.findById(eid);
            employeeService.delEmployee(em, hrid, hr.get().getName(), hr.get().getCompany());
        }

    }

    @PatchMapping("/hire/{eid}/{hrid}")
    public ResponseEntity<Employee> hire(@PathVariable Long eid, @PathVariable Long hrid) {
        Optional<HR> hr = hrRepository.findById(hrid);
        if (!hr.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        employeeService.hireEmployee(employeeService.findById(eid), hr.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
