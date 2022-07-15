package com.co.sofka.api.tourfrance.ciclist.mapper;

import com.co.sofka.api.tourfrance.ciclist.collection.Ciclist;
import com.co.sofka.api.tourfrance.ciclist.dto.CiclistDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CiclistMapper {
    public Function<CiclistDTO, Ciclist> mapperToCiclist(String id) {
        return ciclistCreate -> {
            var ciclist = new Ciclist();
          ciclist.setId(id);
          ciclist.setNameComplete(ciclistCreate.getNameComplete());
          ciclist.setNumberCompetitor(ciclistCreate.getNumberCompetitor());
          ciclist.setCodeTeam(ciclistCreate.getCodeTeam());
          ciclist.setNacionality(ciclistCreate.getNacionality());
          return ciclist;
        };
    }

    public Function<Ciclist, CiclistDTO> mapperToCiclistDTO() {
        return ciclistDTO -> new CiclistDTO(
                ciclistDTO.getId(),
                ciclistDTO.getNameComplete(),
                ciclistDTO.getNumberCompetitor(),
                ciclistDTO.getCodeTeam(),
                ciclistDTO.getNacionality()
        );
    }
}
