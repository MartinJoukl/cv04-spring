package com.martin.joukl.cv02martinjoukl.Controller;

import com.martin.joukl.cv02martinjoukl.model.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class AppUserController {

    private AppUserRepository appUserRepository;

    public AppUserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/getAppUser")
    public List<AppUser> getAppUser(@RequestParam boolean active) throws Exception {
        return appUserRepository.findByActive(active);
    }

    @GetMapping("/listAppUser")
    public List<AppUser> listAppUser(@RequestParam String roleName) throws Exception {
        List<AppUser> a = appUserRepository.listByRoleName(roleName);
        return appUserRepository.listByRoleName(roleName);
    }

    @GetMapping("/app-user/{id}")
    public AppUser getAppUser(@PathVariable int id) {
        return appUserRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/create-app-user")
    public AppUser createAppUser(AppUser appUser) {
        Date creationDate = new Date(System.currentTimeMillis());
        appUser.setCreationDate(creationDate);
        appUser.setUpdateDate(null);
        appUser.setId(0);

        AppUser created = appUserRepository.save(appUser);
        return created;
    }

    @PutMapping("/update-app-user")
    public AppUser updateAppUser(AppUser appUser) {
        AppUser fromDao = appUserRepository.findById(appUser.getId()).orElseThrow(NotFoundException::new);
        appUser.setCreationDate(fromDao.getCreationDate());
        appUser.setUpdateDate(new Date(System.currentTimeMillis()));

        AppUser updated = appUserRepository.save(appUser);
        return updated;
    }

    @DeleteMapping("/delete-app-user")
    public AppUser deleteAppUser(int id) {
        Optional<AppUser> fromDao = appUserRepository.findById(id);
        if (fromDao.isEmpty()) {
            return null;
        }
        AppUser user = fromDao.get();
        appUserRepository.delete(user);
        user.setTasks(null);
        user.setRoles(null);
        return user;
    }
}
