package com.todo_app.back_end.todo_app_back_end.todos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class TodoQueriesInTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void listTodos() throws Exception {

        // String expectedResponse = "{\"data\": {\"findAllTodos\": [{\"id\":
        // \"4\",\"title\": \"Todo 1 For Aicha\",\"createdDate\": \"2023-06-03
        // 22:22:35.017\",\"userId\": \"2\"},{\"id\": \"5\",\"title\": \"Todo 2 For
        // Aicha\",\"createdDate\": \"2023-06-03 22:22:39.042\",\"userId\": \"2\"}]}}";

        mockMvc.perform(post("/graphql")

                .content("{\"query\":\"{ findAllTodos { id title createdDate userId } }\"}")

                .contentType(MediaType.APPLICATION_JSON)

                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())

                // .andExpect(content().json(expectedResponse))

                .andReturn();

    }

}
