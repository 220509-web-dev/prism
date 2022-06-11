package dev.innov8.prism.auth;

import dev.innov8.prism.organization.Organization;

@SuppressWarnings("unused")
public class Principal {

    private String orgId;
    private String orgName;
    private String authCode;

    public Principal() {
        super();
    }

    public Principal(Organization org) {
        this.orgId = org.getId();
        this.orgName = org.getName();
        this.authCode = org.getAuthCode();
    }

    public Principal(String id, String orgName, String authCode) {
        this.orgId = id;
        this.orgName = orgName;
        this.authCode = authCode;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    @Override
    public String toString() {
        return "PrincipalResponse{" +
                "orgId='" + orgId + '\'' +
                ", orgName='" + orgName + '\'' +
                '}';
    }

}
