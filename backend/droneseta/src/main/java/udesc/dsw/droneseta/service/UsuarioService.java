package udesc.dsw.droneseta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import udesc.dsw.droneseta.model.entity.Cliente;
import udesc.dsw.droneseta.model.entity.Usuario;
import udesc.dsw.droneseta.repository.AdminRepository;
import udesc.dsw.droneseta.repository.ClienteRepository;

@Service
public class UsuarioService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AdminRepository adminRepository;

    public Usuario getUsuarioByEmail(String email){
        Cliente cliente = clienteRepository.getClienteByEmail(email);
        if(cliente != null){
            return cliente;
        }

        return adminRepository.getAdminByEmail(email);
    }
}
