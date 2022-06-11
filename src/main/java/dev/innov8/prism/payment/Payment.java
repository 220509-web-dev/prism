package dev.innov8.prism.payment;

import dev.innov8.prism.employee.Employee;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Employee payee;

    @Column(nullable = false)
    private double amount;

    private LocalDateTime submitted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Employee getPayee() {
        return payee;
    }

    public void setPayee(Employee payee) {
        this.payee = payee;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getSubmitted() {
        return submitted;
    }

    public void setSubmitted(LocalDateTime submitted) {
        this.submitted = submitted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Double.compare(payment.amount, amount) == 0 && Objects.equals(id, payment.id) && Objects.equals(payee, payment.payee) && Objects.equals(submitted, payment.submitted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, payee, amount, submitted);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", payee=" + payee +
                ", amount=" + amount +
                ", submitted=" + submitted +
                '}';
    }

}
