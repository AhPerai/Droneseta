package udesc.dsw.droneseta.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import udesc.dsw.droneseta.model.dto.PedidoDTO;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Pedido {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "pedido")
    private List<ProdutoPedido> produtos;

    @ManyToOne
    private Cliente cliente;

    @Column
    private float preco;

    @Column
    private LocalDateTime horario;

    @Column
    private int quantidadeAEntregar;

    @Column
    private int quantidadePedido;

    @ManyToOne
    private Endereco endereco;

    @Column
    private boolean entregue;

    public Pedido(PedidoDTO pedidoDTO){
        this.id = pedidoDTO.getId();
        this.preco = pedidoDTO.getPreco();
        this.horario = LocalDateTime.now();
        this.entregue = false;
    }

    public Pedido(float preco) {
        this.preco = preco;
        this.horario = LocalDateTime.now();
        this.entregue = false;
    }
}
