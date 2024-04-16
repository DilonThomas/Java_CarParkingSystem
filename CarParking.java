package carparking;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarParking extends JFrame implements ActionListener {
    private ParkVehicleFrame parkVehicleFrame;
    private UnparkVehicleFrame unparkVehicleFrame;
    private CheckAvailabilityFrame checkAvailabilityFrame;

    public CarParking() {
        // Create GUI components
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout()); // Use BorderLayout for better organization

        // Add title label
        JLabel titleLabel = new JLabel("Car Parking System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font style
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10)); // Adjusted layout with spacing

        // Create buttons with smaller size
        JButton parkButton = new JButton("Park Vehicle");
        parkButton.setPreferredSize(new Dimension(100, 20)); // Adjust button size
        parkButton.addActionListener(this);

        JButton unparkButton = new JButton("Unpark Vehicle");
        unparkButton.setPreferredSize(new Dimension(100, 20)); // Adjust button size
        unparkButton.addActionListener(this);

        JButton checkSlotsButton = new JButton("Check Availability");
        checkSlotsButton.setPreferredSize(new Dimension(100, 20)); // Adjust button size
        checkSlotsButton.addActionListener(this);

        // Add buttons to panel
        buttonPanel.add(parkButton);
        buttonPanel.add(unparkButton);
        buttonPanel.add(checkSlotsButton);

        // Add button panel to main panel
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Added padding

        // Set up frame
        setTitle("Car Parking System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setSize(500, 550); // Increased frame size
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Park Vehicle":
                if (parkVehicleFrame == null) {
                    parkVehicleFrame = new ParkVehicleFrame();
                } else {
                    parkVehicleFrame.setVisible(true);
                }
                break;
            case "Unpark Vehicle":
                if (unparkVehicleFrame == null) {
                    unparkVehicleFrame = new UnparkVehicleFrame();
                } else {
                    unparkVehicleFrame.setVisible(true);
                }
                break;
            case "Check Availability":
                if (checkAvailabilityFrame == null) {
                    checkAvailabilityFrame = new CheckAvailabilityFrame();
                } else {
                    checkAvailabilityFrame.setVisible(true);
                }
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CarParking::new);
    }
}

class ParkVehicleFrame extends JFrame implements ActionListener {
    private JTextField regNoField;
    private JTextField colorField;
    private JComboBox<String> typeComboBox;
    private JLabel statusLabel;
    private JTextField ticketField; // Added ticket ID field

    public ParkVehicleFrame() {
        // Create GUI components
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 2, 10, 10)); // Adjusted layout with spacing

        JLabel regNoLabel = new JLabel("Registration No:");
        regNoField = new JTextField();
        JLabel colorLabel = new JLabel("Color:");
        colorField = new JTextField();
        JLabel typeLabel = new JLabel("Type:");
        String[] types = {"Heavy", "Car", "Motor"};
        typeComboBox = new JComboBox<>(types);
        JButton parkButton = new JButton("Park Vehicle");
        parkButton.addActionListener(this);

        statusLabel = new JLabel("");

        // Add components to main panel with padding
        mainPanel.add(regNoLabel);
        mainPanel.add(regNoField);
        mainPanel.add(colorLabel);
        mainPanel.add(colorField);
        mainPanel.add(typeLabel);
        mainPanel.add(typeComboBox);
        mainPanel.add(parkButton);
        mainPanel.add(statusLabel);

        JLabel ticketLabel = new JLabel("Ticket ID:");
        ticketField = new JTextField();
        ticketField.setEditable(false); // Make ticket ID field read-only
        mainPanel.add(ticketLabel);
        mainPanel.add(ticketField);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Added padding

        // Set up frame
        setTitle("Park Vehicle");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Generate a unique ticket ID
        String ticketID = generateTicketID();
        // Display the ticket ID
        ticketField.setText(ticketID);
        statusLabel.setText("Vehicle parked successfully. Ticket ID: " + ticketID);
    }

    // Method to generate a random ticket ID
    private String generateTicketID() {
        Random random = new Random();
        int randomNumber = random.nextInt(10000); // Generate a random number
        String prefix = "TICKET"; // Fixed prefix for the ticket ID
        return prefix + randomNumber;
    }
}

class UnparkVehicleFrame extends JFrame implements ActionListener {
    private JTextField ticketIdField;
    private JLabel statusLabel;

    public UnparkVehicleFrame() {
        // Create GUI components
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2, 10, 10)); // Adjusted layout with spacing

        JLabel ticketIdLabel = new JLabel("Ticket ID:");
        ticketIdField = new JTextField();
        JButton unparkButton = new JButton("Unpark Vehicle");
        unparkButton.addActionListener(this);

        statusLabel = new JLabel("");

        // Add components to main panel with padding
        mainPanel.add(ticketIdLabel);
        mainPanel.add(ticketIdField);
        mainPanel.add(unparkButton);
        mainPanel.add(statusLabel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Added padding

        // Set up frame
        setTitle("Unpark Vehicle");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get user inputs and process unpark vehicle action
    }
}

class CheckAvailabilityFrame extends JFrame implements ActionListener {
    private final int NUM_FLOORS = 3;
    private final int SPACES_PER_FLOOR = 30;
    private int currentFloor = 0;

    private JButton[][] parkingButtons;
    private JPanel[] floorPanels;
    private JPanel mainPanel;

    public CheckAvailabilityFrame() {
        // Create GUI components
        mainPanel = new JPanel(new BorderLayout());

        // Create panels for each floor
        floorPanels = new JPanel[NUM_FLOORS];
        for (int i = 0; i < NUM_FLOORS; i++) {
            floorPanels[i] = createFloorPanel(i);
        }

        // Create "Next" and "Previous" buttons
        JPanel navigationPanel = new JPanel(new FlowLayout());
        JButton prevButton = new JButton("Previous");
        prevButton.addActionListener(this);
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        navigationPanel.add(prevButton);
        navigationPanel.add(nextButton);

        // Add navigation panel to main panel
        mainPanel.add(navigationPanel, BorderLayout.SOUTH);

        // Add the initial floor panel to the main panel
        mainPanel.add(floorPanels[currentFloor], BorderLayout.CENTER);

        // Set up frame
        setTitle("Parking Lot Availability");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(500, 500); // Increased frame size
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createFloorPanel(int floor) {
        JPanel panel = new JPanel(new GridLayout(5, 6, 5, 5));
        parkingButtons = new JButton[5][6];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                JButton button = new JButton("P" + (floor * SPACES_PER_FLOOR + i * 6 + j + 1));
                button.setPreferredSize(new Dimension(50, 50)); // Set button size
                button.addActionListener(this);
                parkingButtons[i][j] = button;
                panel.add(button);
            }
        }

        panel.setBorder(BorderFactory.createTitledBorder("Floor " + (floor + 1)));
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Previous")) {
            currentFloor = (currentFloor == 0) ? NUM_FLOORS - 1 : currentFloor - 1;
        } else if (e.getActionCommand().equals("Next")) {
            currentFloor = (currentFloor + 1) % NUM_FLOORS;
        }
        // Remove the current floor panel from the main panel
        mainPanel.remove(floorPanels[currentFloor]);
        // Add the new floor panel to the main panel
        mainPanel.add(floorPanels[currentFloor], BorderLayout.CENTER);
        // Repaint the main panel to reflect the changes
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}


