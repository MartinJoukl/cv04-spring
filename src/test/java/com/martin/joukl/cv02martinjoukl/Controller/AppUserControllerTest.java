package com.martin.joukl.cv02martinjoukl.Controller;

import com.martin.joukl.cv02martinjoukl.model.AppUser;
import com.martin.joukl.cv02martinjoukl.service.AppUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.sql.Date;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AppUserController.class)
@Import(AppUserController.class)
class AppUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppUserService appUserService;


    private String token;
    @Test
    @WithMockUser(username = "user", password = "password")
    void getAppUser() throws Exception {
        AppUser expectedAppUser = new AppUser();
        expectedAppUser.setId(50);
        expectedAppUser.setRoles(new LinkedList<>());
        expectedAppUser.setActive(true);
        expectedAppUser.setPassword("xHeslo123");
        expectedAppUser.setCreationDate(Date.valueOf("2024-03-19"));
        List<AppUser> expectedAppUsers = new LinkedList<>();
        expectedAppUsers.add(expectedAppUser);

        Mockito.when(appUserService.listByActive(true)).thenReturn(expectedAppUsers);

        ResultActions action = mockMvc.perform(
                get("/api/v1/getAppUser")
                        .header("authorization", "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("active", "true")
        ).andExpect(status().isOk());

        System.out.println(action);
    }

    @Test
    void listAppUser() {

    }

    @Test
    void testGetAppUser() {

    }

    @Test
    void createAppUser() {

    }

    @Test
    void updateAppUser() {

    }

    @Test
    void deleteAppUser() {

    }
}