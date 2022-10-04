package de.dmichel90.food.services;

import de.dmichel90.food.domain.DishEntity;
import de.dmichel90.food.repositories.DishRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DishServiceTest {

    @InjectMocks
    DishService dishService;

    @Mock
    DishRepository dishRepository;

    @Mock
    ModelMapper modelMapper;

    @Test
    public void testGetAccounts() {
        var dish1 = new DishEntity();
        var dish2 = new DishEntity();
        var dishes = List.of(dish1, dish2);

        when(dishRepository.findAll()).thenReturn(dishes);

        var list = dishService.getDishes();
        Assertions.assertEquals(2, list.size());
        verify(dishRepository, times(1)).findAll();
    }
}
