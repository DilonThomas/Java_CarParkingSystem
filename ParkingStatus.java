package carparking;

public class ParkingStatus {
    private ParkingLot parkingLot;

    public ParkingStatus(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }


    public void displayOpenSlots(String type) {
        parkingLot.displayOpenSlots(type);
    }

    public void displayOccupiedSlots(String type) {
        parkingLot.displayOccupiedSlots(type);
    }
}
