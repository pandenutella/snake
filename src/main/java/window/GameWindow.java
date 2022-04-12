package window;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.JFrame;
import java.awt.Container;

@EqualsAndHashCode(callSuper = true)
@Data
public class GameWindow extends JFrame {

    private GameScreen gameScreen;

    public void initialize() {
        Container contentPane = getContentPane();
        contentPane.add(gameScreen);

        setTitle("Snake - pandenutella");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setResizable(false);
        setLocationRelativeTo(null);

        pack();
        setVisible(true);
    }
}
