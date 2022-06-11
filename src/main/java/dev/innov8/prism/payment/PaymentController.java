package dev.innov8.prism.payment;

import dev.innov8.prism.common.dtos.ResourceCreationResponse;
import dev.innov8.prism.common.security.Authenticated;
import dev.innov8.prism.common.security.SecurityContext;
import dev.innov8.prism.payment.dtos.NewPaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private SecurityContext securityContext;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Autowired
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @Authenticated
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResourceCreationResponse postPaymentToEmployeeAccount (@Valid @RequestBody NewPaymentRequest request) {
        return paymentService.postPaymentToEmployeeAccount(request, securityContext.getRequester().getOrgId());
    }

}
