package _test;

import gui.GUI;
import linear.Stack;
import linear.StackWithViewer;

public class Parser {
    public boolean deineMama(String input){
        Stack keller = new StackWithViewer<Character>();
        int zustand = 0;
        for(int i=0;i<input.length();i++){
            char v = input.charAt(i);
            if(zustand==0){
                if(v=='s') zustand=1;
                else return false;
                continue;
            }
            else if(zustand==1){
                if(v=='d') keller.push('A');
                else if(v=='t') keller.push('A');
                else if(v=='k') {
                    zustand=2;
                }
                else return false;
                continue;
            }
            else if(zustand==2){
                if(v=='k') keller.pop();
                else if(v=='z' && keller.isEmpty()) zustand=3;
                else return false;
                continue;
            }
        }

        return (zustand==3);
    }

    public static void main (String[] args){
        Parser p = new Parser();
        new GUI(p);
    }
}
