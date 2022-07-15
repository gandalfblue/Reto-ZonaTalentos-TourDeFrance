package com.co.sofka.api.tourfrance.ciclist.routes;

import com.co.sofka.api.tourfrance.ciclist.dto.CiclistDTO;
import com.co.sofka.api.tourfrance.ciclist.usecase.UpdateCiclistUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CiclistUpdateRoute {

    @Bean
    @RouterOperation(beanClass = UpdateCiclistUseCase.class, beanMethod = "createCiclist",
            operation = @Operation(operationId = "update", summary = "Update Ciclist", tags = {"Ciclist"},
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Successful operation"),
                            @ApiResponse(responseCode = "400", description = "Invalid parameters in body supplied")}))
    public RouterFunction<ServerResponse> updateCiclist(UpdateCiclistUseCase updateCiclistUseCase) {
        Function<CiclistDTO, Mono<ServerResponse>> updateCiclist = CiclistDTO -> updateCiclistUseCase.createCiclist(CiclistDTO)
                .flatMap(serverResponse -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(serverResponse));
        return route(PUT("/updateCiclist").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CiclistDTO.class)
                        .flatMap(updateCiclist));
    }
}
