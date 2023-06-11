package udesc.dsw.droneseta.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumoMensalDTO {

    private int numeroViagens;
    private int totalPedidos;
    private float totalPreco;
}
