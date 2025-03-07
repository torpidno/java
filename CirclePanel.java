package classes;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

public class CirclePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private Circle circle1;
    private List<Ball> balls;
    private int mouseX, mouseY;
    private Timer gameTimer;

    public CirclePanel() {
        circle1 = new Circle(100, 100, 20);
        balls = new ArrayList<>();
        
        // Initialize mouse coordinates
        mouseX = 0;
        mouseY = 0;
        
        setLayout(null);
       
        // Make panel focusable for keyboard input
        setFocusable(true);
        
        // Add keyboard listener for arrow keys and space
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                
                switch (keyCode) {
                    case KeyEvent.VK_W:
                        circle1.move(0, -10);
                        break;
                    case KeyEvent.VK_S:
                        circle1.move(0, 10);
                        break;
                    case KeyEvent.VK_A:
                        circle1.move(-10, 0);
                        break;
                    case KeyEvent.VK_D:
                        circle1.move(10, 0);
                        break;
                    case KeyEvent.VK_SPACE:
                        // Shoot a ball when space is pressed
                        balls.add(circle1.shoot());
                        break;
                }
                
                repaint();
            }
        });
        
        // Add mouse motion listener to track mouse position
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                circle1.updateAngle(mouseX, mouseY);
                repaint();
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                circle1.updateAngle(mouseX, mouseY);
                repaint();
            }
        });
        
        // Create game timer to update ball positions
        gameTimer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();
                repaint();
            }
        });
        gameTimer.start();
    }
    
    private void updateGame() {
        // Update circle angle based on mouse position
        circle1.updateAngle(mouseX, mouseY);
        
        // Update all balls and remove inactive ones
        int width = getWidth();
        int height = getHeight();
        
        for (int i = balls.size() - 1; i >= 0; i--) {
            Ball ball = balls.get(i);
            ball.update();
            
            // Remove balls that go out of bounds
            if (ball.isOutOfBounds(width, height)) {
                balls.remove(i);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw the circle with its pointing rectangle
        circle1.draw(g);
        
        // Draw all active balls
        for (Ball ball : balls) {
            ball.draw(g);
        }
    }
    
    // Request focus when panel becomes visible
    @Override
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
}
