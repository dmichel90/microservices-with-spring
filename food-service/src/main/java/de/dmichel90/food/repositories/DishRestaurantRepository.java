package de.dmichel90.food.repositories;

import de.dmichel90.food.domain.DishRestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRestaurantRepository extends JpaRepository<DishRestaurantEntity, String> {

    List<DishRestaurantEntity> findAllByRestaurantId(String restaurantId);
}
