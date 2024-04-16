package carparking;
import java.util.ArrayList;
import java.util.List;
import carparking.*;

public class Park {
    private ParkingLot parkingLot;

    public Park(String parkingLotId, int nfloors, int noOfSlotsPerFlr) {
        parkingLot = new ParkingLot(parkingLotId, nfloors, noOfSlotsPerFlr);
    }

    public String parkVehicle(String type, String regNo, String color) {
        return parkingLot.parkVehicle(type, regNo, color);
    }

    public int getNoOfOpenSlots(String type) {
        return parkingLot.getNoOfOpenSlots(type);
    }

    public void displayOpenSlots(String type) {
        parkingLot.displayOpenSlots(type);
    }
}
