package org.daboo.stars.repo;

import org.daboo.stars.domain.entity.Star;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by daboo on 6/19/17.
 */
public interface StarRepository extends JpaRepository<Star, Long>{
}
