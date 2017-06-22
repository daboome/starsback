package org.daboo.stars.service;

import org.daboo.stars.domain.entity.Star;

import java.util.List;

public interface StarService {
    List<Star> getStars();

    void saveOrUpdateStar(Star star);

    void deleteStar(Long id);

}
