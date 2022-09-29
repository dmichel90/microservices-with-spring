package de.dmichel90.restaurant.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestaurantsApiController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class RestaurantsApiControllerTest {

    @MockBean
    private JwtDecoder jwtDecoder;

    @MockBean
    private RestaurantsApiDelegateImpl restaurantsApiDelegate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @WithMockUser(username = "ben")
    public void getRestaurantsShouldSucceed() throws Exception {
        mockMvc.perform(get("/v1/restaurants")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getRestaurantsNoAuthShouldFail() throws Exception {
        mockMvc.perform(get("/v1/restaurants")).andDo(print()).andExpect(status().isUnauthorized());
    }
}