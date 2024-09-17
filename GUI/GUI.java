package GUI;

/**
 * @author Nicholas Parise
 * @version 1.0
 * @since July 2nd , 2023
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Render extends JPanel {

    ConnectFour confour;

    public Render(ConnectFour connectFour){
        confour = connectFour;
    }

    public void paint(Graphics g){
        paintComponents(g);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponent(g);
        makeGrid(g);
        drawText(g);
    }


    public void drawText(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        if(confour.getGameState() == 1) {

            float f=80.0f; // font size.
            g2d.setFont(g.getFont().deriveFont(f));
            g2d.setColor(Color.green);
            g2d.drawString("Winner",100,f+100);

        }else if(confour.getGameState() == 2) {

            float f=80.0f; // font size.
            g2d.setFont(g.getFont().deriveFont(f));
            g2d.setColor(Color.red);
            g2d.drawString("Loser",120,f+100);

        }else if(confour.getGameState() == 3) {

            float f=80.0f; // font size.
            g2d.setFont(g.getFont().deriveFont(f));
            g2d.setColor(Color.red);
            g2d.drawString("Waiting",90,f+100);

        }
    }


    public void makeGrid(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        char[][] matrix = confour.getMatrix();

        g2d.setColor(Color.blue);
        g2d.fillRect(10, 30, 430, 400);

        g2d.fillRect(10, 400, 50, 70);
        g2d.fillRect(390, 400, 50, 70);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {

                if(matrix[i][j] == 'X') {
                    g2d.setColor(Color.red);
                }else if(matrix[i][j] == 'O'){
                    g2d.setColor(Color.yellow);
                }else{
                    g2d.setColor(Color.gray);
                }

                if(i == confour.getGhosti() && j == confour.getGhostj()){
                    if(confour.getGhostPlayer() == 'o'){
                        g2d.setColor(Color.ORANGE);
                    }else{
                        g2d.setColor(Color.PINK);
                    }
                }

                g.fillOval(20+j*60, 40+i*60, 50, 50);
            }
        }
    }
}

public class GUI extends JFrame {

    JButton[] b;

    ConnectFour confour;

    public GUI(ConnectFour connectFour) {

        confour = connectFour;

        addButtons();

        add(new Render(confour));

        setSize(465, 500);
        setTitle("Connect 4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setLayout(null);

    }

    public void close(){
        dispose();
    }

    public void update() {
        // updates the render window
        validate();
        repaint();
    }


    public String SpawnPlayAgainBox(){
        String message = "Would you like to play again?";
        int answer = JOptionPane.showConfirmDialog(this, message);
        if (answer == JOptionPane.YES_OPTION) {
            return "Y";
        } else if (answer == JOptionPane.NO_OPTION) {
            return "N";
        }
        return "N";
    }



    private void addButtons(){

        b = new JButton[7];

        for (int i = 0; i < 7; i++) {
            b[i] = new JButton("" + (i + 1));//creating instance of JButton
            b[i].setBounds(20 + (i * 60), 10, 50, 20);//x axis, y axis, width, height
            add(b[i]);

            b[i].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    confour.removeGhost();
                }
            });
        }


            b[0].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){

                    if(confour.isCanSend() && confour.canInsert(0) && !confour.getPromptPlayAgain()){
                        confour.insertAt(1);
                    }
                }
            });
            b[0].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    confour.insertGhost(0);
                }
            });


        b[1].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(confour.isCanSend() && confour.canInsert(1) && !confour.getPromptPlayAgain()){
                    confour.insertAt(2);
                }
            }
        });
        b[1].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confour.insertGhost(1);
            }
        });


        b[2].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(confour.isCanSend() && confour.canInsert(2) && !confour.getPromptPlayAgain()){
                    confour.insertAt(3);
                }
            }
        });
        b[2].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confour.insertGhost(2);
            }
        });


        b[3].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(confour.isCanSend() && confour.canInsert(3) && !confour.getPromptPlayAgain()){
                    confour.insertAt(4);
                }
            }
        });
        b[3].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confour.insertGhost(3);
            }
        });


        b[4].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(confour.isCanSend() && confour.canInsert(4) && !confour.getPromptPlayAgain()){
                    confour.insertAt(5);
                }
            }
        });
        b[4].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confour.insertGhost(4);
            }
        });


        b[5].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(confour.isCanSend() && confour.canInsert(5) && !confour.getPromptPlayAgain()){
                    confour.insertAt(6);
                }
            }
        });
        b[5].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confour.insertGhost(5);
            }
        });


        b[6].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(confour.isCanSend() && confour.canInsert(6) && !confour.getPromptPlayAgain()){
                    confour.insertAt(7);
                }
            }
        });
        b[6].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confour.insertGhost(6);
            }
        });
    }
}
