package udesc.dsw.droneseta.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import udesc.dsw.droneseta.model.entity.Admin;
import udesc.dsw.droneseta.model.entity.enums.RoleName;

@Data
@NoArgsConstructor
public class AdminDTO {

    private Long id;

    @NotEmpty(message = "O nome do admin deve ser informado")
    private String nome;

    @NotEmpty(message = "O email do admin deve ser informado")
    private String email;

    @NotEmpty(message = "A senha do admin deve ser informado")
    private String senha;

    private RoleName role;

    public AdminDTO(Admin admin) {
        this.id = admin.getId();
        this.nome = admin.getNome();
        this.email = admin.getEmail();
        this.role = admin.getRole();
    }
}
