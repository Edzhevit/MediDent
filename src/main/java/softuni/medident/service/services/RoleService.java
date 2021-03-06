package softuni.medident.service.services;

import softuni.medident.service.models.RoleServiceModel;

import java.util.Set;

public interface RoleService {
    void seedRolesInDb();

    Set<RoleServiceModel> findAllRoles();

    RoleServiceModel findByAuthority(String authority);

}
