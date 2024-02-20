package com.martin.joukl.cv02martinjoukl.service;

import com.martin.joukl.cv02martinjoukl.model.Ship;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IShipService {
    Ship getShipById(int id) throws Exception;

    List<Ship> listShips();
}
