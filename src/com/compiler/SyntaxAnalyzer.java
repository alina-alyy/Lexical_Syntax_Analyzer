package com.compiler;

import java.util.ArrayList;
import java.util.Stack;

public class SyntaxAnalyzer {
    SLRTable slr = new SLRTable();
    String[] productionRules = new String[]{"", "E->E + T", "E->T", "T->T * F", "T->F", "F->( E )", "F->id"};
    Stack<String> stack = new Stack<String>();

    public void resetSyntaxAnalyzer() {
        stack.clear();
    }

    private void addTokenToStack(Token token) {
        if (token.getTokenName().equals("ID")) {
            stack.push("ID");
        } else {
            stack.push(token.getValue());
        }
    }

    private String getTokenValue(Token token) {
        if (token.getTokenName().equals("ID")) {
            return "ID";
        } else {
            return token.getValue();
        }
    }

    public String compile(ArrayList<Token> tokenList) throws Exception {
        int i = 0;
        int curState = 0;
        String action = "";
        String curTokenValue = "";
        Token token = new Token("$", "$");
        tokenList.add(token);

        stack.push("0");

        while (i < tokenList.size()) {
            curTokenValue = getTokenValue(tokenList.get(i));
            if (!stack.peek().matches("\\d+")) {
                curState = Integer.parseInt(stack.get(stack.size() - 2));
                action = slr.SLRTable.get(curState).get(stack.peek());
                stack.push(action);
                curState = Integer.parseInt(stack.peek());
            }
            action = slr.SLRTable.get(curState).get(curTokenValue);

            if (action == null || action.equals("-")) {
                throw new Exception("Compile Error, Invalid input\n");
            } else if (action.equals("accept")) {
                return "Compiled Successfully";
            }

            if (action.charAt(0) == 's') {
                addTokenToStack(tokenList.get(i));
                stack.push(action.substring(1));
                curState = Integer.parseInt(stack.peek());
                i++;
            } else if (action.charAt(0) == 'r') {
                int ruleNo = Integer.parseInt(action.substring(1));
                String productionRule = productionRules[ruleNo];
                String terminal = productionRule.split("->")[0];
                int length = productionRule.split("->")[1].split(" ").length;
                for (int j = 0; j < length * 2; j++) {
                    stack.pop();
                }
                stack.push(terminal);
            }
        }
        return "Compile Error, Invalid input";
    }
}
