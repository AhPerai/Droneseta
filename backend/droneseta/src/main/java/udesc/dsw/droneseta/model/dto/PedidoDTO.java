package udesc.dsw.droneseta.model.dto;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import udesc.dsw.droneseta.model.entity.Pedido;

@Data
@NoArgsConstructor
public class PedidoDTO {

    private Long id;

    @NotEmpty(message = "Um pedido deve ser criado com produtos")
    private List<ProdutoDTO> produtos;

    @Min(value = 1, message = "O preço do pedido deve ser informado")
    @NotNull(message = "O preço do pedido deve ser informado")
    private float preco;

    @NotNull(message = "O cliente que realizou o pedido deve ser informado")
    private Long clienteId;

    private boolean entregue;

    private LocalDateTime horario;

    public PedidoDTO(Pedido pedido) {
        this.produtos = new ArrayList<>();

        pedido.getProdutos().forEach(produtoPedido -> {
            produtos.add(new ProdutoDTO(produtoPedido.getProduto()));
        });

        this.preco = pedido.getPreco();
        this.entregue = pedido.isEntregue();
        this.clienteId = pedido.getCliente().getId();
        this.horario = LocalDateTime.now();
    }

}

