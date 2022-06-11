package dev.innov8.prism.organization;

import dev.innov8.prism.common.security.Authenticated;
import dev.innov8.prism.common.security.SecurityContext;
import dev.innov8.prism.organization.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/organizations")
public class OrgController {

    private final OrgService orgService;
    private SecurityContext securityContext;

    @Autowired
    public OrgController(OrgService orgService) {
        this.orgService = orgService;
    }

    @Autowired
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public NewOrgResponse registerNewOrganization(@Valid @RequestBody NewOrgRequest newOrgRequest) {
        return orgService.createNewOrganization(newOrgRequest);
    }

    @Authenticated
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(consumes = "application/json", produces = "application/json")
    public void editOrganization(@Valid @RequestBody EditOrgRequest request) {
        orgService.editOrganization(request, securityContext.getRequester().getAuthCode());
    }

    @Authenticated
    @DeleteMapping("/{orgId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable String orgId) {
        orgService.removeOrganization(orgId, securityContext.getRequester().getAuthCode());
    }

    // GET all
    // GET by id
    // GET by email
    // POST new
    // PATCH existing
    // DELETE (soft)

}
