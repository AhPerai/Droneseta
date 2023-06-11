package udesc.dsw.droneseta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import udesc.dsw.droneseta.model.entity.CartaoInfo;
import udesc.dsw.droneseta.model.entity.Cliente;
import udesc.dsw.droneseta.model.entity.Endereco;
import udesc.dsw.droneseta.repository.CartaoInfoRepository;
import udesc.dsw.droneseta.repository.ClienteRepository;
import udesc.dsw.droneseta.repository.EnderecoRepository;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    CartaoInfoRepository cartaoInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Cliente createCliente(Cliente newCliente, Endereco endereco, CartaoInfo cartaoInfo) {

        Optional<Endereco> oEndereco =  Optional.empty();
        if(endereco.getId() != null){
            oEndereco = enderecoRepository.findById(endereco.getId());
        }
        if(oEndereco.isPresent()){
            endereco = oEndereco.get();
        }else{
            endereco = enderecoRepository.save(endereco);
        }

        Optional<CartaoInfo> oCartaoInfo =  Optional.empty();
        if(cartaoInfo.getId() !=null){
            oCartaoInfo = cartaoInfoRepository.findById(cartaoInfo.getId());
        }
        if(oCartaoInfo.isPresent()){
            cartaoInfo = oCartaoInfo.get();
        }else{
            cartaoInfo = cartaoInfoRepository.save(cartaoInfo);
        }

        newCliente.setEndereco(endereco);
        newCliente.setCartaoInfo(cartaoInfo);
        newCliente.setSenha(passwordEncoder.encode(newCliente.getSenha()));

        return clienteRepository.save(newCliente);
    }

    public void deleteCliente(Long idCliente) {
        clienteRepository.deleteById(idCliente);
    }

    public boolean existsById(Long idCliente){
        return clienteRepository.existsById(idCliente);
    }

    public Cliente getClienteById(Long idCliente){
        return clienteRepository.findById(idCliente).orElse(null);
    }

    public CartaoInfo getCartaoByCliente(Long idCliente){
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);

        return cliente == null ? null : cliente.getCartaoInfo();
    }

    public void createCartao(CartaoInfo cartaoInfo, Long idCliente) throws DataIntegrityViolationException {
        Cliente cliente = getClienteById(idCliente);

        if(cliente == null){
            throw new DataIntegrityViolationException("O cliente informado não está registrado no sistema");
        }

        cartaoInfo = cartaoInfoRepository.save(cartaoInfo);
        cliente.setCartaoInfo(cartaoInfo);
        clienteRepository.save(cliente);
    }
}
