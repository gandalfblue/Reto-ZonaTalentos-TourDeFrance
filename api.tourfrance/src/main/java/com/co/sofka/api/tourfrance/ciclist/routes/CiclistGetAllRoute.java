package com.co.sofka.api.tourfrance.ciclist.routes;

import com.co.sofka.api.tourfrance.ciclist.dto.CiclistDTO;
import com.co.sofka.api.tourfrance.ciclist.usecase.CiclistListUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CiclistGetAllRoute {

    @Bean
    @RouterOperation(beanClass = CiclistListUseCase.class, beanMethod = "get", operation = @Operation(operationId = "GetAll", summary = "Get All Ciclists", tags = {
            "Ciclist"}, responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Ciclists not found")}))
    public RouterFunction<ServerResponse> getAll(CiclistListUseCase ciclistListUseCase) {
        return route(GET("/getAllCiclist"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(ciclistListUseCase.get(),
                                CiclistDTO.class)));
    }
}
