package com.parkit.parkingsystem.dao;

import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.constants.ParkingType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)
public class ParkingSpotDAOTest {

    @Mock
    private DataBaseConfig dataBaseConfig;


    private final ParkingSpotDAO parkingSpotDAO = new ParkingSpotDAO();

    @BeforeEach
    public void setup() {
        parkingSpotDAO.dataBaseConfig = dataBaseConfig;
    }


    @Test
    public void getNextAvailableSlot_KO_Test() throws Exception {
        // GIVEN a parking type
        final ParkingType parkingType = ParkingType.BIKE;

        // AND the expected result if an exception is fired
        final int expected = -1;

        // WHEN
        Mockito.when(dataBaseConfig.getConnection()).thenThrow(new SQLException("Pouf KO"));


        final int result = parkingSpotDAO.getNextAvailableSlot(parkingType);

        //THEN
        Assertions.assertEquals(expected, result);


    }


}
