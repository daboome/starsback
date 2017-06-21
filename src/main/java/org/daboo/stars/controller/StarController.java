package org.daboo.stars.controller;

import org.daboo.stars.domain.dto.StarsCollection;
import org.daboo.stars.domain.entity.Star;
import org.daboo.stars.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StarController {
    @Autowired
    StarService starService;

    @RequestMapping(value = "/stars/all", method = RequestMethod.GET)
    public StarsCollection getAllStars() {
        StarsCollection starsCollection = new StarsCollection();
        starsCollection.setAllStars(starService.getStars());
        return starsCollection;
    }

    @RequestMapping(value = "/stars", method = RequestMethod.POST)
    public StarsCollection saveOrUpdate(@RequestBody Star star) {
        starService.saveOrUpdateStar(star);
        System.out.println(star.toString());
        return getAllStars();
    }

    @RequestMapping(value = "/stars/delete/{id}")
    public StarsCollection deleteStar(@PathVariable Long id) {
        starService.deleteStar(id);
        return getAllStars();
    }
}
