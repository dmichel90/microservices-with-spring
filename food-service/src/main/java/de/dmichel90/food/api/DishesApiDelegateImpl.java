package de.dmichel90.food.api;

import de.dmichel90.food.model.Dish;
import de.dmichel90.food.services.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DishesApiDelegateImpl implements DishesApiDelegate {

    private final DishService dishService;

    public DishesApiDelegateImpl(DishService dishService) {
        this.dishService = dishService;
    }

    @Override
    public ResponseEntity<Dish> createDish(Dish dish) {
        return ResponseEntity.ok(dishService.create(dish));
    }

    @Override
    public ResponseEntity<Void> deleteDishForRestaurant(String id, String restaurantId) {
        dishService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<Dish>> getDishes() {
        return ResponseEntity.ok(dishService.getDishes());
    }

    @Override
    public ResponseEntity<List<Dish>> getDishesByRestaurant(String restaurantId) {
        return ResponseEntity.ok(dishService.getDishesByRestaurant(restaurantId));
    }

    @Override
    public ResponseEntity<Dish> updateDish(Dish dish) {
        return ResponseEntity.ok(dishService.update(dish));
    }
}
