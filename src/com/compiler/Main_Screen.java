package com.compiler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_Screen extends JFrame{
    private JPanel mainPanel;

    private JButton lexicalAnalysisButton;
    private JButton syntaxAnalysisButton;
    private JButton exitButton;
    private JTextArea textArea1;

    public Main_Screen(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        syntaxAnalysisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        lexicalAnalysisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


    }
    public static void main(String[] args){
     JFrame frame=new Main_Screen("Hello");
     frame.setVisible(true);
    }
}
