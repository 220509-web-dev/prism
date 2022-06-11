package dev.innov8.prism.auth.dtos;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@SuppressWarnings("unused")
public class AuthenticationRequest {

    @NotBlank
    private String orgId;

    @NotBlank
    private String authCode;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationRequest that = (AuthenticationRequest) o;
        return Objects.equals(orgId, that.orgId) && Objects.equals(authCode, that.authCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orgId, authCode);
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "orgId='" + orgId + '\'' +
                ", authCode='" + authCode + '\'' +
                '}';
    }

}
