package org.daboo.stars.domain.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by daboo on 5/5/17.
 */
@Data
@MappedSuperclass
public class AbstractDomainClass {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    @Version
    Integer version;
}
