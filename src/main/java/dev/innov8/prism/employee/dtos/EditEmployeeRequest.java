package dev.innov8.prism.employee.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.innov8.prism.employee.AccountInfo;

import javax.validation.constraints.NotBlank;

@SuppressWarnings("unused")
public class EditEmployeeRequest {

    @NotBlank
    private String id;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private AccountInfo accountInfo;

    @JsonIgnore
    private String orgId;

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

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        return "EditEmployeeRequest{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", accountInfo=" + accountInfo +
                ", orgId='" + orgId + '\'' +
                '}';
    }
}
