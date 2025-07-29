# 🏨 Hotel Booking Management System

A **Hotel Booking Management System** built using **Java (JDK 23)** and **Swing (GUI)**.  
This desktop application allows **Admins** to manage hotel rooms and bookings, while **Users** can book rooms, track payments, and view their reservations.

---

## ⚙️ Features

### 👨‍💼 **Admin Panel**
- Add new rooms with details like **Room Number** and **Type (Single/Double/Suite)**.
- View all rooms with their **availability and cleaning status**.
- Mark rooms as **clean** after checkout.
- View all hotel bookings with **status and payment details**.

### 🙋 **User Panel**
- **Book rooms** from available and clean rooms.
- **View personal bookings** with check-in/check-out dates.
- **Track payment status** (Paid/Unpaid).
- Mark bookings as **Paid**.

---

## 🧠 **Data Management**
- Data stored in **ArrayLists** for:
  - Rooms
  - Bookings
  - Users (Admin & Customer accounts)
- Default login accounts for Admin and User.

---

## 💻 **Interface**
- Simple and responsive **Swing-based GUI**.
- **Dialogs (JOptionPane)** for interactive input and status updates.
- Separate **Admin** and **User menus** for role-specific actions.

---

## 📁 **File Structure**
hotel-booking-system/
│
├── HotelBookingGUI.java # Main application entry point
├── README.md # Documentation
└── assets/ # (Optional) Screenshots or icons

---

## 🧑‍💻 **Technologies Used**
- **Java 23** – Core language  
- **Swing** – GUI components and event handling  
- **OOP Concepts** – Classes, Objects, Encapsulation, and Inheritance  
- **Collections Framework** – ArrayLists for dynamic data storage  

---

## 🔧 **How to Use**
1. Clone or download the repository.
2. Open the project in **IntelliJ IDEA**, **Eclipse**, or any Java IDE.
3. Run the `HotelBookingGUI.java` file.

### **Default Logins:**
- **Admin:**  
  Email: `admin@hotel.com`  
  Password: `admin123`
  
- **User:**  
  Email: `user@hotel.com`  
  Password: `user123`

---

## 📌 **Notes**
- Data is **not persistent** (no database); all data is cleared when the application is closed.
- Add rooms before booking to test the system properly.

---

## 🌐 **Platform Compatibility**
Tested on:
- Windows 10/11 ✅
- Linux (Ubuntu) ✅
- macOS ✅

---

## 📄 **License**
This project is for **educational and personal use**. Feel free to modify and enhance.

---
