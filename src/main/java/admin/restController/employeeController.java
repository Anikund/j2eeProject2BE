package admin.restController;

import admin.dao.domain.Employee;
import admin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employee", produces = "application/json")
@CrossOrigin(origins = "*")
public class employeeController {
    private EmployeeService employeeService;

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
}
