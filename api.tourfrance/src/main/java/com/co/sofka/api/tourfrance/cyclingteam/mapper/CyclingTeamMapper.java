package com.co.sofka.api.tourfrance.cyclingteam.mapper;

import com.co.sofka.api.tourfrance.cyclingteam.collection.CyclingTeam;
import com.co.sofka.api.tourfrance.cyclingteam.dto.CyclingTeamDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CyclingTeamMapper {

    public Function<CyclingTeamDTO, CyclingTeam> mapperToCyclingTeam(String id) {
        return cyclingTeamCreate -> {
            var cyclingTeam = new CyclingTeam();
            cyclingTeam.setId(id);
            cyclingTeam.setTeamName(cyclingTeamCreate.getTeamName());
            cyclingTeam.setCodeTeam(cyclingTeamCreate.getCodeTeam());
            cyclingTeam.setTeamLocation(cyclingTeamCreate.getTeamLocation());
            return cyclingTeam;
        };
    }

    public Function<CyclingTeam, CyclingTeamDTO> mapperToCyclingTeamDTO(){
        return cyclingTeamDTO -> new CyclingTeamDTO(
                cyclingTeamDTO.getId(),
                cyclingTeamDTO.getTeamName(),
                cyclingTeamDTO.getCodeTeam(),
                cyclingTeamDTO.getTeamLocation()
        );
    }
}
