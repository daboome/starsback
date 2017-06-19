package org.daboo.stars.domain.entity;

import lombok.Data;
import org.daboo.stars.domain.enums.StarClass;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by daboo on 6/19/17.
 */
@Data
@Entity
public class Star extends AbstractDomainClass {
    private String starName;
    private String xcoord;
    private String ycoord;
    @Enumerated(EnumType.STRING)
    private StarClass starClass;
    private String discoveredPerson;

    public Star() {
    }

    public Star(String starName, String xcoord, String ycoord, StarClass starClass, String discoveredPerson) {
        this();
        this.starName = starName;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.starClass = starClass;
        this.discoveredPerson = discoveredPerson;
    }
}
