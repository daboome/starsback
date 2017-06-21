package org.daboo.stars.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public class AbstractDomainClass {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    @JsonIgnore
    @Version
    Integer version;
}
