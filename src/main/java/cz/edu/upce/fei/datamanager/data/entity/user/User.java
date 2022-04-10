package cz.edu.upce.fei.datamanager.data.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.edu.upce.fei.datamanager.data.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;

@Getter
@Setter
@Entity
public class User extends AbstractEntity {
    private String username;
    private String name;
    @JsonIgnore
    private String hashedPassword;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;
    @Lob
    private String profilePictureUrl;
}
