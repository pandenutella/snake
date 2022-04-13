package framework.display;

import framework.control.ControllerManager;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.JFrame;
import java.awt.Container;

@EqualsAndHashCode(callSuper = true)
@Data
public class GameWindow extends JFrame {

    private GameScreen gameScreen;
    private String gameName;
    private ControllerManager controllerManager;

    public void initialize() {
        Container contentPane = getContentPane();
        contentPane.add(gameScreen);

        setTitle(gameName);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        addKeyListener(controllerManager);

        pack();
        setVisible(true);
    }
}
