package udesc.dsw.droneseta.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DadosProdutosVendidosDTO {

    private String produtoNome;
    private String tamanho;
    private int totalQuantidade;
    private float totalPreco;

    public DadosProdutosVendidosDTO(String produtoNome, String tamanho, int totalQuantidade, float totalPreco) {
        this.produtoNome = produtoNome;
        this.tamanho = tamanho;
        this.totalQuantidade = totalQuantidade;
        this.totalPreco = totalPreco;
    }
}
