package dev.innov8.prism.organization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrgRepository extends JpaRepository<Organization, String> {
    Optional<Organization> findOrganizationByIdAndAuthCode(String id, String authCode);
}
