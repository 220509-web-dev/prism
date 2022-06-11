package dev.innov8.prism.employee;

import dev.innov8.prism.common.dtos.ResourceCreationResponse;
import dev.innov8.prism.common.security.Authenticated;
import dev.innov8.prism.common.security.SecurityContext;
import dev.innov8.prism.employee.dtos.EditEmployeeRequest;
import dev.innov8.prism.employee.dtos.EmployeeResponse;
import dev.innov8.prism.employee.dtos.NewEmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private SecurityContext securityContext;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @Authenticated
    @GetMapping(produces = "application/json")
    public List<EmployeeResponse> getAllOrgEmployees() {
        return employeeService.getOrgEmployees(securityContext.getRequester().getOrgId());
    }

    @Authenticated
    @GetMapping(value = "/{employeeId}", produces = "application/json")
    public EmployeeResponse getEmployeeById(@PathVariable String employeeId) {
        return employeeService.findEmployeeById(employeeId, securityContext.getRequester().getOrgId());
    }

    @Authenticated
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResourceCreationResponse registerNewEmployee(@Valid @RequestBody NewEmployeeRequest request) {
        request.setOrgId(securityContext.getRequester().getOrgId());
        return employeeService.addEmployeeToOrg(request);
    }

    @Authenticated
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(consumes = "application/json", produces = "application/json")
    public void editEmployeeInformation(@RequestBody EditEmployeeRequest request) {
        request.setOrgId(securityContext.getRequester().getOrgId());
        employeeService.updateEmployeeInfo(request);
    }


    @Authenticated
    @PatchMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reactivateEmployee(@PathVariable String employeeId) {
        employeeService.reactivateEmployee(employeeId, securityContext.getRequester().getOrgId());
    }

    @Authenticated
    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateEmployee(@PathVariable String employeeId) {
        employeeService.deactivateEmployee(employeeId, securityContext.getRequester().getOrgId());
    }

}
