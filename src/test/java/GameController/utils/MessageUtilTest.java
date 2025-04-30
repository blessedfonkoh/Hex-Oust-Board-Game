package GameController.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class MessageUtilTest {
    @BeforeEach
    void suppressMessages() {
        MessageUtil.suppressMessages = true;
    }

    @AfterEach
    void resetSuppressMessages() {
        MessageUtil.suppressMessages = false;
    }

    @Test
    void testShowErrorMessage() {
        JDialog dialog = MessageUtil.showErrorMessage();
        assertNotNull(dialog);
        assertEquals(0, dialog.getContentPane().getComponentCount());
    }

    @Test
    void testShowWinner() {
        JDialog dialog = MessageUtil.showWinner("RED WINS");
        assertNotNull(dialog);
        assertEquals(0, dialog.getContentPane().getComponentCount());
    }
}