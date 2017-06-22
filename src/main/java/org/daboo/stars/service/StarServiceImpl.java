package org.daboo.stars.service;

import org.daboo.stars.domain.entity.Star;
import org.daboo.stars.repo.StarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StarServiceImpl implements StarService {
    @Autowired
    StarRepository starRepository;

    @Override
    public List<Star> getStars() {
        List<Star> stars = new ArrayList<>();
        starRepository.findAll().forEach(stars::add);
        return stars;
    }

    @Override
    public void saveOrUpdateStar(Star star) {
        starRepository.save(star);
    }

    @Override
    public void deleteStar(Long id) {
        starRepository.delete(id);
    }

}
