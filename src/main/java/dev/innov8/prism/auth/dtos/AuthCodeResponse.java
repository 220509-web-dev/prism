package dev.innov8.prism.auth.dtos;

@SuppressWarnings("unused")
public class AuthCodeResponse {

    private String orgId;
    private String authCode;

    public AuthCodeResponse() {
        super();
    }

    public AuthCodeResponse(String orgId, String authCode) {
        this.orgId = orgId;
        this.authCode = authCode;
    }

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
    public String toString() {
        return "AuthCodeResponse{" +
                "orgId='" + orgId + '\'' +
                ", authCode='" + authCode + '\'' +
                '}';
    }

}
