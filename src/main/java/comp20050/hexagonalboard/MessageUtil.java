package comp20050.hexagonalboard;

import javax.swing.*;
import java.awt.*;

public class MessageUtil {
    /**
     * Method to create a custom error message dialog for invalid cell placement.
     *
     * @return dialog : JDialog instance displaying the custom error message.
     */
    public static JDialog showErrorMessage() {
        JPanel panel = new JPanel();

        // Create a custom message label
        JLabel messageLabel = new JLabel("Invalid Cell Placement.");
        messageLabel.setFont(new Font("Calibri", Font.BOLD, 16));

        // Create a custom button
        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Calibri", Font.BOLD, 14));
        okButton.setFocusPainted(false);

        JOptionPane optionPane = new JOptionPane(messageLabel, JOptionPane.ERROR_MESSAGE,
                JOptionPane.DEFAULT_OPTION, null, new Object[] {okButton});

        JDialog dialog = optionPane.createDialog(panel, "Invalid Move");
        okButton.addActionListener(e -> dialog.dispose());

        return dialog;
    }
}
