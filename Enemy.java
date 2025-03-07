package classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class Enemy {
    private int xCoord;
    private int yCoord;
    private int radius;
    private Color color;
    private int rectLength;
    private double angle;
    private boolean active;
    private int shootTimer;
    private Random random;
    
    public Enemy(int x, int y, int r) {
        this.xCoord = x;
        this.yCoord = y;
        this.radius = r;
        this.color = Color.RED;
        this.rectLength = r * 2; // Rectangle length is twice the radius
        this.angle = 0;
        this.active = true;
        this.shootTimer = 0;
        this.random = new Random();
    }
    
    public void draw(Graphics g) {
        if (!active) return;
        
        // Save original color
        Color originalColor = g.getColor();
        
        // Set enemy color
        g.setColor(this.color);
        
        // Draw a filled circle
        g.fillOval(this.xCoord - this.radius, this.yCoord - this.radius, 
                this.radius * 2, this.radius * 2);
        
        // Draw the rectangle pointing toward the player
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        
        g2d.translate(xCoord, yCoord);
        g2d.rotate(angle);
        
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, -radius/3, rectLength, radius/2);
        
        g2d.setTransform(old);
        
        // Restore original color
        g.setColor(originalColor);
    }
    
    public void updateAngle(int playerX, int playerY) {
        // Calculate angle between enemy and player
        double deltaX = playerX - xCoord;
        double deltaY = playerY - yCoord;
        this.angle = Math.atan2(deltaY, deltaX);
    }
    
    public Ball shoot() {
        // Reset the shoot timer
        this.shootTimer = random.nextInt(100) + 50; // Random delay between 50-150 frames
        
        // Create a new ball at the end of the rectangle
        int ballX = (int)(xCoord + Math.cos(angle) * (radius + rectLength));
        int ballY = (int)(yCoord + Math.sin(angle) * (radius + rectLength));
        
        // Set velocity based on angle
        int speed = 3;
        int xVel = (int)(Math.cos(angle) * speed);
        int yVel = (int)(Math.sin(angle) * speed);
        
        return new Ball(ballX, ballY, radius/3, xVel, yVel, true);
    }
    
    public boolean update(int playerX, int playerY) {
        if (!active) return false;
        
        // Update angle to track player
        updateAngle(playerX, playerY);
        
        // Decrease shoot timer
        if (shootTimer > 0) {
            shootTimer--;
        }
        
        // Return true if it's time to shoot
        return shootTimer == 0;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public int getX() {
        return xCoord;
    }
    
    public int getY() {
        return yCoord;
    }
    
    public int getRadius() {
        return radius;
    }
}
