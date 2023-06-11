package udesc.dsw.droneseta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import udesc.dsw.droneseta.model.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
