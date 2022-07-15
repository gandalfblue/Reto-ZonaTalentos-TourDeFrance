package com.co.sofka.api.tourfrance.ciclist.routes;

import com.co.sofka.api.tourfrance.ciclist.dto.CiclistDTO;
import com.co.sofka.api.tourfrance.ciclist.usecase.ListCiclistByTeamUseCase;
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

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CiclistGetByCodeTeamRoute {

    @Bean
    @RouterOperation(operation = @Operation(operationId = "getAll", summary = "Get all ciclisy by team", tags = {"Ciclist"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "codeTeam", description = "Ciclist codeTeam")},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid parameters in body supplied"),
                    @ApiResponse(responseCode = "404", description = "Ciclists not found")}))
    public RouterFunction<ServerResponse> getAllCiclistByTeam(ListCiclistByTeamUseCase listCiclistByTeamUseCase){
        return route(GET("/getAllCiclist/{codeTeam}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                listCiclistByTeamUseCase.apply(request.pathVariable("codeTeam")),
                                CiclistDTO.class)));
    }
}