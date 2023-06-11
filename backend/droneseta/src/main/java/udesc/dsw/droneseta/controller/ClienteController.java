package udesc.dsw.droneseta.controller;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import udesc.dsw.droneseta.exception.ValidatorException;
import udesc.dsw.droneseta.model.dto.CartaoInfoDTO;
import udesc.dsw.droneseta.model.dto.ClienteDTO;
import udesc.dsw.droneseta.model.entity.CartaoInfo;
import udesc.dsw.droneseta.model.entity.Cliente;
import udesc.dsw.droneseta.service.ClienteService;


@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    Validator validator;

    @DeleteMapping("{idCliente}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long idCliente){
        clienteService.deleteCliente(idCliente);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{idCliente}")
    public ResponseEntity<?> getClienteById(@PathVariable Long idCliente){
        Cliente cliente = clienteService.getClienteById(idCliente);

        if(cliente == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ClienteDTO(cliente), HttpStatus.OK);
    }

    @PostMapping("{idCliente}/cartao")
    public ResponseEntity<?> createCartao(@PathVariable Long idCliente, @RequestBody CartaoInfoDTO cartaoInfoDTO) throws ValidatorException{
        Set<ConstraintViolation<CartaoInfoDTO>> violations = validator.validate(cartaoInfoDTO);

        if(violations.isEmpty()){
            clienteService.createCartao(new CartaoInfo(cartaoInfoDTO), idCliente);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            throw new ValidatorException(violations);
        }
    }

    @GetMapping("cartao/{idCliente}")
    public ResponseEntity<?> getCartaoByCliente(@PathVariable Long idCliente){
        CartaoInfo cartao = clienteService.getCartaoByCliente(idCliente);

        return new ResponseEntity<>(cartao, HttpStatus.OK);
    }

}