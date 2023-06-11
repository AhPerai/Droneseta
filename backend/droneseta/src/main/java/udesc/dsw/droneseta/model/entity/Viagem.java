package udesc.dsw.droneseta.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Viagem {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private LocalDateTime horarioChegada;

    @Column
    private LocalDateTime horarioSaida;

    @Column
    private int qtdProdutos;

    @ManyToMany
    @JoinTable(name = "viagem_pedido",
            joinColumns = @JoinColumn(name = "viagem_id"),
            inverseJoinColumns = @JoinColumn(name = "pedido_id"))
    private List<Pedido> pedidos;

    public Viagem(List<Pedido> pedidos, int qtdProdutos) {
        this.pedidos = pedidos;
        this.qtdProdutos = qtdProdutos;
        LocalDateTime saida = LocalDateTime.now();
        this.horarioSaida = saida;
        this.horarioChegada = saida.plusMinutes(1);
//        this.horarioChegada = saida.plusHours(1);
    }
}
