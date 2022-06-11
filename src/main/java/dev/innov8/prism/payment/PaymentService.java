package dev.innov8.prism.payment;

import dev.innov8.prism.common.dtos.ResourceCreationResponse;
import dev.innov8.prism.common.exceptions.AuthorizationException;
import dev.innov8.prism.common.exceptions.ResourceNotFoundException;
import dev.innov8.prism.employee.Employee;
import dev.innov8.prism.employee.EmployeeRepository;
import dev.innov8.prism.payment.dtos.NewPaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepo;
    private final EmployeeRepository employeeRepo;

    @Autowired
    public PaymentService(PaymentRepository paymentRepo, EmployeeRepository employeeRepo) {
        this.paymentRepo = paymentRepo;
        this.employeeRepo = employeeRepo;
    }

    @Transactional
    public ResourceCreationResponse postPaymentToEmployeeAccount(NewPaymentRequest request, String requesterOrgId) {
        Employee payee = employeeRepo.findById(request.getPayeeId()).orElseThrow(ResourceNotFoundException::new);
        if (payee.getOrganization().getId().equals(requesterOrgId)) {
            Payment newPayment = new Payment();
            newPayment.setSubmitted(LocalDateTime.now());
            newPayment.setId(UUID.randomUUID().toString());
            newPayment.setPayee(payee);
            newPayment.setAmount(request.getPaymentAmount());
            paymentRepo.save(newPayment);
            return new ResourceCreationResponse(newPayment.getId());
        } else {
            throw new AuthorizationException("That employee does not belong to your organization.");
        }
    }

}
