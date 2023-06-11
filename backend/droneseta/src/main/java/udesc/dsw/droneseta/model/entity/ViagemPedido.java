package udesc.dsw.droneseta.model.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "viagem_pedido")
public class ViagemPedido {

    @EmbeddedId
    private ViagemPedidoId id;
}
