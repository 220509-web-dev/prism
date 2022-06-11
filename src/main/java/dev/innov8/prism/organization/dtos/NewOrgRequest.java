package dev.innov8.prism.organization.dtos;

import dev.innov8.prism.organization.Organization;

import javax.validation.constraints.NotBlank;

@SuppressWarnings("unused")
public class NewOrgRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String key;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Organization extractResource() {

        Organization newOrg = new Organization(name, key);
        newOrg.setName(name);
        newOrg.setKey(key);
        return newOrg;

    }

}
