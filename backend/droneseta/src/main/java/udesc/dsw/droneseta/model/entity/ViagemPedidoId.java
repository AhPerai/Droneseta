package udesc.dsw.droneseta.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ViagemPedidoId implements Serializable {
        @Column(name = "viagem_id")
        private Long viagemId;

        @Column(name = "pedido_id")
        private Long pedidoId;
}
