package GameController.utils;

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


    public static JDialog showWinner(String winner) {


        //Loading image for displaying winner.
        ImageIcon image = new ImageIcon("src/main/java/GameController/utils/meme.jpg");
        Image scaledImage = image.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH); // Adjust size as needed
        ImageIcon meme = new ImageIcon(scaledImage);
        JLabel icon = new JLabel(meme);

        // Creating the text to display the winner
        JLabel text = new JLabel(winner);
        text.setFont(new Font("Impact", Font.PLAIN, 24));
        if(winner.startsWith("R")){
        text.setForeground(Color.RED);}
        else{
            text.setForeground(Color.BLUE);
        }
        text.setHorizontalAlignment(SwingConstants.CENTER);


        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(icon, BorderLayout.CENTER);
        panel.add(text, BorderLayout.SOUTH);


        // Create a custom button
        JButton newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font("Calibri", Font.BOLD, 14));
        newGameButton.setFocusPainted(false);



        JOptionPane optionPane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE,
                JOptionPane.DEFAULT_OPTION, null, new Object[] {newGameButton});

        JDialog dialog = optionPane.createDialog(null, "Winner Winner Chicken Dinner!");
        newGameButton.addActionListener(e -> dialog.dispose());


        return dialog;
    }
}
