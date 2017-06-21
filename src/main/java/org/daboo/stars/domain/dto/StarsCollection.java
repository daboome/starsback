package org.daboo.stars.domain.dto;

import lombok.Data;
import org.daboo.stars.domain.entity.Star;

import java.util.List;

@Data
public class StarsCollection {
    List<Star> allStars;
}
