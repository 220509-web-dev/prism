package dev.innov8.prism.employee.dtos;

import dev.innov8.prism.employee.Employee;

@SuppressWarnings("unused")
public class EmployeeResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String orgId;
    private AccountInfoResponse accountInfo;
    private boolean active;

    public EmployeeResponse(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.emailAddress = employee.getEmailAddress();
        this.orgId = employee.getOrganization().getId();
        this.accountInfo = new AccountInfoResponse(employee.getAccountInfo());
        this.active = employee.isActive();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public AccountInfoResponse getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfoResponse accountInfo) {
        this.accountInfo = accountInfo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "EmployeeResponse{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", orgId='" + orgId + '\'' +
                ", accountInfo=" + accountInfo +
                ", active=" + active +
                '}';
    }

}
