package com.martin.joukl.cv02martinjoukl.service;

import com.martin.joukl.cv02martinjoukl.model.AppUser;
import com.martin.joukl.cv02martinjoukl.model.AppUserRepository;
import com.martin.joukl.cv02martinjoukl.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    private AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser createAppUser(AppUser appUser) {
        Date creationDate = new Date(System.currentTimeMillis());
        appUser.setCreationDate(creationDate);
        appUser.setUpdateDate(null);
        appUser.setId(0);

        return appUserRepository.save(appUser);
    }

    public AppUser getById(int id){
        return appUserRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<AppUser> listByRoleName(String roleName){
        return appUserRepository.listByRoleName(roleName);
    }

    public List<AppUser> listByActive(boolean active){
        return appUserRepository.findByActive(active);
    }

    public AppUser updateAppUser(AppUser appUser) {
        AppUser fromDao = appUserRepository.findById(appUser.getId()).orElseThrow(NotFoundException::new);
        appUser.setCreationDate(fromDao.getCreationDate());
        appUser.setUpdateDate(new Date(System.currentTimeMillis()));

        return appUserRepository.save(appUser);
    }

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
