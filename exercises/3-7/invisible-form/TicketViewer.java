import javax.swing.*;
import java.awt.*;

/**
 * Exercise 3-7: Vulnerable Ticket Viewer.
 * This class renders ticket descriptions using a JLabel that allows HTML.
 */
public class TicketViewer {

    public void showTicket(String id, String realPriority, String description) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("FastSupport Ticket Viewer - #" + id);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
            frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Metadata Panel
            JPanel metaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel idLabel = new JLabel("Ticket ID: " + id);
            JLabel priorityLabel = new JLabel("Priority: " + realPriority);
            metaPanel.add(idLabel);
            metaPanel.add(new JLabel(" | "));
            metaPanel.add(priorityLabel);

            // Description Area
            JLabel descHeader = new JLabel("Description:");
            descHeader.setAlignmentX(Component.LEFT_ALIGNMENT);

            // VULNERABILITY: This label displays the untrusted description.
            // If the description starts with <html>, it can spoof the UI.
            JLabel descContent = new JLabel(description);
            descContent.setBorder(BorderFactory.createEtchedBorder());
            descContent.setAlignmentX(Component.LEFT_ALIGNMENT);

            // Action Buttons
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton acceptButton = new JButton("Accept Ticket");
            JButton rejectButton = new JButton("Reject Ticket");
            buttonPanel.add(acceptButton);
            buttonPanel.add(rejectButton);

            frame.add(metaPanel);
            frame.add(Box.createVerticalStrut(10));
            frame.add(descHeader);
            frame.add(descContent);
            frame.add(Box.createVerticalStrut(10));
            frame.add(buttonPanel);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
