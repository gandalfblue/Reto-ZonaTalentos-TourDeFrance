package com.co.sofka.api.tourfrance.ciclist.routes;

import com.co.sofka.api.tourfrance.ciclist.usecase.DeleteCiclistUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CiclistDeleteRoute {

    @Bean
    @RouterOperation(operation = @Operation(operationId = "delete", summary = "Delete ciclist by Id", tags = {"Ciclist"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "ciclist id")},
            responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters in body supplied"),
            @ApiResponse(responseCode = "404", description = "Ciclist not found")}))
    public RouterFunction<ServerResponse> deleteCiclist(DeleteCiclistUseCase deleteCiclistUseCase){
        return route(
                DELETE("/deleteCiclist/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                deleteCiclistUseCase.apply(request.pathVariable("id")),
                                Void.class)));
    }
}
