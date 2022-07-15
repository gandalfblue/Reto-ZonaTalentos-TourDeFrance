package com.co.sofka.api.tourfrance.ciclist.collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Document(collection = "ciclist")
public class Ciclist implements Serializable {

    private static final long serialVersionUID = 6081299697391889314L;
    @Id
    private String id;

    @NotBlank
    private String nameComplete;

    @NotBlank
    @Size(min = 1, max = 3)
    @Indexed(unique = true)
    private String numberCompetitor;

    @NotBlank
    private String codeTeam;

    @NotBlank
    private String nacionality;
}
