package carparking;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private String parkingLotId;
    private List<List<Slot>> slots;

    public ParkingLot(String parkingLotId, int nfloors, int noOfSlotsPerFlr) {
        this.parkingLotId = parkingLotId;
        initializeParkingLot(nfloors, noOfSlotsPerFlr);
    }

    private void initializeParkingLot(int nfloors, int noOfSlotsPerFlr) {
        slots = new ArrayList<>();
        for (int i = 0; i < nfloors; i++) {
            slots.add(new ArrayList<>());
            List<Slot> floorSlots = slots.get(i);

            // Add default heavy vehicle slots
            for (int j = 0; j < 6; j++) {
                floorSlots.add(new Slot("truck"));
            }

            // Add default car slots
            for (int j = 6; j < noOfSlotsPerFlr; j++) {
                floorSlots.add(new Slot("car"));
            }
        }
    }

    public String parkVehicle(String type, String regNo, String color) {
        for (int i = 0; i < slots.size(); i++) {
            for (int j = 0; j < slots.get(i).size(); j++) {
                Slot slot = slots.get(i).get(j);
                if (slot.getType().equals(type) && slot.getVehicle() == null) {
                    Vehicle vehicle = new Vehicle(type, regNo, color);
                    slot.setVehicle(vehicle);
                    slot.setTicketId(generateTicketId(i + 1, j + 1));
                    return slot.getTicketId();
                }
            }
        }
        System.out.println("No available slot for " + type);
        return null;
    }

    private String generateTicketId(int flr, int slno) {
        return parkingLotId + "_" + flr + "_" + slno;
    }

    public void unparkVehicle(String ticketId) {
        try {
            String[] extract = ticketId.split("_");
            int flrIdx = Integer.parseInt(extract[1]) - 1;
            int slotIdx = Integer.parseInt(extract[2]) - 1;

            for (int i = 0; i < slots.size(); i++) {
                for (int j = 0; j < slots.get(i).size(); j++) {
                    if (i == flrIdx && j == slotIdx) {
                        Slot slot = slots.get(i).get(j);
                        slot.setVehicle(null);
                        slot.setTicketId(null);
                        System.out.println("Vehicle unparked successfully.");
                        return;
                    }
                }
            }
            System.out.println("Invalid ticket ID or vehicle not found.");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid ticket ID format.");
        }
    }

    public int getNoOfOpenSlots(String type) {
        int count = 0;
        for (List<Slot> floor : slots) {
            for (Slot slot : floor) {
                if (slot.getVehicle() == null && slot.getType().equals(type)) {
                    count++;
                }
            }
        }
        return count;
    }

    public void displayOpenSlots(String type) {
        System.out.println("Available slots for " + type + ":");
        for (int i = 0; i < slots.size(); i++) {
            for (int j = 0; j < slots.get(i).size(); j++) {
                Slot slot = slots.get(i).get(j);
                if (slot.getVehicle() == null && slot.getType().equals(type)) {
                    System.out.println("Floor " + (i + 1) + ", Slot " + (j + 1));
                }
            }
        }
    }

    public void displayOccupiedSlots(String type) {
        System.out.println("Occupied slots for " + type + ":");
        for (int i = 0; i < slots.size(); i++) {
            for (int j = 0; j < slots.get(i).size(); j++) {
                Slot slot = slots.get(i).get(j);
                if (slot.getVehicle() != null && slot.getType().equals(type)) {
                    System.out.println("Floor " + (i + 1) + ", Slot " + (j + 1));
                }
            }
        }
    }
}

class Slot {
    private String type;
    private Vehicle vehicle;
    private String ticketId;

    public Slot(String type) {
        this.type = type;
        this.vehicle = null;
        this.ticketId = null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}

class Vehicle {
    private String type;
    private String registration;
    private String color;

    public Vehicle(String type, String registration, String color) {
        this.type = type;
        this.registration = registration;
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
