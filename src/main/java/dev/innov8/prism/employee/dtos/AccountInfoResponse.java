package dev.innov8.prism.employee.dtos;

import dev.innov8.prism.employee.AccountInfo;

@SuppressWarnings("unused")
public class AccountInfoResponse {

    private String institutionName;
    private String routingNumber;
    private String accountNumber;

    public AccountInfoResponse() {
        super();
    }

    public AccountInfoResponse(AccountInfo accountInfo) {
        this.institutionName = accountInfo.getInstitutionName();
        this.accountNumber = accountInfo.getAccountNumber();
        this.routingNumber = accountInfo.getRoutingNumber();
    }

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
    public String toString() {
        return "AccountInfoResponse{" +
                "institutionName='" + institutionName + '\'' +
                ", routingNumber='" + routingNumber + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }

}
