package com.compiler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_Screen extends JFrame {
    SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer();
    private JPanel mainPanel;
    private JPanel panel2;
    private JTextArea inputTextArea;
    private JButton lexicalAnalysisButton;
    private JButton exitButton;
    private JButton syntaxButton;
    private JTextArea outputTextArea;
    private JButton resetButton;
    private LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();

    public Main_Screen(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        lexicalAnalysisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user_input = inputTextArea.getText();
                try {
                    lexicalAnalyzer.processInput(user_input);
                } catch (Exception exception) {
                    outputTextArea.append(exception.getMessage());
                    return;
                }
                outputTextArea.append("============SYMBOL TABLE============\n\n");
                outputTextArea.append(lexicalAnalyzer.symbolTableOutput);
                outputTextArea.append("\n\n============TOKEN LIST============\n\n");
                outputTextArea.append(lexicalAnalyzer.tokenListOutput);
                syntaxButton.setEnabled(true);
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputTextArea.setText("");
                lexicalAnalyzer.resetLexicalAnalyzer();
                syntaxAnalyzer.resetSyntaxAnalyzer();
                syntaxButton.setEnabled(false);
            }
        });
        syntaxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = "";
                outputTextArea.append("\n\n============SYNTAX ANALYZER============\n\n");
                try {
                    result = syntaxAnalyzer.compile(lexicalAnalyzer.getTokenList());
                } catch (Exception exception) {
                    outputTextArea.append(exception.getMessage());
                    return;
                }
                outputTextArea.append(result);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new Main_Screen("Lexical and Syntax Analyzer");
        frame.setVisible(true);
    }
}
