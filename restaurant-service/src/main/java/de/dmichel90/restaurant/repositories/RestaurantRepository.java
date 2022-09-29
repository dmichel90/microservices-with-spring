package de.dmichel90.restaurant.repositories;

import de.dmichel90.restaurant.domain.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, String> {
}
