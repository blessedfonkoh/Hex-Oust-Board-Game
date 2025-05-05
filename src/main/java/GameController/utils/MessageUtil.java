package GameController.utils;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.Objects;

public class MessageUtil {

    public static boolean suppressMessages = false;

    public static JDialog showErrorMessage() {
        if (suppressMessages) {
            return new JDialog(); // return dummy dialog without setting up anything
        }

        JPanel panel = new JPanel();

        // Create a custom message label
        JLabel messageLabel = new JLabel("Invalid Cell Placement.");
        messageLabel.setFont(new Font("Calibri", Font.BOLD, 16));

        // Create a custom button
        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Calibri", Font.BOLD, 14));
        okButton.setFocusPainted(false);

        JOptionPane optionPane = new JOptionPane(messageLabel, JOptionPane.ERROR_MESSAGE,
                JOptionPane.DEFAULT_OPTION, null, new Object[]{okButton});

        JDialog dialog = optionPane.createDialog(panel, "Invalid Move");
        okButton.addActionListener(e -> dialog.dispose());

        return dialog;
    }


    public static JDialog showWinner(String winner) {
        if (suppressMessages) {
            return new JDialog(); // Return dummy dialog without setting up anything
        }

        // Loading image for displaying winner.
        ImageIcon image = new ImageIcon(Objects.requireNonNull(MessageUtil.class.getResource("/meme.jpg")));
        Image scaledImage = image.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH); // Adjust size as needed
        JPanel panel = getPanel(winner, scaledImage);

        // Create a custom button
        JButton newGameButton = new JButton("NEW GAME");
        newGameButton.setFont(new Font("Arial", Font.BOLD, 14));
        newGameButton.setFocusPainted(false);
        newGameButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        JOptionPane optionPane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE,
                JOptionPane.DEFAULT_OPTION, null, new Object[]{newGameButton});

        JDialog dialog = optionPane.createDialog(null, "Winner Winner Chicken Dinner!");
        newGameButton.addActionListener(e -> dialog.dispose());

        return dialog;
    }

    private static JPanel getPanel(String winner, Image scaledImage) {
        ImageIcon meme = new ImageIcon(scaledImage);
        JLabel icon = new JLabel(meme);

        JLabel text = new JLabel(winner);
        text.setFont(new Font("Impact", Font.PLAIN, 24));

        if (winner.startsWith("R")) {
            text.setForeground(Color.RED);
        } else {
            text.setForeground(Color.BLUE);
        }
        text.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(icon, BorderLayout.CENTER);
        panel.add(text, BorderLayout.NORTH);
        return panel;
    }
}
