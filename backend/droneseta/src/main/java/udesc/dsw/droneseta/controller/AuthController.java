package udesc.dsw.droneseta.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import udesc.dsw.droneseta.exception.ValidatorException;
import udesc.dsw.droneseta.model.dto.*;
import udesc.dsw.droneseta.model.entity.*;
import udesc.dsw.droneseta.model.entity.enums.RoleName;
import udesc.dsw.droneseta.service.*;

import javax.management.InstanceNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.Collections;
import java.util.Set;

@RestController
public class AuthController {

    @Autowired
    Validator validator;

    @Autowired
    ClienteService clienteService;

    @Autowired
    AdminService adminService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/signup/cliente")
    public ResponseEntity<?> createCliente(@RequestBody ClienteDTO clienteDTO) throws ValidatorException {
        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(clienteDTO);

        if (!violations.isEmpty()) {
            throw new ValidatorException(violations);
        }

        if(clienteDTO.getCartaoInfo() != null) {
            Set<ConstraintViolation<CartaoInfoDTO>> violationsCartao = validator.validate(clienteDTO.getCartaoInfo());
            if (!violationsCartao.isEmpty()) {
                throw new ValidatorException(violationsCartao);
            }
        }

        if(clienteDTO.getEndereco() != null) {
            Set<ConstraintViolation<EnderecoDTO>> violationsEndereco = validator.validate(clienteDTO.getEndereco());
            if (!violationsEndereco.isEmpty()) {
                throw new ValidatorException(violationsEndereco);
            }
        }

        Cliente response = clienteService.createCliente(
                new Cliente(clienteDTO),
                new Endereco(clienteDTO.getEndereco()),
                new CartaoInfo(clienteDTO.getCartaoInfo())
        );

        if(response == null){
            throw new DataIntegrityViolationException("Não foi possível registrar o novo usuário no banco de dados");
        }

        return new ResponseEntity<>(new ClienteDTO(response), HttpStatus.CREATED);
    }

    @PostMapping("login/cliente")
    public ResponseEntity<?> loginCliente(@RequestBody LoginDTO loginDTO) throws Exception {
        Usuario usuario = usuarioService.getUsuarioByEmail(loginDTO.getEmail());
        if (usuario == null) {
            throw new InstanceNotFoundException("Não foi encontrado um usuário com o seguinte email: " + loginDTO.getEmail());
        }

        if(!usuario.getRole().equals(RoleName.ROLE_USER)){
            throw new InstanceNotFoundException("Não é possível prosseguir a autenticação pois o usuário informado não possui os privilégios para acessar essa página");
        }

        if (!passwordEncoder.matches(loginDTO.getSenha(), usuario.getSenha())) {
            throw new AccessDeniedException("O Email ou Senha foi informado incorretamente!");
        }

        Cliente response = clienteService.getClienteById(usuario.getId());
        return new ResponseEntity<>(new ClienteDTO(response), HttpStatus.OK);
    }

    @PostMapping("login/admin")
    public ResponseEntity<?> loginAdmin(@RequestBody LoginDTO loginDTO) throws Exception {
        Usuario usuario = usuarioService.getUsuarioByEmail(loginDTO.getEmail());
        if (usuario == null) {
            throw new InstanceNotFoundException("Não foi encontrado um usuário com o seguinte email: " + loginDTO.getEmail());
        }

        if(!usuario.getRole().equals(RoleName.ROLE_ADMIN)){
            throw new InstanceNotFoundException("Não é possível prosseguir a autenticação pois o usuário informado não possui os privilégios para acessar essa página");
        }

        if (!passwordEncoder.matches(loginDTO.getSenha(), usuario.getSenha())) {
            throw new AccessDeniedException("O Email ou Senha foi informado incorretamente!");
        }

        Admin response = adminService.getAdminById(usuario.getId());
        return new ResponseEntity<>(new AdminDTO(response), HttpStatus.OK);
    }

}
