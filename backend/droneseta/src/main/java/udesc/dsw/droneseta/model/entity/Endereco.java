package udesc.dsw.droneseta.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import udesc.dsw.droneseta.model.dto.EnderecoDTO;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String rua;

    @Column
    private String cep;

    @Column
    private String pais;

    @Column
    private String bairro;

    @Column
    private String cidade;

    @Column
    private String estado;

    @Column
    private int numero;

    @OneToMany(mappedBy = "endereco")
    @JsonIgnore
    private List<Pedido> pedidos;

    public Endereco(String pais,String estado, String cidade, String bairro, String rua, String cep, int numero) {
        this.rua = rua;
        this.cep = cep;
        this.pais = pais;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
    }

    public Endereco(EnderecoDTO dto) {
        id = dto.getId();
        rua = dto.getRua();
        cep = dto.getCep();
        pais = dto.getPais();
        bairro = dto.getBairro();
        cidade = dto.getCidade();
        estado = dto.getEstado();
        numero = dto.getNumero();
        pedidos = dto.getPedidos();
    }
}

