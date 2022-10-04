package de.dmichel90.restaurant.domain;

import de.dmichel90.restaurant.model.Nationality;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "restaurant")
@Data
@EqualsAndHashCode(callSuper = true)
public class RestaurantEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Nationality nationality;

    @Column
    @Size(max = 5, min = 5)
    private String postalCode;

    @Column
    private String street;

    @Column
    private String houseNumber;
}
