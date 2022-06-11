package dev.innov8.prism.employee;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class AccountInfo {

    private String institutionName;
    private String routingNumber;
    private String accountNumber;

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountInfo that = (AccountInfo) o;
        return Objects.equals(institutionName, that.institutionName) && Objects.equals(routingNumber, that.routingNumber) && Objects.equals(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(institutionName, routingNumber, accountNumber);
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "institutionName='" + institutionName + '\'' +
                ", routingNumber='" + routingNumber + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }

}
