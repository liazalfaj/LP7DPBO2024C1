import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    // image attributes
    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    // player
    Player player;

    // pipes attributes
    ArrayList<Pipe> pipes;
    Timer gameLoop;
    Timer pipesCoolDown;
    int gravity = 1;
    boolean gameOver = false;

    // score
    int score = 0;
    JLabel scoreLabel;

    // constructors
    public FlappyBird() {
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setFocusable(true);
        addKeyListener(this);

        // load images
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        player = new Player(frameWidth / 8, frameHeight / 2, 34, 24, birdImage);
        pipes = new ArrayList<>();

        pipesCoolDown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) { // Only place pipes if game is not over
                    placePipes();
                }
            }
        });
        pipesCoolDown.start();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();

        // Membuat label skor
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(10, 10, 100, 30);
        add(scoreLabel);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);
        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }

        //jika game over
        if (gameOver) {
            //menulis tulisan game over
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Game Over", frameWidth / 2 - 100, frameHeight / 2 - 30);

            // Tambahkan tulisan "Press R to Restart"
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Press R to Restart", frameWidth / 2 - 90, frameHeight / 2);

            // Tampilkan skor hasil permainan tadi
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Score: " + score, frameWidth / 2 - 40, frameHeight / 2 + 30);
        }
    }

    public void move() {
        if (!gameOver) {
            player.setVelocityY(player.getVelocityY() + gravity);
            player.setPosY(player.getPosY() + player.getVelocityY());
            player.setPosY(Math.max(player.getPosY(), 0));

            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());

                // Cek tabrakan dengan pipa
                if (player.collidesWith(pipe)) {
                    gameOver = true;
                }

                // Check for passing pipes
                if (pipe.getPosX() < player.getPosX() && !pipe.isPassed()) {
                    // Cek apakah burung melewati pipa atas dan bawah
                    Pipe upperPipe = null;
                    Pipe lowerPipe = null;
                    boolean passed = false;
                    for (int j = 0; j < pipes.size(); j += 2) {
                        upperPipe = pipes.get(j);
                        lowerPipe = pipes.get(j + 1);
                        if (upperPipe == pipe || lowerPipe == pipe) {
                            if (player.getPosY() > upperPipe.getPosY() + upperPipe.getHeight() &&
                                    player.getPosY() + player.getHeight() < lowerPipe.getPosY()) {
                                score++;
                                scoreLabel.setText("Score: " + score);
                                upperPipe.setPassed(true);
                                lowerPipe.setPassed(true);
                                passed = true;
                                break;
                            }
                        }
                    }
                    if (!passed) {
                        pipe.setPassed(false);
                    }
                }
            }

            // Check if player touches ground
            if (player.getPosY() + player.getHeight() >= frameHeight) {
                gameOver = true;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!gameOver) {
                player.setVelocityY(-10);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {//atur tombol r untuk restart permainan
            restartGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void placePipes() {
        int randomPosY = (int) (frameHeight / 4 + Math.random() * (frameHeight / 3));
        int gapHeight = 150; // Tinggi celah antara pipa atas dan bawah

        Pipe upperPipe = new Pipe(frameWidth, randomPosY - 512 - gapHeight / 2, 64, 512, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(frameWidth, randomPosY + gapHeight / 2, 64, 512, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    private void restartGame() {
        pipes.clear();
        player.setPosX(frameWidth / 8);
        player.setPosY(frameHeight / 2);
        gameOver = false;
        score = 0;
        scoreLabel.setText("Score: " + score);
    }
}
