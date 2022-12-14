package com.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static String ArrayToString(ArrayList<String> Array) {
        StringBuilder result = new StringBuilder();
        for (String s : Array) {
            result.append(s);
        }

        return result.toString();
    }

    public static ArrayList<String> StringToArray(String input) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            result.add(String.valueOf(input.charAt(i)));
        }

        return result;
    }


    public static void main(String[] args) throws IOException {
        // The Program Execution Starts from Here

        //Parameters:

        String fileName = "NFA";
        String input_string_for_dfa = "aabb";

        //Step 1: Read NDFSA-null from File:
        FileHandler fileHandler = new FileHandler(fileName);
        HashMap<String, State> allStates = fileHandler.readNFA();

        //Step 2: Convert NDFSA-null to NDFSA:
        NFA nfa = new NFA();
        HashMap<String, State> NFA = nfa.NFA_NULL_TO_NFA(allStates);


        //Step 3: Convert NFA to DFA
        HashMap<String, State> nfa_DFA = nfa.NFA_TO_DFA(NFA);

        //Step 4: Minimize DFA
        DFA dfa = new DFA();
        HashMap<String, State> DFA = dfa.MINIMIZE_DFA(nfa_DFA);

        //Step 5: Run DFA to get Accept/Reject
        if (dfa.EXECUTE_ON_DFA(DFA, input_string_for_dfa)) {
            System.out.println("Accepted");
        }
        else {
            System.out.println("Rejected");
        }

        //Step 6: Write DFA onto a File
        fileHandler.writeDFA(DFA);

    }
}
