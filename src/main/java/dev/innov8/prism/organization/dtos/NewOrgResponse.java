package dev.innov8.prism.organization.dtos;

@SuppressWarnings("unused")
public class NewOrgResponse {

    private String orgId;
    private String authCode;

    public NewOrgResponse() {
        super();
    }

    public NewOrgResponse(String orgId, String authCode) {
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
        return "NewOrgResponse{" +
                "orgId='" + orgId + '\'' +
                ", authCode='" + authCode + '\'' +
                '}';
    }

}
