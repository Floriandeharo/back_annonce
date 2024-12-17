package tech.chillo.avis_utilisateurs.enums;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum TypeDeRole {

    UTILISATEUR(
            Set.of(TypePermission.UTILISATEUR_CREATE_AVIS)
    ),
    MANAGER(
            Set.of(
                    TypePermission.MANAGER_CREATE,
                    TypePermission.MANAGER_READ,
                    TypePermission.MANAGER_UPDATE,
                    TypePermission.MANAGER_DELETE_AVIS
            )
    ),
    ADMINISTRATEUR(
            Set.of(
                    TypePermission.ADMINISTRATEUR_CREATE,
                    TypePermission.ADMINISTRATEUR_READ,
                    TypePermission.ADMINISTRATEUR_UPDATE,
                    TypePermission.ADMINISTRATEUR_DELETE,

                    TypePermission.MANAGER_CREATE,
                    TypePermission.MANAGER_READ,
                    TypePermission.MANAGER_UPDATE,
                    TypePermission.MANAGER_DELETE_AVIS
            )
    );

    private final Set<TypePermission> permissions;

    // Constructor
    private TypeDeRole(Set<TypePermission> permissions) {
        this.permissions = permissions;
    }

    // Getter
    public Set<TypePermission> getPermissions() {
        return permissions;
    }
    
    public Collection<? extends GrantedAuthority> getAuthorities(){
    	final List<SimpleGrantedAuthority> grantedAuthorities = this.getPermissions().stream().map(
    			permission -> new SimpleGrantedAuthority(permission.getLibelle())
    			).collect(Collectors.toList());
    	
    	grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    	return grantedAuthorities;
    }
}

