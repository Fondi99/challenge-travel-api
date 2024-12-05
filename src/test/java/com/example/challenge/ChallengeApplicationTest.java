package com.example.challenge;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ChallengeApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddStation() throws Exception {
        mockMvc.perform(put("/stations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Station1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ok"));
    }

    @Test
    public void testAddPath() throws Exception {
        mockMvc.perform(put("/stations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Station1\"}"))
                .andExpect(status().isOk());
        mockMvc.perform(put("/stations/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Station2\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(put("/paths/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"source_id\": 1, \"destination_id\": 2, \"cost\": 10.5}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ok"));
    }

    @Test
    public void testGetCheapestPath() throws Exception {
        mockMvc.perform(put("/stations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Station1\"}"))
                .andExpect(status().isOk());
        mockMvc.perform(put("/stations/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Station2\"}"))
                .andExpect(status().isOk());
        mockMvc.perform(put("/stations/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Station3\"}"))
                .andExpect(status().isOk());
        mockMvc.perform(put("/paths/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"source_id\": 1, \"destination_id\": 2, \"cost\": 5.0}"))
                .andExpect(status().isOk());
        mockMvc.perform(put("/paths/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"source_id\": 2, \"destination_id\": 3, \"cost\": 10.0}"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/paths/1/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.path").isArray())
                .andExpect(jsonPath("$.path[0]").value(1))
                .andExpect(jsonPath("$.path[1]").value(2))
                .andExpect(jsonPath("$.path[2]").value(3))
                .andExpect(jsonPath("$.cost").value(15.0));
    }

    @Test
    public void testGetCheapestPath_NoPath() throws Exception {
        mockMvc.perform(put("/stations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Station1\"}"))
                .andExpect(status().isOk());
        mockMvc.perform(put("/stations/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Station2\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/paths/1/2"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("No path exists between the given stations."));
    }
}
