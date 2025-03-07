package classes;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class CirclePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private Circle circle1;

    public CirclePanel() {
        circle1 = new Circle(100, 100, 20);
        setLayout(null);
        
        // Skapa knappar för att flytta cirkeln
        JButton btnUp = new JButton("Upp");
        btnUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                circle1.move(0, -10);
                repaint();
            }
        });
        btnUp.setBounds(120, 6, 100, 29);
        add(btnUp);
        
        JButton btnDown = new JButton("Ner");
        btnDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                circle1.move(0, 10);
                repaint();
            }
        });
        btnDown.setBounds(120, 40, 100, 29);
        add(btnDown);
        
        JButton btnLeft = new JButton("Vänster");
        btnLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                circle1.move(-10, 0);
                repaint();
            }
        });
        btnLeft.setBounds(10, 40, 100, 29);
        add(btnLeft);
        
        JButton btnRight = new JButton("Höger");
        btnRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                circle1.move(10, 0);
                repaint();
            }
        });
        btnRight.setBounds(230, 40, 100, 29);
        add(btnRight);
        
        // Gör panelen fokusbar för tangentbordsinmatning
        setFocusable(true);
        
        // Lägg till tangentbordslyssnare för piltangenterna
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        circle1.move(0, -10);
                        break;
                    case KeyEvent.VK_DOWN:
                        circle1.move(0, 10);
                        break;
                    case KeyEvent.VK_LEFT:
                        circle1.move(-10, 0);
                        break;
                    case KeyEvent.VK_RIGHT:
                        circle1.move(10, 0);
                        break;
                }
                
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        circle1.draw(g);
    }
    
    // Begär fokus när panelen blir synlig
    @Override
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
}