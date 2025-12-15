package cat.itacademy.s04.t02.n01.fruit_api_h2;

import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.dto.FruitRequest;
import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.dto.FruitResponse;
import cat.itacademy.s04.t02.n01.fruit_api_h2.fruit.repository.FruitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class FruitControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FruitRepository fruitRepository;


    @BeforeEach
    void resetDatabase() {
        fruitRepository.deleteAll();
    }

    @Test
    void addFruit_shouldCreateFruit() throws Exception {
        FruitRequest request = new FruitRequest("Apple", 2);

        mockMvc.perform(post("/fruits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Apple"))
                .andExpect(jsonPath("$.weightInKilos").value(2));
    }

    @Test
    void getAllFruits_shouldReturnList_whenNotEmpty() throws Exception {
        mockMvc.perform(post("/fruits")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new FruitRequest("Apple", 2))));
        mockMvc.perform(post("/fruits")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new FruitRequest("Banana", 1))));

        mockMvc.perform(get("/fruits"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Apple"))
                .andExpect(jsonPath("$[1].name").value("Banana"));
    }

    @Test
    void getAllFruits_shouldReturnError_whenEmpty() throws Exception {
        mockMvc.perform(get("/fruits"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getFruitById_shouldReturnFruit() throws Exception {
        String response = mockMvc.perform(post("/fruits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new FruitRequest("Apple", 2))))
                .andReturn().getResponse().getContentAsString();

        FruitResponse created = objectMapper.readValue(response, FruitResponse.class);

        mockMvc.perform(get("/fruits/" + created.id()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Apple"))
                .andExpect(jsonPath("$.weightInKilos").value(2));
    }

    @Test
    void updateFruit_shouldModifyFruit() throws Exception {
        String response = mockMvc.perform(post("/fruits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new FruitRequest("Apple", 2))))
                .andReturn().getResponse().getContentAsString();

        FruitResponse created = objectMapper.readValue(response, FruitResponse.class);

        FruitRequest updated = new FruitRequest("Apple2", 5);

        mockMvc.perform(put("/fruits/" + created.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Apple2"))
                .andExpect(jsonPath("$.weightInKilos").value(5));
    }

    @Test
    void deleteFruit_shouldRemoveFruit() throws Exception {
        String response = mockMvc.perform(post("/fruits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new FruitRequest("Apple", 2))))
                .andReturn().getResponse().getContentAsString();

        FruitResponse created = objectMapper.readValue(response, FruitResponse.class);

        mockMvc.perform(delete("/fruits/" + created.id()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/fruits/" + created.id()))
                .andExpect(status().isNotFound());
    }
}