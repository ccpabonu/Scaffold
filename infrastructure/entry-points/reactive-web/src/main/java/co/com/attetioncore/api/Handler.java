package co.com.attetioncore.api;

import co.com.attetioncore.api.dto.request.Data;
import co.com.attetioncore.api.dto.response.DataRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class Handler {

    public Mono<ServerResponse> listenPOSTsuma (ServerRequest request) {
        return request.bodyToMono(Data.class)
                .doOnNext(data -> {log.info("Numero a Sumar: {}, {}",data.getNumber1(),data.getNumber2());})
                .map(data -> DataRes.builder()
                        .number(data.getNumber1() + data.getNumber2())
                        .build())
                .flatMap(dataRes -> ServerResponse.ok().bodyValue(dataRes));
    }
}
