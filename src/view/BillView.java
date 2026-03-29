package view;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;

public class BillView {

    public BillView(String customerName, String roomDetails, String checkIn, String checkOut, long days, double totalAmount) {
        JFrame frame = new JFrame("Booking Receipt");
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JTextArea billArea = new JTextArea();
        billArea.setEditable(false);
        billArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        String billHeader =
                "==========================================\n" +
                        "            HOTEL VENUSHA                 \n" +
                        "          Booking Receipt                 \n" +
                        "==========================================\n\n";

        String billBody =
                " Customer Name : " + customerName + "\n" +
                        " Room Details  : " + roomDetails + "\n" +
                        " Check-In Date : " + checkIn + "\n" +
                        " Check-Out Date: " + checkOut + "\n" +
                        " Total Days    : " + days + " days\n" +
                        "------------------------------------------\n" +
                        " TOTAL AMOUNT  : Rs. " + totalAmount + "\n" +
                        "==========================================\n\n" +
                        "        Thank you for choosing us!        \n";

        billArea.setText(billHeader + billBody);

        JScrollPane scrollPane = new JScrollPane(billArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton printBtn = new JButton("Print Bill");
        JButton closeBtn = new JButton("Close");

        printBtn.addActionListener(e -> {
            try {
                billArea.print();
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(frame, "Printer Error: " + ex.getMessage());
            }
        });

        closeBtn.addActionListener(e -> frame.dispose());

        bottomPanel.add(printBtn);
        bottomPanel.add(closeBtn);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}