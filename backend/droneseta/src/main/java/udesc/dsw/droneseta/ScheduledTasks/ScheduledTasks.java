package udesc.dsw.droneseta.ScheduledTasks;

import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import udesc.dsw.droneseta.model.entity.Pedido;
import udesc.dsw.droneseta.model.entity.Viagem;
import udesc.dsw.droneseta.service.PedidoService;
import udesc.dsw.droneseta.service.ViagemService;


@Component
public class ScheduledTasks {

    @Autowired
    PedidoService pedidoService;

    @Autowired
    ViagemService viagemService;

    @Scheduled(fixedRate = 60000)
    public void entregarPedidos(){
        System.out.println("\n\n\nVerificando Pedidos Pendentes\n");
        // Verifica as viagens já feitas, e atualiza para "finalizadas" se já passou uma hora
        List<Pedido> pedidos = pedidoService.getPedidosPendentes();

        Viagem ultimaViagem = viagemService.getUltimaViagem();

        if((ultimaViagem == null || ultimaViagem.getHorarioChegada().isBefore(LocalDateTime.now()))
                && !pedidos.isEmpty()){
            List<Pedido> pedidosViagem = new ArrayList<>();
            int capacidade = 10;

            int index = 0;
            while(capacidade > 0 && index < pedidos.size()){
                Pedido pedido = pedidos.get(index);
                int produtosParaEntrega = pedido.getQuantidadeAEntregar();

                if(produtosParaEntrega <= capacidade){
                    capacidade = capacidade - produtosParaEntrega;
                    pedido.setQuantidadeAEntregar(0);
                    pedido.setEntregue(true);
                    pedidoService.savePedido(pedido);
                } else {
                    pedido.setQuantidadeAEntregar(produtosParaEntrega - capacidade);
                    pedidoService.savePedido(pedido);
                    capacidade = 0;
                }

                pedidosViagem.add(pedido);
                index++;
            }

            Viagem novaViagem = new Viagem(pedidosViagem, 10 - capacidade);
            viagemService.saveViagem(novaViagem);
        }
    }
}
