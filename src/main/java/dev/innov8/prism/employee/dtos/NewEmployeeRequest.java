package dev.innov8.prism.employee.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.innov8.prism.employee.AccountInfo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("unused")
public class NewEmployeeRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String emailAddress;

    @JsonIgnore
    private String orgId;

    private AccountInfo accountInfo;

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

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    @Override
    public String toString() {
        return "NewEmployeeRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", orgId='" + orgId + '\'' +
                ", accountInfo=" + accountInfo +
                '}';
    }

}
