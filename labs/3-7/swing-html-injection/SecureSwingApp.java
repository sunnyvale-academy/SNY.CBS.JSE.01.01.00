import javax.swing.*;
import java.awt.*;

/**
 * Lab 3-7: Secure Swing Application.
 * Demonstrates how to disable HTML rendering for Swing components.
 */
public class SecureSwingApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Secure Swing App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());

            JLabel instructionLabel = new JLabel("Enter your name:");
            JTextField inputField = new JTextField(20);
            JButton submitButton = new JButton("Update Name");

            // This label is protected against HTML injection.
            JLabel resultLabel = new JLabel("Welcome!");

            // SECURE: Disable HTML rendering for this component.
            // Guideline 3-7: Use "html.disable" client property.
            resultLabel.putClientProperty("html.disable", Boolean.TRUE);

            submitButton.addActionListener(e -> {
                String input = inputField.getText();
                // The renderer will now treat the string as literal text,
                // even if it starts with <html>.
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

            System.out.println("[SECURE] HTML rendering is disabled. Tags will be shown as text.");
        });
    }
}
