package udesc.dsw.droneseta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import udesc.dsw.droneseta.model.entity.Endereco;
import udesc.dsw.droneseta.repository.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    public Endereco createEndereco(Endereco endereco) {return enderecoRepository.save(endereco);}

    public boolean existsById(Long idEndereco){
        return enderecoRepository.existsById(idEndereco);
    }
}
