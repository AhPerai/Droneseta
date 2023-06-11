package udesc.dsw.droneseta.model.entity;

import jakarta.persistence.*;
import lombok.*;
import udesc.dsw.droneseta.model.dto.AdminDTO;
import udesc.dsw.droneseta.model.entity.enums.RoleName;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Admin extends Usuario{

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

    public Admin(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = RoleName.ROLE_ADMIN;
    }

    public Admin(AdminDTO adminDTO){
        id = adminDTO.getId();
        nome = adminDTO.getNome();
        email = adminDTO.getEmail();
        senha = adminDTO.getSenha();
        role = RoleName.ROLE_ADMIN;
    }
}
