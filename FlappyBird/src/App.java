import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {
    private JButton startButton;

    public App() {
        setTitle("Flappy Bird");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        // Membuat panel utama
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Membuat label untuk judul "Flappy Bird"
        JLabel titleLabel = new JLabel("Flappy Bird");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Membuat tombol "Start"
        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Menutup jendela MainMenu
                dispose();

                // Membuka JFrame game FlappyBird
                JFrame gameFrame = new JFrame("Flappy Bird");
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setSize(360, 640);
                gameFrame.setLocationRelativeTo(null);
                gameFrame.setResizable(false);

                FlappyBird flappyBird = new FlappyBird();
                gameFrame.add(flappyBird);
                gameFrame.pack();
                gameFrame.setVisible(true);
            }
        });

        // Menambahkan komponen ke panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 10, 20);
        mainPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(10, 20, 20, 20);
        mainPanel.add(startButton, gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }
}