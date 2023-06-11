package udesc.dsw.droneseta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import udesc.dsw.droneseta.model.entity.CartaoInfo;
import udesc.dsw.droneseta.repository.CartaoInfoRepository;

@Service
public class CartaoInfoService {

    @Autowired
    private CartaoInfoRepository cartaoInfoRepository;

    public CartaoInfo createCartaoInfo(CartaoInfo cartao) {
        return cartaoInfoRepository.save(cartao);
    }
}
