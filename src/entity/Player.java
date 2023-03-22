package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/10 - gp.tileSize/2;
        screenY = gp.screenHeight/8 - gp.tileSize/2;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        worldX = gp.tileSize;
        worldY = gp.tileSize;
        speed = 3;
        direction = "down";
    }

    public void getPlayerImage() {

        try {

            up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up.png")));
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_2.png")));
            down = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_2.png")));
            left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_2.png")));
            right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_2.png")));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
                worldY -= speed;
            }
            else if (keyH.downPressed) {
                direction = "down";
                worldY += speed;
            }
            else if (keyH.rightPressed) {
                direction = "right";
                worldX += speed;
            }
            else if (keyH.leftPressed) {
                direction = "left";
                worldX -= speed;
            }

            spriteCounter++;
            if (spriteCounter > 9) {
                if (spriteNum == 0) {
                    spriteNum = 1;
                } else if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 0;
                }
                spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 0) {
                    image = up;
                }
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 0) {
                    image = down;
                }
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 0) {
                    image = left;
                }
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 0) {
                    image = right;
                }
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}
