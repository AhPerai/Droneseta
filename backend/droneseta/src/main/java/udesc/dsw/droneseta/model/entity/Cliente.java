package udesc.dsw.droneseta.model.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import udesc.dsw.droneseta.model.dto.ClienteDTO;
import udesc.dsw.droneseta.model.entity.enums.RoleName;


@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Cliente extends Usuario{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = false)
    private RoleName role;

    @OneToOne
    private Endereco endereco;

    @OneToOne
    private CartaoInfo cartaoInfo;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Pedido> pedidos;

    public Cliente(String nome, String email, String senha, Endereco endereco, CartaoInfo cartaoInfo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
        this.cartaoInfo = cartaoInfo;
        this.role = RoleName.ROLE_USER;
    }

    public Cliente (ClienteDTO clienteDTO){
        this.id = clienteDTO.getId();
        this.nome = clienteDTO.getNome();
        this.email = clienteDTO.getEmail();
        this.senha = clienteDTO.getSenha();
        this.endereco = new Endereco(clienteDTO.getEndereco());
        this.cartaoInfo = new CartaoInfo(clienteDTO.getCartaoInfo());
        this.role = RoleName.ROLE_USER;
    }

}
