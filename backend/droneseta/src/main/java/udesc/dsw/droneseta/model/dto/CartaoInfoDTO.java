package udesc.dsw.droneseta.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import udesc.dsw.droneseta.model.entity.CartaoInfo;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CartaoInfoDTO {

    Long id;

    @Size(max = 16, min = 13, message = "O número de cartão informado não é válido")
    private String numero;

    @Size(min = 3, max = 3, message = "O código de segurança informado é inválido")
    private String cvv;

    @NotNull(message = "A data de vencimento do cartão deve ser inclusa")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate vencimento;

    public CartaoInfoDTO(CartaoInfo cartaoInfo){
        id = cartaoInfo.getId();
        numero = cartaoInfo.getNumero();
        cvv = cartaoInfo.getCvv();
        vencimento = cartaoInfo.getVencimento();
    }
}
