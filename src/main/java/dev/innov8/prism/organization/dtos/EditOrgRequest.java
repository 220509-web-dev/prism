package dev.innov8.prism.organization.dtos;

import javax.validation.constraints.NotBlank;

@SuppressWarnings("unused")
public class EditOrgRequest {

    @NotBlank
    private String orgId;

    @NotBlank
    private String orgName;

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


    @Override
    public String toString() {
        return "EditOrgRequest{" +
                "orgId='" + orgId + '\'' +
                ", orgName='" + orgName + '\'' +
                '}';
    }

}
