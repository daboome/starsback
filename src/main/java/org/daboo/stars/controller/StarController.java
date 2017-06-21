package org.daboo.stars.controller;

import org.daboo.stars.domain.dto.StarsCollection;
import org.daboo.stars.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StarController {
    @Autowired
    StarService starService;

    @RequestMapping("/stars/all")
    public StarsCollection getAllStars() {
        StarsCollection starsCollection = new StarsCollection();
        starsCollection.setAllStars(starService.getStars());
        return starsCollection;
    }
}
