package com.martin.joukl.cv02martinjoukl.Controller;

import com.martin.joukl.cv02martinjoukl.model.Ship;
import com.martin.joukl.cv02martinjoukl.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NavalController {

    private ShipService shipService;

    public NavalController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping ("getShip")
    public Ship getShip(@RequestParam int id) throws Exception {
        return shipService.getShipById(id);
    }

    @GetMapping ("listShips")
    public List<Ship> getShip() {
        return shipService.listShips();
    }
}
