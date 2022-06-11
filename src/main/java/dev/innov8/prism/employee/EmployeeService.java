package dev.innov8.prism.employee;

import dev.innov8.prism.common.dtos.ResourceCreationResponse;
import dev.innov8.prism.common.exceptions.AuthorizationException;
import dev.innov8.prism.common.exceptions.ResourceNotFoundException;
import dev.innov8.prism.common.exceptions.ResourcePersistenceException;
import dev.innov8.prism.employee.dtos.EditEmployeeRequest;
import dev.innov8.prism.employee.dtos.EmployeeResponse;
import dev.innov8.prism.employee.dtos.NewEmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static dev.innov8.prism.common.util.PrismUtils.notNil;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponse> getOrgEmployees(String orgId) {
        return employeeRepo.findEmployeesByOrganizationId(orgId)
                           .stream()
                           .map(EmployeeResponse::new)
                           .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EmployeeResponse findEmployeeById(String employeeId, String requesterOrgId) {
        Employee employee = employeeRepo.findById(employeeId).orElseThrow(ResourceNotFoundException::new);
        if (employee.getOrganization().getId().equals(requesterOrgId)) {
            return new EmployeeResponse(employee);
        } else {
            throw new AuthorizationException("That employee does not belong to your organization.");
        }
    }

    @Transactional
    public ResourceCreationResponse addEmployeeToOrg(NewEmployeeRequest request) {
        Employee newEmployee = Employee.fromNewRequest(request);
        newEmployee.setId(UUID.randomUUID().toString());
        newEmployee.setActive(true);
        try {
            employeeRepo.save(newEmployee);
        } catch (Exception e) {
            throw new ResourcePersistenceException(e.getMessage());
        }
        return new ResourceCreationResponse(newEmployee.getId());
    }

    @Transactional
    public void updateEmployeeInfo(EditEmployeeRequest request) {
        Employee employee = employeeRepo.findById(request.getId()).orElseThrow(ResourceNotFoundException::new);
        if (employee.getOrganization().getId().equals(request.getOrgId())) {

            if (notNil(request.getFirstName())) employee.setFirstName(request.getFirstName());
            if (notNil(request.getLastName())) employee.setLastName(request.getLastName());
            if (notNil(request.getEmailAddress())) employee.setEmailAddress(request.getEmailAddress());
            if (request.getAccountInfo() != null) {
                AccountInfo ogInfo = employee.getAccountInfo();
                AccountInfo newInfo = request.getAccountInfo();
                if (notNil(newInfo.getInstitutionName())) ogInfo.setInstitutionName(newInfo.getInstitutionName());
                if (notNil(newInfo.getAccountNumber())) ogInfo.setAccountNumber(newInfo.getAccountNumber());
                if (notNil(newInfo.getRoutingNumber())) ogInfo.setRoutingNumber(newInfo.getRoutingNumber());
            }

            System.out.println(employee);
            try {
                employeeRepo.save(employee);
            } catch (Exception e) {
                throw new ResourcePersistenceException(e.getMessage());
            }

        } else {
            throw new AuthorizationException("That employee does not belong to your organization.");
        }
    }

    @Transactional
    public void reactivateEmployee(String employeeId, String requesterOrgId) {
        Employee employee = employeeRepo.findById(employeeId).orElseThrow(ResourceNotFoundException::new);
        if (employee.getOrganization().getId().equals(requesterOrgId)) {
            employee.setActive(true);
        } else {
            throw new AuthorizationException("That employee does not belong to your organization.");
        }
    }

    @Transactional
    public void deactivateEmployee(String employeeId, String requesterOrgId) {
        Employee employee = employeeRepo.findById(employeeId).orElseThrow(ResourceNotFoundException::new);
        if (employee.getOrganization().getId().equals(requesterOrgId)) {
            employee.setActive(false);
        } else {
            throw new AuthorizationException("That employee does not belong to your organization.");
        }
    }



}
