package org.nasa.mars.rovers.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("web")
class SimulationControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void should_run_simulation() throws Exception {
        mvc.perform(post("/simulation").contentType(TEXT_PLAIN).content("5 5\n1 2 N\nLMLMLMLMM"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("1 3 N"));
    }

    @Test
    void should_return_bad_request() throws Exception {
        mvc.perform(post("/simulation").contentType(TEXT_PLAIN).content("5 5\n1 2 N\nO"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}