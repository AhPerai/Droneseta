package udesc.dsw.droneseta.model.entity;

import lombok.Getter;
import lombok.Setter;
import udesc.dsw.droneseta.model.entity.enums.RoleName;

@Setter
@Getter
public abstract class Usuario {

    protected Long id;
    protected String email;
    protected String senha;
    protected RoleName role;

}
