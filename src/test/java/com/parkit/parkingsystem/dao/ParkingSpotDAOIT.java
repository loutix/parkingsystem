package com.parkit.parkingsystem.dao;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ParkingSpotDAOIT {
    private final DataBasePrepareService dataBasePrepareService = new DataBasePrepareService();

    private final ParkingSpotDAO parkingSpotDAO = new ParkingSpotDAO();


    @BeforeEach
    public void setUp() {
        parkingSpotDAO.dataBaseConfig = new DataBaseTestConfig();
        dataBasePrepareService.clearDataBaseEntries();

    }

    @Test
    @DisplayName(("Get the next available slot "))
    public void getNextAvailableSlotIsAvailable() {

        // GIVEN a parking Type
        ParkingType carParkingType = ParkingType.CAR;

        // WHEN the ticket is saved
        final Integer actual = parkingSpotDAO.getNextAvailableSlot(carParkingType);

        // THEN return the minimum available  parking number
        assertEquals((Integer) 1, actual);
    }

    @Test
    @DisplayName(("update Parking spot DAO"))
    public void updateParking() {

        // GIVEN a parking Type
        ParkingType carParkingType = ParkingType.CAR;
        ParkingSpot parkingSpot = new ParkingSpot(1, carParkingType, true);

        // WHEN the ticket is saved
        final boolean actual = parkingSpotDAO.updateParking(parkingSpot);

        // THEN return the minimum available  parking number
        assertTrue(actual);
    }


    @Test
    @DisplayName(("update Parking spot DAO with error"))
    public void updateParkingerror() {

        // GIVEN a parking Type
        ParkingType carParkingType = ParkingType.CAR;
        ParkingSpot parkingSpot = new ParkingSpot(8, carParkingType, true);

        // WHEN the ticket is saved
        final boolean actual = parkingSpotDAO.updateParking(parkingSpot);

        // THEN return the minimum available  parking number
        assertFalse(actual);
    }
}