# Hotel Management System 🏨

A comprehensive desktop application built with Java Swing and MySQL for managing daily hotel operations. This system simplifies customer registration, room management, booking handling, and automated bill generation.

## 🌟 Features

* **Interactive Dashboard:** Real-time overview with the current count of available rooms.
* **Customer Management:** Add, update, view, and delete customer records. Includes built-in validation for Sri Lankan phone numbers and email addresses.
* **Room Management:** Manage hotel rooms (Single, Double, Suite) with dynamic pricing and status tracking (Available/Occupied).
* **Booking System:** * Seamless booking process linking registered customers to available rooms.
    * Interactive date selection using `JCalendar`.
    * Automatic validation for check-in and check-out dates.
    * Auto-updates room status to 'Occupied' upon successful booking.
* **Automated Billing:** Instantly generates a printable receipt calculating total days and the final amount upon booking confirmation.
* **Reporting:** View a complete history of all bookings made through the system.

## 🛠️ Tech Stack & Architecture

* **Language:** Java (JDK 8 or higher)
* **GUI Framework:** Java Swing
* **Database:** MySQL
* **Database Connectivity:** JDBC
* **External Libraries:** `JCalendar` (for date picking)
* **Architecture:** MVC (Model-View-Controller) combined with the DAO (Data Access Object) design pattern for clean, maintainable code.

## 📁 Project Structure

```text
src/
├── controller/    # Contains business logic (CustomerController, RoomController, etc.)
├── dao/           # Data Access Objects for direct database interactions
├── db/            # Database connection configuration (DBConnection)
├── model/         # Data models/entities (Booking, ComboItem)
└── view/          # User Interfaces (Dashboard, BookingForm, BillView, etc.)
