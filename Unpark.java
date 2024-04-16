package carparking;

public class Unpark {
    private ParkingLot parkingLot;

    public Unpark(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void unparkVehicle(String ticketId) {
        parkingLot.unparkVehicle(ticketId);
    }

    public void displayOccupiedSlots(String type) {
        parkingLot.displayOccupiedSlots(type);
    }
}

