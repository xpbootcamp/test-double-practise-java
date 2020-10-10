package com.tw.practise;

import com.tw.practise.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private UserService userService;


    @Test
    public void should_request_register_api_success_when_user_register_given_valid_info() throws Exception {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        String userName = "lisa";
        multiValueMap.add("userName", userName);
        String password = "lisa123";
        multiValueMap.add("password", password);
        String telephone = "13829763456";
        multiValueMap.add("telephone", telephone);
        when(userService.register(userName, password, telephone)).thenReturn(1L);

        mockMvc.perform(post("/register")
                .params(multiValueMap))
                .andExpect(status().isCreated());
    }
}
