package udesc.dsw.droneseta.model.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import udesc.dsw.droneseta.model.dto.CartaoInfoDTO;

@Entity
@NoArgsConstructor
@Data
public class CartaoInfo {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String numero;

    @Column
    private String cvv;

    @Column
    private LocalDate vencimento;

    public CartaoInfo(String numero, String cvv, LocalDate vencimento) {
        this.numero = numero;
        this.cvv = cvv;
        this.vencimento = vencimento;
    }

    public CartaoInfo(CartaoInfoDTO cartaoInfoDTO){
        this.numero = cartaoInfoDTO.getNumero();
        this.cvv = cartaoInfoDTO.getCvv();
        this.vencimento = cartaoInfoDTO.getVencimento();
    }
}

