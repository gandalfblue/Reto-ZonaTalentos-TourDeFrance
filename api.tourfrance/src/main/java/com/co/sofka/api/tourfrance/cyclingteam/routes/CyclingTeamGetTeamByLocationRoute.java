package com.co.sofka.api.tourfrance.cyclingteam.routes;

import com.co.sofka.api.tourfrance.cyclingteam.dto.CyclingTeamDTO;
import com.co.sofka.api.tourfrance.cyclingteam.usecase.ListTeamByLocationUseCase;
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
public class CyclingTeamGetTeamByLocationRoute {

    @Bean
    @RouterOperation(operation = @Operation(operationId = "getAll", summary = "Get all teams by country", tags = {"CyclingTeam"},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "teamLocation", description = "Ciclist teamLocation")},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Invalid parameters in body supplied"),
                    @ApiResponse(responseCode = "404", description = "Ciclist not found")}))
    public RouterFunction<ServerResponse> listTeamByLocation(ListTeamByLocationUseCase listTeamByLocationUseCase){
        return route(GET("/listTeam/{teamLocation}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                listTeamByLocationUseCase.apply(request.pathVariable("teamLocation")),
                                CyclingTeamDTO.class)));
    }
}
