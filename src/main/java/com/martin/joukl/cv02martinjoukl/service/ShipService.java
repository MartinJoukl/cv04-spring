package com.martin.joukl.cv02martinjoukl.service;

import com.martin.joukl.cv02martinjoukl.model.Ship;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShipService implements IShipService{
    private final Map<Integer,Ship> shipMap;

    public ShipService() {
        shipMap = Map.of(
                1, new Ship(1, "Mikasa", "Battleship"),
                2, new Ship(2, "Fubuki", "Destroyer"),
                3, new Ship(3, "Lexington", "Carrier")
                );
    }

    public Ship getShipById(int id) throws Exception {
        if(!shipMap.containsKey(id)){
            throw new Exception("Ship was not found by id!");
        }
        return shipMap.get(id);
    }

    public List<Ship> listShips() {
        return shipMap.values().stream().toList();
    }
}
