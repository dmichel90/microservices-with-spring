package de.dmichel90.restaurant.services;

import de.dmichel90.restaurant.domain.RestaurantEntity;
import de.dmichel90.restaurant.repositories.RestaurantRepository;
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
public class RestaurantServiceTest {

    @InjectMocks
    RestaurantService restaurantService;

    @Mock
    RestaurantRepository restaurantRepository;

    @Mock
    ModelMapper modelMapper;

    @Test
    public void testGetAccounts() {
        var restaurant1 = new RestaurantEntity();
        var restaurant2 = new RestaurantEntity();
        var restaurantList = List.of(restaurant1, restaurant2);

        when(restaurantRepository.findAll()).thenReturn(restaurantList);

        var list = restaurantService.getRestaurants();
        Assertions.assertEquals(2, list.size());
        verify(restaurantRepository, times(1)).findAll();
    }
}
