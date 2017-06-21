package org.daboo.stars.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public class AbstractDomainClass {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    @Version
    Integer version;
}
