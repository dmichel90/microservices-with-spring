package de.dmichel90.food.repositories;

import de.dmichel90.food.domain.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<DishEntity, String> {
}
