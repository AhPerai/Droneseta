package udesc.dsw.droneseta.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import udesc.dsw.droneseta.model.entity.Endereco;
import udesc.dsw.droneseta.model.entity.Pedido;

import java.util.List;

@Data
@NoArgsConstructor
public class EnderecoDTO {

    private Long id;

    @NotEmpty(message = "É necessário informar o cep  no endereço ")
    private String cep;

    @NotEmpty(message = "É necessário informar o pais no endereço ")
    private String pais;

    @NotEmpty(message = "É necessário informar o estado no endereço ")
    private String estado;

    @NotEmpty(message = "É necessário informar a cidade no endereço ")
    private String cidade;

    @NotEmpty(message = "É necessário informar o bairro no endereço ")
    private String bairro;

    @NotEmpty(message = "É necessário informar a rua  no endereço ")
    private String rua;

    @NotNull(message = "É necessário informar número no endereço ")
    private int numero;

    private List<Pedido> pedidos;

    public EnderecoDTO(Endereco endereco){
        id = endereco.getId();
        estado = endereco.getEstado();
        cidade = endereco.getCidade();
        bairro = endereco.getBairro();
        rua = endereco.getRua();
        numero = endereco.getNumero();
    }
}
