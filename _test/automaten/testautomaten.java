package _test.automaten;

import linear.Stack;

public class testautomaten {
    


    public boolean mehrWnachTalsVorT(String pWort){
        int zustand = 0;
        Stack<Character> keller = new Stack<>();
        for(int i = 0;i<pWort.length();i++){
            char c = pWort.charAt(i);
            if(zustand==0){
                if(c=='w'){
                    keller.push('A');
                }
                else if(c=='z'){
                    continue;
                }
                else if(c=='t'){
                    zustand = 1;
                }
                else{
                    return false;
                }
            }else if(zustand == 1){
                if(c=='w' && !keller.isEmpty() && keller.top()=='A'){
                    keller.pop();
                }else if(c=='w' && !keller.isEmpty() && keller.top()=='B'){
                    keller.push('B');
                }else if(c=='w' && keller.isEmpty()){
                    keller.push('B');
                }else if(c=='z'){
                    continue;
                }else{
                    return false;
                }
            }
    
        }
        return (!keller.isEmpty() && keller.top()=='B');
    
    
    }
    public static void main(String[] args) {
        testautomaten t = new testautomaten();
        System.out.println(t.mehrWnachTalsVorT("ztw")); // true
        System.out.println(t.mehrWnachTalsVorT("ztz")); // false
        System.out.println(t.mehrWnachTalsVorT("zttw")); // false
        System.out.println(t.mehrWnachTalsVorT("zwtzwzz")); // false
        System.out.println(t.mehrWnachTalsVorT("zwtzwwz")); // true
    }

}
