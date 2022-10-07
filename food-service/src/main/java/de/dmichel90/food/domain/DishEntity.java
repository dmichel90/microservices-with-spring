package de.dmichel90.food.domain;

import de.dmichel90.food.model.FoodType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "dish")
@Data
@EqualsAndHashCode(callSuper = true)
public class DishEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private BigDecimal price;

    @Column
    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    private Set<DishRestaurantEntity> restaurantSet;
}
