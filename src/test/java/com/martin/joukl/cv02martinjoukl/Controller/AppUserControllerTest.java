package com.martin.joukl.cv02martinjoukl.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.martin.joukl.cv02martinjoukl.model.AppUser;
import com.martin.joukl.cv02martinjoukl.model.Role;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        expectedAppUser.setUsername("Mao Mao");
        List<AppUser> expectedAppUsers = new LinkedList<>();
        expectedAppUsers.add(expectedAppUser);

        Mockito.when(appUserService.listByActive(true)).thenReturn(expectedAppUsers);

        mockMvc.perform(
                        get("/getAppUser")
                                .accept(MediaType.APPLICATION_JSON)
                                .param("active", "true")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").value(expectedAppUser.getId()))
                .andExpect(jsonPath("[0].username").value(expectedAppUser.getUsername()))
                .andExpect(jsonPath("[0].password").value(expectedAppUser.getPassword()))
                .andExpect(jsonPath("[0].active").value(expectedAppUser.isActive()))
                .andExpect(jsonPath("[0].creationDate").value(expectedAppUser.getCreationDate().toString()));
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    void listAppUser() throws Exception {
        AppUser expectedAppUser = new AppUser();
        expectedAppUser.setId(50);
        expectedAppUser.setRoles(new LinkedList<>());
        expectedAppUser.setActive(false);
        expectedAppUser.setPassword("xHeslo123");
        expectedAppUser.setCreationDate(Date.valueOf("2024-03-19"));
        expectedAppUser.setUsername("Mao Mao");
        Role role = new Role();
        role.setName("Apothecary");
        role.setId(1);
        expectedAppUser.setRoles(new ArrayList<>());
        expectedAppUser.getRoles().add(role);
        List<AppUser> expectedAppUsers = new LinkedList<>();
        expectedAppUsers.add(expectedAppUser);

        Mockito.when(appUserService.listByRoleName(eq("Apothecary"))).thenReturn(expectedAppUsers);

        mockMvc.perform(
                        get("/listAppUser")
                                .accept(MediaType.APPLICATION_JSON)
                                .param("roleName", "Apothecary")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").value(expectedAppUser.getId()))
                .andExpect(jsonPath("[0].username").value(expectedAppUser.getUsername()))
                .andExpect(jsonPath("[0].password").value(expectedAppUser.getPassword()))
                .andExpect(jsonPath("[0].active").value(expectedAppUser.isActive()))
                .andExpect(jsonPath("[0].creationDate").value(expectedAppUser.getCreationDate().toString()))
                .andExpect(jsonPath("[0].roles[0].id").value(expectedAppUser.getRoles().get(0).getId()))
                .andExpect(jsonPath("[0].roles[0].name").value(expectedAppUser.getRoles().get(0).getName()));
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    void createAppUser() throws Exception {
        AppUser expectedAppUser = new AppUser();
        expectedAppUser.setRoles(new LinkedList<>());
        expectedAppUser.setActive(true);
        expectedAppUser.setPassword("xHeslo123");
        expectedAppUser.setCreationDate(Date.valueOf("2024-03-19"));
        expectedAppUser.setUsername("Mao Mao");
        Role role = new Role();
        role.setName("Apothecary");
        role.setId(1);
        expectedAppUser.setRoles(new ArrayList<>());
        expectedAppUser.getRoles().add(role);

        Mockito.when(appUserService.createAppUser(any(AppUser.class))).thenReturn(expectedAppUser);

        ObjectMapper objectMapper = new ObjectMapper();
        String appUserJson = objectMapper.writeValueAsString(expectedAppUser);
        mockMvc.perform(
                        post("/create-app-user")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                                .param("appUser", appUserJson)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("id").value(expectedAppUser.getId()))
                .andExpect(jsonPath("username").value(expectedAppUser.getUsername()))
                .andExpect(jsonPath("password").value(expectedAppUser.getPassword()))
                .andExpect(jsonPath("active").value(expectedAppUser.isActive()))
                .andExpect(jsonPath("creationDate").value(expectedAppUser.getCreationDate().toString()))
                .andExpect(jsonPath("roles[0].id").value(expectedAppUser.getRoles().get(0).getId()))
                .andExpect(jsonPath("roles[0].name").value(expectedAppUser.getRoles().get(0).getName()));
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    void getAppUserById() throws Exception {
        AppUser expectedAppUser = new AppUser();
        expectedAppUser.setRoles(new LinkedList<>());
        expectedAppUser.setActive(true);
        expectedAppUser.setPassword("xHeslo123");
        expectedAppUser.setCreationDate(Date.valueOf("2024-03-19"));
        expectedAppUser.setUsername("Mao Mao");
        Role role = new Role();
        role.setName("Apothecary");
        role.setId(1);
        expectedAppUser.setRoles(new ArrayList<>());
        expectedAppUser.getRoles().add(role);

        Mockito.when(appUserService.getById(1)).thenReturn(expectedAppUser);

        mockMvc.perform(
                        get("/app-user/1")
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("id").value(expectedAppUser.getId()))
                .andExpect(jsonPath("username").value(expectedAppUser.getUsername()))
                .andExpect(jsonPath("password").value(expectedAppUser.getPassword()))
                .andExpect(jsonPath("active").value(expectedAppUser.isActive()))
                .andExpect(jsonPath("creationDate").value(expectedAppUser.getCreationDate().toString()))
                .andExpect(jsonPath("roles[0].id").value(expectedAppUser.getRoles().get(0).getId()))
                .andExpect(jsonPath("roles[0].name").value(expectedAppUser.getRoles().get(0).getName()));
    }


    @Test
    @WithMockUser(username = "user", password = "password")
    void updateAppUser() throws Exception {
        AppUser expectedAppUser = new AppUser();
        expectedAppUser.setRoles(new LinkedList<>());
        expectedAppUser.setActive(true);
        expectedAppUser.setPassword("xHeslo123");
        expectedAppUser.setCreationDate(Date.valueOf("2024-03-19"));
        expectedAppUser.setUsername("Mao Mao");
        Role role = new Role();
        role.setName("Apothecary");
        role.setId(1);
        expectedAppUser.setRoles(new ArrayList<>());
        expectedAppUser.getRoles().add(role);

        Mockito.when(appUserService.updateAppUser(any(AppUser.class))).thenReturn(expectedAppUser);

        ObjectMapper objectMapper = new ObjectMapper();
        String appUserJson = objectMapper.writeValueAsString(expectedAppUser);
        mockMvc.perform(
                        put("/update-app-user")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                                .param("appUser", appUserJson)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("id").value(expectedAppUser.getId()))
                .andExpect(jsonPath("username").value(expectedAppUser.getUsername()))
                .andExpect(jsonPath("password").value(expectedAppUser.getPassword()))
                .andExpect(jsonPath("active").value(expectedAppUser.isActive()))
                .andExpect(jsonPath("creationDate").value(expectedAppUser.getCreationDate().toString()))
                .andExpect(jsonPath("roles[0].id").value(expectedAppUser.getRoles().get(0).getId()))
                .andExpect(jsonPath("roles[0].name").value(expectedAppUser.getRoles().get(0).getName()));
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    void deleteAppUser() throws Exception {
        AppUser expectedAppUser = new AppUser();
        expectedAppUser.setRoles(new LinkedList<>());
        expectedAppUser.setActive(true);
        expectedAppUser.setPassword("xHeslo123");
        expectedAppUser.setCreationDate(Date.valueOf("2024-03-19"));
        expectedAppUser.setUsername("Mao Mao");
        Role role = new Role();
        role.setName("Apothecary");
        role.setId(1);
        expectedAppUser.setRoles(new ArrayList<>());
        expectedAppUser.getRoles().add(role);

        Mockito.when(appUserService.deleteAppUser(expectedAppUser.getId())).thenReturn(expectedAppUser);

        mockMvc.perform(
                        delete("/delete-app-user")
                                .accept(MediaType.APPLICATION_JSON)
                                .param("id", String.valueOf(expectedAppUser.getId()))
                                .with(csrf())
                ).andExpect(status().isOk())
                .andExpect(jsonPath("id").value(expectedAppUser.getId()))
                .andExpect(jsonPath("username").value(expectedAppUser.getUsername()))
                .andExpect(jsonPath("password").value(expectedAppUser.getPassword()))
                .andExpect(jsonPath("active").value(expectedAppUser.isActive()))
                .andExpect(jsonPath("creationDate").value(expectedAppUser.getCreationDate().toString()))
                .andExpect(jsonPath("roles[0].id").value(expectedAppUser.getRoles().get(0).getId()))
                .andExpect(jsonPath("roles[0].name").value(expectedAppUser.getRoles().get(0).getName()));
    }
}