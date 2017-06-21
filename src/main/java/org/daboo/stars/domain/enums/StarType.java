package org.daboo.stars.domain.enums;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.daboo.stars.domain.dto.StarTypeSerializer;

@JsonSerialize(using = StarTypeSerializer.class)
public enum StarType {
    BLUE("BLUE", "#b0e0e6"),
    WHITE_BLUE("WHITE_BLUE", "#f0f8ff"),
    WHITE("WHITE", "#ffffff"),
    YELLOW_WHITE("YELLOW_WHITE","#fcf8a1"),
    YELLOW("YELLOW", "#ffd700"),
    ORANGE("ORANGE", "#ffa500"),
    RED("RED", "#ff7373"),;

    private String value;

    private String color;

    StarType(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return this.value;
    }

}
