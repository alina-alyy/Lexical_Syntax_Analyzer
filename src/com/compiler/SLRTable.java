package com.compiler;

import java.util.ArrayList;
import java.util.HashMap;

public class SLRTable {
    public ArrayList<HashMap<String, String>> SLRTable = new ArrayList<HashMap<String, String>>();

    public SLRTable(){
        HashMap<String, String> row0 = new HashMap<String, String>();
        row0.put("ID", "s5");
        row0.put("AD", "-");
        row0.put("ML", "-");
        row0.put("OP", "s4");
        row0.put("CP", "-");
        row0.put("$", "-");
        row0.put("E", "1");
        row0.put("T", "2");
        row0.put("F", "3");
        SLRTable.add(row0);

        HashMap<String, String> row1 = new HashMap<String, String>();
        row1.put("ID", "-");
        row1.put("AD", "s6");
        row1.put("ML", "-");
        row1.put("OP", "-");
        row1.put("CP", "-");
        row1.put("$", "accept");
        row1.put("E", "-");
        row1.put("T", "-");
        row1.put("F", "-");
        SLRTable.add(row1);

        HashMap<String, String> row2 = new HashMap<String, String>();
        row2.put("ID", "-");
        row2.put("AD", "r2");
        row2.put("ML", "s7");
        row2.put("OP", "-");
        row2.put("CP", "r2");
        row2.put("$", "r2");
        row2.put("E", "-");
        row2.put("T", "-");
        row2.put("F", "-");
        SLRTable.add(row2);

        HashMap<String, String> row3 = new HashMap<String, String>();
        row3.put("ID", "-");
        row3.put("AD", "r4");
        row3.put("ML", "r4");
        row3.put("OP", "-");
        row3.put("CP", "r4");
        row3.put("$", "r4");
        row3.put("E", "-");
        row3.put("T", "-");
        row3.put("F", "-");
        SLRTable.add(row3);

        HashMap<String, String> row4 = new HashMap<String, String>();
        row4.put("ID", "s5");
        row4.put("AD", "-");
        row4.put("ML", "-");
        row4.put("OP", "s4");
        row4.put("CP", "-");
        row4.put("$", "-");
        row4.put("E", "8");
        row4.put("T", "2");
        row4.put("F", "3");
        SLRTable.add(row4);

        HashMap<String, String> row5 = new HashMap<String, String>();
        row5.put("ID", "-");
        row5.put("AD", "r6");
        row5.put("ML", "r6");
        row5.put("OP", "-");
        row5.put("CP", "r6");
        row5.put("$", "r6");
        row5.put("E", "-");
        row5.put("T", "-");
        row5.put("F", "-");
        SLRTable.add(row5);

        HashMap<String, String> row6 = new HashMap<String, String>();
        row6.put("ID", "s5");
        row6.put("AD", "-");
        row6.put("ML", "-");
        row6.put("OP", "s4");
        row6.put("CP", "-");
        row6.put("$", "-");
        row6.put("E", "-");
        row6.put("T", "9");
        row6.put("F", "3");
        SLRTable.add(row6);

        HashMap<String, String> row7 = new HashMap<String, String>();
        row7.put("ID", "s5");
        row7.put("AD", "-");
        row7.put("ML", "-");
        row7.put("OP", "s4");
        row7.put("CP", "-");
        row7.put("$", "-");
        row7.put("E", "-");
        row7.put("T", "-");
        row7.put("F", "10");
        SLRTable.add(row7);

        HashMap<String, String> row8 = new HashMap<String, String>();
        row8.put("ID", "-");
        row8.put("AD", "s6");
        row8.put("ML", "-");
        row8.put("OP", "-");
        row8.put("CP", "s11");
        row8.put("$", "-");
        row8.put("E", "-");
        row8.put("T", "-");
        row8.put("F", "-");
        SLRTable.add(row8);

        HashMap<String, String> row9 = new HashMap<String, String>();
        row9.put("ID", "-");
        row9.put("AD", "r1");
        row9.put("ML", "s7");
        row9.put("OP", "-");
        row9.put("CP", "r1");
        row9.put("$", "r1");
        row9.put("E", "-");
        row9.put("T", "-");
        row9.put("F", "-");
        SLRTable.add(row9);

        HashMap<String, String> row10 = new HashMap<String, String>();
        row10.put("ID", "-");
        row10.put("AD", "r3");
        row10.put("ML", "r3");
        row10.put("OP", "-");
        row10.put("CP", "r3");
        row10.put("$", "r3");
        row10.put("E", "-");
        row10.put("T", "-");
        row10.put("F", "-");
        SLRTable.add(row10);

        HashMap<String, String> row11 = new HashMap<String, String>();
        row11.put("ID", "-");
        row11.put("AD", "r5");
        row11.put("ML", "r5");
        row11.put("OP", "-");
        row11.put("CP", "r5");
        row11.put("$", "r5");
        row11.put("E", "-");
        row11.put("T", "-");
        row11.put("F", "-");
        SLRTable.add(row11);

    }
}
