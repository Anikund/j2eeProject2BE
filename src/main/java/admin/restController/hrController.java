package admin.restController;

import admin.dao.domain.HR;
import admin.dao.repo.HRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/hr", produces = "application/json")
@CrossOrigin(origins = "*")
public class hrController {
    @Autowired
    HRRepository hrRepo;

    @GetMapping("/get")
    public HR getHR(@RequestBody Long hrId) {
        return hrRepo.getById(hrId);
    }

    @PatchMapping("/update")
    @PutMapping("/update")
    public HR updateHR(@RequestBody HR hr) {
        return hrRepo.save(hr);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHR(@RequestBody Long id) {
        Optional<HR> hr = hrRepo.findById(id);
        if (hr.isPresent()) {
            hrRepo.delete(hr.get());
        }
    }
}
