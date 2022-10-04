package de.dmichel90.food.api;

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

@WebMvcTest(DishesApiController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class DishesApiControllerTest {

    @MockBean
    private JwtDecoder jwtDecoder;

    @MockBean
    private DishesApiDelegateImpl dishesApiDelegate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @WithMockUser(username = "ben")
    public void getDishesShouldSucceed() throws Exception {
        mockMvc.perform(get("/v1/dishes")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getDishesNoAuthShouldFail() throws Exception {
        mockMvc.perform(get("/v1/restaurants")).andDo(print()).andExpect(status().isUnauthorized());
    }
}