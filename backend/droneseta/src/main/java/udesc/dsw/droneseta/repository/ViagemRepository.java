package udesc.dsw.droneseta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import udesc.dsw.droneseta.model.entity.Viagem;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {

    @Query("SELECT v FROM Viagem v ORDER BY v.horarioChegada DESC LIMIT 1")
    Viagem getFirstViagemOrderByHorarioChegadaDesc();

    @Query("SELECT COUNT(v) FROM Viagem v WHERE MONTH(v.horarioChegada) = MONTH(CURRENT_DATE)")
    int getNumeroViagemNoMes();

}
