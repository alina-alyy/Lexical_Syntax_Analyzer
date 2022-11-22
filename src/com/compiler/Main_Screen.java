package com.compiler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_Screen extends JFrame{
    private JPanel mainPanel;
    private JPanel panel2;
    private JTextArea inputTextArea;
    private JButton lexicalAnalysisButton;
    private JButton exitButton;
    private JButton syntaxButton;
    private JTextArea outputTextArea;


    public Main_Screen(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public static void main(String[] args){
     JFrame frame = new Main_Screen("Lexical and Syntax Analyzer");
     frame.setVisible(true);
    }
}
