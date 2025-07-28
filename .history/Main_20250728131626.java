import javax.swing.*;
import java.util.*;

// Room class
class Room {
    int number;
    String type;
    boolean isClean;

    public Room(int number, String type, boolean isClean) {
        this.number = number;
        this.type = type;
        this.isClean = isClean;
    }
}

// Booking class
class Booking {
    int id;
    String user;
    String hotelName;
    Room room;
    String checkInDate;
    String checkOutDate;
    String status;
    String paymentStatus;

    public Booking(int id, String user, String hotelName, Room room, String checkInDate, String checkOutDate, String status, String paymentStatus) {
        this.id = id;
        this.user = user;
        this.hotelName = hotelName;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;
        this.paymentStatus = paymentStatus;
    }
}

class UserAccount {
    String email;
    String password;
    String role; // "Admin" or "User"

    public UserAccount(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}

public class HotelBookingGUI extends JFrame {
    static List<Booking> bookings = new ArrayList<>();
    static List<Room> roomList = new ArrayList<>();
    static List<UserAccount> accounts = new ArrayList<>();
    static int nextBookingId = 205;

    private JTextField emailField;
    private JPasswordField passwordField;

    public HotelBookingGUI() {
        setTitle("Hotel Booking Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(30, 30, 80, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(100, 30, 200, 25);
        add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 70, 80, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 70, 200, 25);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(120, 110, 100, 30);
        add(loginButton);

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            boolean found = false;
            for (UserAccount acc : accounts) {
                if (acc.email.equals(email) && acc.password.equals(password)) {
                    found = true;
                    JOptionPane.showMessageDialog(this, acc.role + " login successful");

                    if (acc.role.equalsIgnoreCase("Admin")) {
                        new AdminMenu().setVisible(true);
                    } else {
                        new UserMenu(acc.email).setVisible(true);
                    }

                    dispose();
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    class AdminMenu extends JFrame {
        public AdminMenu() {
            setTitle("Admin Menu");
            setSize(450, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);

            JButton addRoomBtn = new JButton("Add New Room");
            JButton viewRoomsBtn = new JButton("View All Rooms");
            JButton viewBookingsBtn = new JButton("View All Bookings");
            JButton viewHousekeepingBtn = new JButton("Rooms Needing Housekeeping");
            JButton cleanRoomBtn = new JButton("Mark Room as Clean");
            JButton exitBtn = new JButton("Exit");

            addRoomBtn.setBounds(120, 20, 200, 30);
            viewRoomsBtn.setBounds(120, 60, 200, 30);
            viewBookingsBtn.setBounds(120, 100, 200, 30);
            viewHousekeepingBtn.setBounds(120, 140, 200, 30);
            cleanRoomBtn.setBounds(120, 180, 200, 30);
            exitBtn.setBounds(120, 220, 200, 30);

            add(addRoomBtn);
            add(viewRoomsBtn);
            add(viewBookingsBtn);
            add(viewHousekeepingBtn);
            add(cleanRoomBtn);
            add(exitBtn);

            addRoomBtn.addActionListener(e -> {
                JTextField numberField = new JTextField();
                JTextField typeField = new JTextField();

                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.add(new JLabel("Room Number:"));
                panel.add(numberField);
                panel.add(new JLabel("Room Type (Single/Double):"));
                panel.add(typeField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Add New Room", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        int number = Integer.parseInt(numberField.getText());
                        String type = typeField.getText();
                        roomList.add(new Room(number, type, true));
                        JOptionPane.showMessageDialog(this, "Room added successfully.");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Invalid room number.");
                    }
                }
            });

            viewRoomsBtn.addActionListener(e -> {
                if (roomList.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No rooms added yet.");
                    return;
                }
                StringBuilder sb = new StringBuilder();
                for (Room r : roomList) {
                    sb.append("Room #: ").append(r.number).append(" | Type: ").append(r.type).append(" | Clean: ").append(r.isClean ? "Yes" : "No").append("\n");
                }
                JOptionPane.showMessageDialog(this, sb.toString());
            });

            viewBookingsBtn.addActionListener(e -> {
                StringBuilder sb = new StringBuilder();
                for (Booking b : bookings) {
                    sb.append("ID: ").append(b.id).append(", Hotel: ").append(b.hotelName).append(", Room: ").append(b.room.number).append(", Status: ").append(b.status).append("\n");
                }
                JOptionPane.showMessageDialog(this, sb.length() > 0 ? sb.toString() : "No bookings found.");
            });

            viewHousekeepingBtn.addActionListener(e -> {
                StringBuilder sb = new StringBuilder();
                for (Room r : roomList) {
                    if (!r.isClean) {
                        sb.append("Room #").append(r.number).append(" (").append(r.type).append(") needs cleaning\n");
                    }
                }
                JOptionPane.showMessageDialog(this, sb.length() > 0 ? sb.toString() : "All rooms are clean.");
            });

            cleanRoomBtn.addActionListener(e -> {
                String input = JOptionPane.showInputDialog("Enter Room Number to mark as clean:");
                try {
                    int roomNum = Integer.parseInt(input);
                    boolean found = false;
                    for (Room r : roomList) {
                        if (r.number == roomNum && !r.isClean) {
                            r.isClean = true;
                            found = true;
                            JOptionPane.showMessageDialog(this, "Room #" + roomNum + " marked clean.");
                            break;
                        }
                    }
                    if (!found) {
                        JOptionPane.showMessageDialog(this, "Room not found or already clean.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input.");
                }
            });

            exitBtn.addActionListener(e -> System.exit(0));
            setLocationRelativeTo(null);
        }
    }

    class UserMenu extends JFrame {
        String userEmail;

        public UserMenu(String email) {
            this.userEmail = email;
            setTitle("Customer Menu");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);

            JButton bookRoomBtn = new JButton("Book a Room");
            bookRoomBtn.setBounds(100, 10, 200, 30);
            add(bookRoomBtn);

            JButton viewBookingsBtn = new JButton("View My Bookings");
            viewBookingsBtn.setBounds(100, 50, 200, 30);
            add(viewBookingsBtn);

            JButton paymentStatusBtn = new JButton("View Payment Status");
            paymentStatusBtn.setBounds(100, 90, 200, 30);
            add(paymentStatusBtn);

            JButton markPaidBtn = new JButton("Mark Payment as Paid");
            markPaidBtn.setBounds(100, 130, 200, 30);
            add(markPaidBtn);

            JButton exitBtn = new JButton("Exit");
            exitBtn.setBounds(100, 170, 200, 30);
            add(exitBtn);

            add(bookRoomBtn);
            add(viewBookingsBtn);
            add(paymentStatusBtn);
            add(exitBtn);



            markPaidBtn.addActionListener(e -> {
                String input = JOptionPane.showInputDialog(this, "Enter Booking ID to mark as paid:");
                try {
                    int id = Integer.parseInt(input);
                    boolean found = false;
                    for (Booking b : bookings) {
                        if (b.id == id && b.user.equalsIgnoreCase("User")) {
                            b.paymentStatus = "Paid";
                            JOptionPane.showMessageDialog(this, "Payment marked as Paid.");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        JOptionPane.showMessageDialog(this, "Booking ID not found.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input.");
                }
            });

            bookRoomBtn.addActionListener(e -> {
                List<Room> availableRooms = new ArrayList<>();
                for (Room r : roomList) {
                    if (r.isClean) availableRooms.add(r);
                }

                if (availableRooms.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "All rooms are dirty or unavailable.");
                    return;
                }

                String[] roomOptions = new String[availableRooms.size()];
                for (int i = 0; i < availableRooms.size(); i++) {
                    roomOptions[i] = "Room " + availableRooms.get(i).number + " (" + availableRooms.get(i).type + ")";
                }

                JComboBox<String> roomDropdown = new JComboBox<>(roomOptions);
                JTextField hotelField = new JTextField();
                JTextField checkInField = new JTextField();
                JTextField checkOutField = new JTextField();

                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.add(new JLabel("Select Room:"));
                panel.add(roomDropdown);
                panel.add(new JLabel("Hotel Name:"));
                panel.add(hotelField);
                panel.add(new JLabel("Check-In Date (YYYY-MM-DD):"));
                panel.add(checkInField);
                panel.add(new JLabel("Check-Out Date (YYYY-MM-DD):"));
                panel.add(checkOutField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Book a Room", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    int selectedIndex = roomDropdown.getSelectedIndex();
                    Room selectedRoom = availableRooms.get(selectedIndex);

                    bookings.add(new Booking(nextBookingId++, userEmail, hotelField.getText(), selectedRoom,
                            checkInField.getText(), checkOutField.getText(), "Pending", "Unpaid"));
                    selectedRoom.isClean = false;
                    JOptionPane.showMessageDialog(this, "Room booked successfully!");
                }
            });

            viewBookingsBtn.addActionListener(e -> {
                StringBuilder sb = new StringBuilder();
                for (Booking b : bookings) {
                    if (b.user.equalsIgnoreCase(userEmail)) {
                        sb.append("Booking ID: ").append(b.id)
                                .append("\nHotel: ").append(b.hotelName)
                                .append("\nRoom: ").append(b.room.number).append(" (").append(b.room.type).append(")")
                                .append("\nCheck-In: ").append(b.checkInDate)
                                .append("\nCheck-Out: ").append(b.checkOutDate)
                                .append("\nStatus: ").append(b.status).append("\n\n");
                    }
                }
                JOptionPane.showMessageDialog(this, sb.length() > 0 ? sb.toString() : "No bookings found.");
            });

            paymentStatusBtn.addActionListener(e -> {
                StringBuilder sb = new StringBuilder();
                for (Booking b : bookings) {
                    if (b.user.equalsIgnoreCase(userEmail)) {
                        sb.append("Booking ID: ").append(b.id)
                                .append(" - Payment: ").append(b.paymentStatus).append("\n");
                    }
                }
                JOptionPane.showMessageDialog(this, sb.length() > 0 ? sb.toString() : "No payment status found.");
            });

            exitBtn.addActionListener(e -> System.exit(0));
            setLocationRelativeTo(null);
        }
    }

    public static void initializeData() {
        roomList.add(new Room(101, "Single", true));
        roomList.add(new Room(102, "Double", true));
        roomList.add(new Room(103, "Suite", true));

        accounts.add(new UserAccount("admin@hotel.com", "admin123", "Admin"));
        accounts.add(new UserAccount("user@hotel.com", "user123", "User"));
    }

    public static void Main(String[] args) {
        initializeData();
        SwingUtilities.invokeLater(HotelBookingGUI::new);
    }
}

