package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;
    final int tileSize = originalTileSize * scale; //48x48 pixels
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = maxScreenCol * tileSize; // 768 pixels
    final int screenHeight = maxScreenRow * tileSize; // 576 pixels

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

//        double drawInterval = 1000000000/FPS;
//        double delta = 0;
//        long lastTime = System.nanoTime();
//        long currentTime;

        while(gameThread != null) {
            System.out.println("GAME");
//
//            currentTime = System.nanoTime();
//
//            delta += (currentTime - lastTime) / drawInterval;
//
//            lastTime = currentTime;
//
//            if (delta >= 1) {
                //1.UPDATE: update information such as character position
                update();
                //2.DRAW: draw the screen with the updated information
                repaint();
//                delta--;
//            }

        }
    }

    public void update() {
        if (keyH.upPressed) {
            playerY -= playerSpeed;
            System.out.println("Player move up");
        }
        else if (keyH.downPressed) {
            playerY += playerSpeed;
            System.out.println("Player move down");
        }
        else if (keyH.rightPressed) {
            playerX += playerSpeed;
            System.out.println("Player move right");
        }
        else if (keyH.leftPressed) {
            playerX -= playerSpeed;
            System.out.println("Player move left");
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.WHITE);

        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose();
    }
}
