package com.parkit.parkingsystem.dao;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class TicketDAOTest {


    private final DataBasePrepareService dataBasePrepareService = new DataBasePrepareService();
    private final TicketDAO ticketDAO = new TicketDAO();


    @BeforeEach
    public void setUpTest() {
        ticketDAO.dataBaseConfig = new DataBaseTestConfig();
        dataBasePrepareService.clearDataBaseEntries();
    }


    @Test
    @DisplayName(("Save a new ticket"))
    public void saveTicket() {
        // GIVEN a new ticket is created
        Ticket expected = new Ticket();
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
        expected.setParkingSpot(parkingSpot);
        expected.setVehicleRegNumber("ABCDEF");
        expected.setPrice(0.0);
        expected.setInTime(new Date(2023, 12, 01, 20, 00, 00));
        expected.setOutTime(null);

        // WHEN the ticket is saved
        ticketDAO.saveTicket(expected);

        // THEN the saved ticket can be found
        final Ticket actual = ticketDAO.getTicket(expected.getVehicleRegNumber());

        // AND the saved is equal to the expected ticket
        assertEquals(actual.getPrice(), expected.getPrice(), "Price are not equals");
        assertEquals(actual.getParkingSpot(), expected.getParkingSpot(), "ParkingSpot are not equals");
        assertEquals(actual.getVehicleRegNumber(), expected.getVehicleRegNumber(), "VehicleRegNumber are not equals");
        assertEquals(actual.getInTime().getTime(), expected.getInTime().getTime(), "InTime are not equals");
        assertNull(actual.getOutTime());
    }

    @Test
    @DisplayName(("Get a saved ticket"))
    public void getTicket() {
        // GIVEN a saved ticket
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
        Ticket expected = new Ticket();
        expected.setId(1);
        expected.setParkingSpot(parkingSpot);
        expected.setVehicleRegNumber("ABCDEF");
        expected.setPrice(0.0);
        expected.setInTime(new Date(2022, 11, 10, 20, 30, 40));
        expected.setOutTime(new Date(2023, 12, 11, 22, 33, 44));

        ticketDAO.saveTicket(expected);

        // WHEN the ticket is given
        final Ticket actual = ticketDAO.getTicket(expected.getVehicleRegNumber());

        // then the saved is equal to the expected ticket
        assertEquals(expected.getId(), actual.getId(), "Id are not equals");
        assertEquals(expected.getPrice(), actual.getPrice(), "Price are not equals");
        assertEquals(expected.getParkingSpot(), actual.getParkingSpot(), "ParkingSpot are not equals");
        assertEquals(expected.getVehicleRegNumber(), actual.getVehicleRegNumber(), "VehicleRegNumber are not equals");
        assertEquals(expected.getInTime().getTime(), actual.getInTime().getTime(), "InTime are not equals");
        assertEquals(expected.getOutTime().getTime(), actual.getOutTime().getTime(), "OutTime are not equals");
    }

    @Test
    @DisplayName(("Update a ticket"))
    public void updateTicket() {

        // GIVEN a saved ticket
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
        Ticket ticketSaved = new Ticket();
        ticketSaved.setId(1);
        ticketSaved.setParkingSpot(parkingSpot);
        ticketSaved.setVehicleRegNumber("ABCDEF");
        ticketSaved.setPrice(0.0);
        ticketSaved.setInTime(new Date(2022, 11, 10, 20, 30, 40));
        ticketSaved.setOutTime(new Date(2023, 12, 11, 22, 33, 44));

        ticketDAO.saveTicket(ticketSaved);

        final Ticket actual = ticketDAO.getTicket(ticketSaved.getVehicleRegNumber());

        actual.setPrice(10.00);
        actual.setOutTime(new Date(2024, 01, 02, 23, 43, 54));


        // WHEN the ticket is given
        ticketDAO.updateTicket(actual);
        final Ticket expected = ticketDAO.getTicket(actual.getVehicleRegNumber());

        // then the saved is equal to the expected ticket
        assertEquals(expected.getId(), actual.getId(), "Id are not equals");
        assertEquals(expected.getPrice(), actual.getPrice(), "Price are not equals");
        assertEquals(expected.getParkingSpot(), actual.getParkingSpot(), "ParkingSpot are not equals");
        assertEquals(expected.getVehicleRegNumber(), actual.getVehicleRegNumber(), "VehicleRegNumber are not equals");
        assertEquals(expected.getInTime().getTime(), actual.getInTime().getTime(), "InTime are not equals");
        assertEquals(expected.getOutTime().getTime(), actual.getOutTime().getTime(), "OutTime are not equals");
    }

    @Test
    @DisplayName(("Get number of ticket"))
    public void getNbTicket() {
        // GIVEN a new ticket is created
        Ticket expected = new Ticket();
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
        expected.setParkingSpot(parkingSpot);
        expected.setVehicleRegNumber("ABCDEF");
        expected.setPrice(0.0);
        expected.setInTime(new Date(2023, 12, 01, 20, 00, 00));
        expected.setOutTime(null);

        // WHEN the ticket is saved
        ticketDAO.saveTicket(expected);

        // THEN the saved ticket can be found
        Integer actual = ticketDAO.getNbTicket(expected);

        // AND the getNbTicket is equal to the expected number
        assertEquals(Integer.valueOf(1), actual);
    }


}