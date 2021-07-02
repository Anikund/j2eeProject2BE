package admin.restController;

import admin.dao.domain.Employee;
import admin.dao.domain.StaffWorkRec;
import admin.service.StaffWorkRecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(path = "/api/workRecord", produces = "application/json")
@CrossOrigin(origins = "*")
public class workRecordController {
    @Autowired
    StaffWorkRecService recordService;

    @GetMapping("/browse")
    public List<StaffWorkRec> getRecordByEmployee(@RequestBody Long employeeId) {
        List<StaffWorkRec> list = recordService.findByEmployeeId(employeeId);
        Collections.sort(list, new Comparator<StaffWorkRec>() {
            @Override
            public int compare(StaffWorkRec o1, StaffWorkRec o2) {
                return o1.getInDate().compareTo(o2.getInDate());
            }
        });
        return list;
    }


}
