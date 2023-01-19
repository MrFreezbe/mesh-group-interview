package ru.meshgroup.interview.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;
import ru.meshgroup.interview.AbstractTest;
import ru.meshgroup.interview.domain.User;
import ru.meshgroup.interview.model.EmailDataDto;
import ru.meshgroup.interview.repository.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class EmailCreationTest extends AbstractTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void testEmailCreation() throws Exception {
        User user = userRepository.save(new User());
        MockHttpServletRequestBuilder requestBuilder = post("/v1/email/{userId}/emails", user.getId())
                .contentType(MediaType.APPLICATION_JSON.getMediaType())
                .content(new ObjectMapper().writeValueAsString(new EmailDataDto("test@test.com")));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"email\":\"test@test.com\"}"));
    }

}
