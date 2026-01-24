import javax.swing.*;
import java.awt.*;

/**
 * Lab 3-7: Vulnerable Swing Application.
 * Demonstrates automatic HTML rendering of untrusted input.
 */
public class VulnerableSwingApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Vulnerable Swing App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());

            JLabel instructionLabel = new JLabel("Enter your name:");
            JTextField inputField = new JTextField(20);
            JButton submitButton = new JButton("Update Name");

            // This label will display the untrusted input.
            JLabel resultLabel = new JLabel("Welcome!");

            submitButton.addActionListener(e -> {
                String input = inputField.getText();
                // VULNERABILITY: Setting text that might start with <html>
                // directly triggers Swing's HTML renderer.
                resultLabel.setText(input);

                // Ensure the UI updates its layout and resizes the window
                frame.revalidate();
                frame.repaint();
                frame.pack();
            });

            frame.add(instructionLabel);
            frame.add(inputField);
            frame.add(submitButton);
            frame.add(resultLabel);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            System.out.println("[VULNERABLE] Try these payloads:");
            System.out.println("   1. UI Spoofing: <html><font color='red' size='7'>HACKED</font></html>");
            System.out.println(
                    "   2. Image Leak:  <html><img src='https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png'></html>");
        });
    }
}
