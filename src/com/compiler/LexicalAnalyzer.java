package com.compiler;

import java.util.ArrayList;


public class LexicalAnalyzer {
    public String symbolTableOutput = "";
    public String tokenListOutput = "";
    ArrayList<String[]> symbolTable = new ArrayList();
    ArrayList<Token> tokenList = new ArrayList();

    public LexicalAnalyzer() {
        initializeSymbolTable();
    }

    public static boolean isAlphaNumeric(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9');
    }

    public static boolean isAlphabet(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }

    public static boolean isTerminateLexeme(char ch) {
        return (ch == ' ' || ch == '\t' || ch == '\n' || ch == '$' || ch == ';');
    }

    public void resetLexicalAnalyzer() {
        symbolTableOutput = "";
        tokenListOutput = "";
        symbolTable = new ArrayList();
        initializeSymbolTable();
        tokenList = new ArrayList();
    }

    public ArrayList<Token> getTokenList() {
        return tokenList;
    }

    private void addToTokenList(Token token) {
        for (int i = 0; i < symbolTable.size(); i++) {
            if (symbolTable.get(i)[0].equals(token.getTokenName()) && symbolTable.get(i)[1].equals(token.getValue())) {
                tokenList.add(new Token(token.getTokenName(), Integer.toString(i)));
                return;
            }
        }
        tokenList.add(token);
    }

    private void addToSymbolTable(Token token) {
        for (String[] curToken : symbolTable) {
            if (curToken[0].equals(token.getTokenName()) && curToken[1].equals(token.getValue())) {
                return;
            }
        }
        symbolTable.add(new String[]{token.getTokenName(), token.getValue()});
    }

    private void initializeSymbolTable() {
        symbolTable.add(new String[]{"int", "-"});
        symbolTable.add(new String[]{"char", "-"});
        symbolTable.add(new String[]{"string", "-"});
        symbolTable.add(new String[]{"if", "-"});
        symbolTable.add(new String[]{"else", "-"});
        symbolTable.add(new String[]{"do", "-"});
        symbolTable.add(new String[]{"while", "-"});
    }

    private boolean searchKeyword(String word) {
        for (int i = 0; i < symbolTable.size(); i++) {
            if (symbolTable.get(i)[0].equals(word)) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumeric(char ch) {
        return Character.isDigit(ch);
    }

    private boolean isOperator(char ch) {
        return (ch == '<' || ch == '>' || ch == '=' || ch == '(' || ch == ')' || ch == '{' || ch == '}'
                || ch == '+' || ch == '-' || ch == '*' || ch == '/');
    }

    public void processInput(String input) throws Exception {
        int state = 0;
        int line = 1;
        input = input + "$";
        String curLexeme = "";
        String errorMsg = "lexeme not found";

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            switch (state) {
                case 0:
                    if (ch == ';') {
                        state = 15;
                    } else if (ch == '<') {
                        state = 4;
                    } else if (ch == '>') {
                        state = 7;
                    } else if (ch == '=') {
                        state = 9;
                    } else if (isAlphabet(ch) || ch == '_') {
                        curLexeme += ch;
                        state = 2;
                    } else if (ch == '(') {
                        state = 11;
                    } else if (ch == ')') {
                        state = 12;
                    } else if (ch == '{') {
                        state = 13;
                    } else if (ch == '}') {
                        state = 14;
                    } else if (isTerminateLexeme(ch)) {
                        if (ch == '\n') {
                            line = line + 1;
                        }
                        state = 3;
                    } else if (ch == '+') {
                        state = 16;
                    } else if (ch == '-') {
                        state = 17;
                    } else if (ch == '*') {
                        state = 18;
                    } else if (ch == '/') {
                        state = 19;
                    } else if (ch == '"') {
                        state = 20;
                    } else if (isNumeric(ch)) {
                        curLexeme += ch;
                        state = 21;
                    } else {
                        state = 1;
                    }
                    break;

                case 1:
                    // no token matches state
                    i--;
                    ch = input.charAt(i);
                    while (!isTerminateLexeme(ch)) {
                        ch = input.charAt(i);
                        curLexeme += ch;
                        i++;
                    }
                    throw new Exception("Error: " + curLexeme.strip().replace("$", "") + " " + errorMsg + " at line number " + line + "\n");
//                    curLexeme = "";
//                    state = 0;
                    //break;

                case 2:
                    if (isAlphaNumeric(ch) || ch == '_') {
                        curLexeme += ch;
                        state = 2;
                    } else if (isTerminateLexeme(ch) || isOperator(ch)) {
                        // identifier final state
                        if (searchKeyword(curLexeme)) {
                            Token token = new Token(curLexeme, "-");
                            addToTokenList(token);
                        } else {
                            Token token = new Token("ID", curLexeme);
                            addToSymbolTable(token);
                            addToTokenList(token);
                        }
                        i--;
                        curLexeme = "";
                        state = 0;
                    } else {
                        errorMsg = "The keyword is invalid";
                        state = 1;
                    }
                    break;
                case 3:
                    if (isTerminateLexeme(ch)) { // whitespace state
                        state = 3;
                        if (ch == '\n') {
                            line++;
                        }
                    } else {
                        state = 0;
                        i--;
                    }
                    break;
                case 4:
                    if (isTerminateLexeme(ch) || isAlphaNumeric(ch)) {
                        Token token = new Token("ROP", "LT");
                        addToTokenList(token);
                        i--;
                        state = 0;
                    } else if (ch == '=') {
                        state = 5;
                    } else if (ch == '>') {
                        state = 6;
                    } else {
                        state = 1;
                    }
                    break;
                case 5:
                    if (isTerminateLexeme(ch) || isAlphaNumeric(ch)) {
                        Token token = new Token("ROP", "LE");
                        addToTokenList(token);
                        i--;
                        state = 0;
                    } else {
                        state = 1;
                    }
                    break;
                case 6:
                    if (isTerminateLexeme(ch) || isAlphaNumeric(ch)) {
                        Token token = new Token("ROP", "NE");
                        addToTokenList(token);
                        i--;
                        state = 0;
                    } else {
                        state = 1;
                    }
                    break;
                case 7:
                    if (isTerminateLexeme(ch) || isAlphaNumeric(ch)) {
                        Token token = new Token("ROP", "GT");
                        addToTokenList(token);
                        i--;
                        state = 0;
                    } else if (ch == '=') {
                        state = 8;
                    } else {
                        state = 1;
                    }
                    break;
                case 8:
                    if (isTerminateLexeme(ch) || isAlphaNumeric(ch)) {
                        Token token = new Token("ROP", "GE");
                        addToTokenList(token);
                        i--;

                        state = 0;
                    } else {
                        state = 1;
                    }
                    break;
                case 9:
                    if (isTerminateLexeme(ch) || isAlphaNumeric(ch)|| ch=='"') {
                        Token token = new Token("OOP", "AS");
                        addToTokenList(token);
                        i--;

                        state = 0;
                    } else if (ch == '=') {
                        state = 10;
                    } else {
                        state = 1;
                    }
                    break;
                case 10:
                    if (isTerminateLexeme(ch) || isAlphaNumeric(ch)) {
                        Token token = new Token("ROP", "EQ");
                        addToTokenList(token);
                        i--;
                        state = 0;
                    } else {
                        state = 1;
                    }
                    break;
                case 11:
                    if (isAlphaNumeric(ch) || isTerminateLexeme(ch)) {
                        Token token = new Token("OOP", "OP");
                        addToTokenList(token);
                        i--;
                        state = 0;
                    } else {
                        state = 1;
                    }
                    break;
                case 12:
                    if (isAlphaNumeric(ch) || isTerminateLexeme(ch) || ch == '{') {
                        Token token = new Token("OOP", "CP");
                        addToTokenList(token);
                        i--;
                        state = 0;
                    } else {
                        state = 1;
                    }
                    break;
                case 13:
                    if (isAlphaNumeric(ch) || isTerminateLexeme(ch)) {
                        Token token = new Token("OOP", "OB");
                        addToTokenList(token);
                        i--;

                        state = 0;
                    } else {
                        state = 1;
                    }

                    break;
                case 14:
                    if (isAlphaNumeric(ch) || isTerminateLexeme(ch)) {
                        Token token = new Token("OOP", "CB");
                        addToTokenList(token);
                        i--;

                        state = 0;
                    } else {
                        state = 1;
                    }

                    break;
                case 15:
                    if (isAlphaNumeric(ch) || isTerminateLexeme(ch)) {
                        Token token = new Token("OOP", "TR");
                        addToTokenList(token);
                        i--;

                        state = 0;
                    } else {
                        state = 1;
                    }

                    break;
                case 16:
                    if (isAlphaNumeric(ch) || isTerminateLexeme(ch)) {
                        Token token = new Token("AOP", "AD");
                        addToTokenList(token);
                        i--;

                        state = 0;
                    } else {
                        state = 1;
                    }

                    break;
                case 17:
                    if (isAlphaNumeric(ch) || isTerminateLexeme(ch)) {
                        Token token = new Token("AOP", "SB");
                        addToTokenList(token);
                        i--;

                        state = 0;
                    } else {
                        state = 1;
                    }

                    break;
                case 18:

                    if (isAlphaNumeric(ch) || isTerminateLexeme(ch)) {
                        Token token = new Token("AOP", "ML");
                        addToTokenList(token);
                        i--;

                        state = 0;
                    } else {
                        state = 1;
                    }

                    break;
                case 19:
                    if (isAlphaNumeric(ch) || isTerminateLexeme(ch)) {
                        Token token = new Token("AOP", "DV");
                        addToTokenList(token);
                        i--;

                        state = 0;
                    } else if (ch == '/') {
                        state = 22;
                    } else if (ch == '*') {
                        state = 23;
                    } else {
                        state = 1;
                    }
                    break;
                case 20:
                    if (ch == '"') {
                        Token token = new Token("SL", curLexeme);
                        addToSymbolTable(token);
                        addToTokenList(token);
                        curLexeme = "";
                        state = 0;
                    } else if (ch == '\n') {
                        errorMsg = "Invalid lexeme, String is not complete";
                        state = 1;
                    } else {
                        curLexeme += ch;
                        state = 20;
                    }
                    break;
                case 21:
                    if (isNumeric(ch)) {
                        curLexeme += ch;
                        state = 21;

                    } else if (isTerminateLexeme(ch) || isOperator(ch)) {
                        Token token = new Token("IV", curLexeme);
                        addToSymbolTable(token);
                        addToTokenList(token);
                        curLexeme = "";
                        state = 0;

                        i--;
                    } else {
                        errorMsg = "The identifier is invalid";
                        state = 1;
                    }
                    break;
                case 22:
                    if (ch == '\n' || ch == '$') { // single comment state
                        line++;
                        state = 0;
                    } else {
                        state = 22;
                    }
                    break;
                case 23:
                    if (ch == '\n') {
                        line++;
                    }
                    if (ch == '*') {          // Multi line comment state
                        curLexeme += '*';
                        state = 23;
                    } else if (ch == '/' && curLexeme.equals("*")) {
                        state = 0;
                        curLexeme = "";
                    } else {
                        state = 23;
                        curLexeme = "";
                    }
                    break;
            }
        }
        symbolTableOutput += "Attribute Value" + "\t" + "Token Name" + "\t" + "Value" + "\n";
        for (int i = 0; i < symbolTable.size(); i++) {
            symbolTableOutput += i + "\t" + symbolTable.get(i)[0] + "\t" + symbolTable.get(i)[1] + "\n";
        }

        tokenListOutput += "Token Name" + "\t" + "Attribute Value" + "\n";
        for (int i = 0; i < tokenList.size(); i++) {
            tokenListOutput += tokenList.get(i).getTokenName() + "\t" + tokenList.get(i).getValue() + "\n";
        }

    }

}