package udesc.dsw.droneseta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import udesc.dsw.droneseta.model.entity.Cliente;
import udesc.dsw.droneseta.model.entity.Pedido;

import java.util.List;
import java.util.Map;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByEntregueOrderByHorario(boolean entregue);

    List<Pedido> findByCliente(Cliente cliente);

    @Query("SELECT SUM(p.quantidadePedido) AS totalQuantidadePedido, SUM(p.preco) AS totalPreco FROM Pedido p WHERE MONTH(p.horario) = MONTH(CURRENT_DATE) AND p.entregue = true")
    Map<String, Integer> resumoPedidosDoMes();
}
