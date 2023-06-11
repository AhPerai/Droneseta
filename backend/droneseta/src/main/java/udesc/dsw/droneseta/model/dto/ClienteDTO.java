package udesc.dsw.droneseta.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import udesc.dsw.droneseta.model.entity.CartaoInfo;
import udesc.dsw.droneseta.model.entity.Cliente;
import udesc.dsw.droneseta.model.entity.enums.RoleName;

@Data
@NoArgsConstructor
public class ClienteDTO {

    private Long id;

    @NotEmpty(message = "O nome do cliente deve ser informado")
    private String nome;

    @NotEmpty(message = "O email do cliente deve ser informado")
    private String email;

    @NotEmpty(message = "A senha do cliente deve ser informado")
    private String senha;

    @NotNull(message = "O endereço do cliente deve ser informado")
    private EnderecoDTO endereco;

    @NotNull(message = "As informações do cartão devem ser inclusas")
    private CartaoInfoDTO cartaoInfo;

    private RoleName role;

    public ClienteDTO(Cliente cliente){
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.endereco = new EnderecoDTO(cliente.getEndereco());
        this.cartaoInfo = new  CartaoInfoDTO(cliente.getCartaoInfo());
        this.role = RoleName.ROLE_ADMIN;
    }

}
