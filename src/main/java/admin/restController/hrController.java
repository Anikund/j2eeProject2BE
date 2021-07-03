package admin.restController;

import admin.dao.domain.HR;
import admin.dao.repo.HRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/hr", produces = "application/json")
@CrossOrigin(origins = "*")
public class hrController {
    @Autowired
    HRRepository hrRepo;

    @GetMapping("/get/{hrid}")
    public ResponseEntity<HR> getHR(@PathVariable("hrid") Long hrId) {
        Optional<HR> hr = hrRepo.findById(hrId);
        if (hr.isPresent()) {
            return new ResponseEntity<>(hr.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

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
