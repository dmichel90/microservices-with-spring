package de.dmichel90.food.domain;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "dish")
@Data
@EqualsAndHashCode(callSuper = true)
public class DishRestaurantEntity extends BaseEntity {

    @Column
    private String restaurantId;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "dish_id")
    private DishEntity dish;
}
