package udesc.dsw.droneseta.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDTO {
    @NotEmpty(message = "O email do usuário deve ser informado")
    private String email;

    @NotEmpty(message = "A senha do usuário deve ser informado")
    private String senha;
}
