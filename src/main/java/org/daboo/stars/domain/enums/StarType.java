package org.daboo.stars.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StarType {

    BLUE("BLUE", "#b0e0e6"),
    WHITE_BLUE("WHITE_BLUE", "#f0f8ff"),
    WHITE("WHITE", "#ffffff"),
    YELLOW_WHITE("YELLOW_WHITE","#fcf8a1"),
    YELLOW("YELLOW", "#ffd700"),
    ORANGE("ORANGE", "#ffa500"),
    RED("RED", "#ff7373");

    @JsonProperty("value")
    private String value;

    @JsonProperty("color")
    private String color;

    StarType(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public String getColor() {
        return this.color;
    }

    @JsonCreator
    public static StarType fromNode(JsonNode node) {
        if (!node.has("value"))
            return null;

        String value = node.get("value").asText();

        return StarType.valueOf(value);
    }


}
