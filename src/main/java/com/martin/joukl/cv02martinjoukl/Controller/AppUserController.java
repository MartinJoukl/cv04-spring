package com.martin.joukl.cv02martinjoukl.Controller;

import com.martin.joukl.cv02martinjoukl.model.*;
import com.martin.joukl.cv02martinjoukl.service.AppUserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/getAppUser") //PAST!
    public List<AppUser> getAppUser(@RequestParam boolean active) {
        return appUserService.listByActive(active);
    }

    @GetMapping("/listAppUser")
    public List<AppUser> listAppUser(@RequestParam String roleName) {
        return appUserService.listByRoleName(roleName);
    }

    @GetMapping("/app-user/{id}")
    public AppUser getAppUser(@PathVariable int id) {
        return appUserService.getById(id);
    }

    @PostMapping("/create-app-user")
    public AppUser createAppUser(AppUser appUser) {
        return appUserService.createAppUser(appUser);
    }

    @PutMapping("/update-app-user")
    public AppUser updateAppUser(AppUser appUser) {
        return appUserService.updateAppUser(appUser);
    }

    @DeleteMapping("/delete-app-user")
    public AppUser deleteAppUser(int id) {
        return appUserService.deleteAppUser(id);
    }
}
