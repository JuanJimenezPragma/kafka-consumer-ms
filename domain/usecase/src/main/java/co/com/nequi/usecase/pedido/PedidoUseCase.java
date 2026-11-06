package co.com.nequi.usecase.pedido;

import co.com.nequi.model.pedido.Pedido;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PedidoUseCase {

    public Mono<Void> printPedido(Pedido pedido){
        return Mono.empty();
    }
}
