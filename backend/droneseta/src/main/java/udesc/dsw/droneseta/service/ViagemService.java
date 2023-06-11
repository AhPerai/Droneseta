package udesc.dsw.droneseta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import udesc.dsw.droneseta.model.entity.Viagem;
import udesc.dsw.droneseta.repository.ViagemRepository;

@Service
public class ViagemService {

    @Autowired
    ViagemRepository viagemRepository;

    public Viagem getUltimaViagem(){
        return viagemRepository.getFirstViagemOrderByHorarioChegadaDesc();
    }

    public Viagem saveViagem(Viagem viagem){
        return viagemRepository.save(viagem);
    }

    public int getNumeroViagemNoMes(){
        return  viagemRepository.getNumeroViagemNoMes();
    }

}