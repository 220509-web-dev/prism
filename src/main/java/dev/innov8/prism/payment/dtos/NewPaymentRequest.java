package dev.innov8.prism.payment.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("unused")
public class NewPaymentRequest {

    @NotBlank
    private String payeeId;

    @Min(1)
    private double paymentAmount;

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Override
    public String toString() {
        return "NewPaymentRequest{" +
                "payeeId='" + payeeId + '\'' +
                ", paymentAmount=" + paymentAmount +
                '}';
    }

}
