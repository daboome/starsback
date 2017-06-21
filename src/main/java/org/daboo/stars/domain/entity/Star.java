package org.daboo.stars.domain.entity;

import lombok.Data;
import org.daboo.stars.domain.enums.StarType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity
public class Star extends AbstractDomainClass {
    private String starName;
    private String xcoord;
    private String ycoord;
    @Enumerated(EnumType.STRING)
    private StarType starType;
    private String discoveredPerson;

    public Star() {
    }

    public Star(String starName, String xcoord, String ycoord, StarType starType, String discoveredPerson) {
        this();
        this.starName = starName;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.starType = starType;
        this.discoveredPerson = discoveredPerson;
    }
}
