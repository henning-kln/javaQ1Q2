package _test;

import gui.GUI;
import linear.StackWithViewer;
import linear.Stack;

public class Kellerautomat {
    private Stack<Character> keller;
    public static void main(String[] agrs){
        Kellerautomat ka = new Kellerautomat();
        new GUI(ka);
    }

    private boolean parse(String word){
        keller = new StackWithViewer<>();
        int zustand = 0;
        for (int i=0;i<word.length();i++){
            if(zustand==0){
                if(word.charAt(i)=='a'){

                    keller.push('A');

                }else if(word.charAt(i)=='b' && !keller.isEmpty()){
                    keller.pop();
                    zustand=1;
                }else{
                    return false;
                }
            }else if(zustand==1){
                if(word.charAt(i)=='b' && !keller.isEmpty()){
                    keller.pop();
                }else if(word.charAt(i)=='€'){
                    zustand=2;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }


        return (zustand==2);
    }
}
