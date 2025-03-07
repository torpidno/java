package classes;

import java.awt.Color;
import java.awt.Graphics;

public class Circle {
    private int xCoord;
    private int yCoord;
    private int radius;
    private Color color;

    public Circle(int x, int y, int r) {
        this.xCoord = x;
        this.yCoord = y;
        this.radius = r;
        this.color = Color.BLUE; // Valfri färg, här använder jag blå
    }

    public void draw(Graphics g) {
        // Spara originalfärgen
        Color originalColor = g.getColor();
        
        // Sätt cirkelns färg
        g.setColor(this.color);
        
        // Rita en fylld cirkel
        g.fillOval(this.xCoord - this.radius, this.yCoord - this.radius, 
                this.radius * 2, this.radius * 2);
        
        // Återställ originalfärgen
        g.setColor(originalColor);
    }
    
    public void move(int dx, int dy) {
        this.xCoord += dx;
        this.yCoord += dy;
    }
}