package com.co.sofka.api.tourfrance.cyclingteam.routes;

import com.co.sofka.api.tourfrance.cyclingteam.dto.CyclingTeamDTO;
import com.co.sofka.api.tourfrance.cyclingteam.usecase.CyclingTeamListUseCase;
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
public class CyclingTeamGetAllRoute {

    @Bean
    @RouterOperation(beanClass = CyclingTeamListUseCase.class, beanMethod = "get", operation = @Operation(operationId = "GetAll", summary = "Get All Cycling Team", tags = {
            "CyclingTeam"}, responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Ciclists not found")}))
    public RouterFunction<ServerResponse> getAllTeam(CyclingTeamListUseCase cyclingTeamListUseCase){
        return route(GET("/getAllTeam"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(cyclingTeamListUseCase.get(),
                                CyclingTeamDTO.class)));
    }
}