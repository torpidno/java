package classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Circle {
    private int xCoord;
    private int yCoord;
    private int radius;
    private Color color;
    private int rectLength;
    private double angle;

    public Circle(int x, int y, int r) {
        this.xCoord = x;
        this.yCoord = y;
        this.radius = r;
        this.color = Color.BLUE;
        this.rectLength = r * 2; // Rectangle length is twice the radius
        this.angle = 0;
    }

    public void draw(Graphics g) {
        // Save original color
        Color originalColor = g.getColor();
        
        // Set circle color
        g.setColor(this.color);
        
        // Draw a filled circle
        g.fillOval(this.xCoord - this.radius, this.yCoord - this.radius, 
                this.radius * 2, this.radius * 2);
        
        // Draw the rectangle pointing toward the mouse
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        
        g2d.translate(xCoord, yCoord);
        g2d.rotate(angle);
        
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, -radius/3, rectLength, radius/2);
        
        g2d.setTransform(old);
        
        // Restore original color
        g.setColor(originalColor);
    }
    
    public void move(int dx, int dy) {
        this.xCoord += dx;
        this.yCoord += dy;
    }
    
    public void updateAngle(int mouseX, int mouseY) {
        // Calculate angle between circle center and mouse position
        double deltaX = mouseX - xCoord;
        double deltaY = mouseY - yCoord;
        this.angle = Math.atan2(deltaY, deltaX);
    }
    
    public Ball shoot() {
        // Create a new ball at the end of the rectangle
        int ballX = (int)(xCoord + Math.cos(angle) * (radius + rectLength));
        int ballY = (int)(yCoord + Math.sin(angle) * (radius + rectLength));
        
        // Set velocity based on angle
        int speed = 5;
        int xVel = (int)(Math.cos(angle) * speed);
        int yVel = (int)(Math.sin(angle) * speed);
        
        return new Ball(ballX, ballY, radius/2, xVel, yVel);
    }
    
    public int getX() {
        return xCoord;
    }
    
    public int getY() {
        return yCoord;
    }
}
