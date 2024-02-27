package com.martin.joukl.cv02martinjoukl.Controller;

import com.martin.joukl.cv02martinjoukl.model.AppUser;
import com.martin.joukl.cv02martinjoukl.model.AppUserRepository;
import com.martin.joukl.cv02martinjoukl.model.Ship;
import com.martin.joukl.cv02martinjoukl.service.ShipService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NavalController {

    private ShipService shipService;
    private AppUserRepository appUserRepository;

    public NavalController(ShipService shipService, AppUserRepository appUserRepository) {
        this.shipService = shipService;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping ("getShip")
    public Ship getShip(@RequestParam int id) throws Exception {
        return shipService.getShipById(id);
    }

    @GetMapping ("listShips")
    public List<Ship> getShip() {
        return shipService.listShips();
    }

    @GetMapping ("getAppUser")
    public List<AppUser> getAppUser(@RequestParam boolean active) throws Exception {
        return appUserRepository.findByActive(active);
    }

    @GetMapping ("listAppUser")
    public List<AppUser> listAppUser(@RequestParam String roleName) throws Exception {
        List<AppUser> a = appUserRepository.listByRoleName(roleName);
        return appUserRepository.listByRoleName(roleName);
    }
}
