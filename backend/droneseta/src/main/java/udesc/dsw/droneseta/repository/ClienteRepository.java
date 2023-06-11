package udesc.dsw.droneseta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udesc.dsw.droneseta.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente getClienteByEmail(String email);
}
