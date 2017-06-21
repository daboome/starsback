package org.daboo.stars.repo;

import org.daboo.stars.domain.entity.Star;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarRepository extends JpaRepository<Star, Long>{
}
