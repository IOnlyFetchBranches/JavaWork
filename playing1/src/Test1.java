/**
 * Created by demarcus-joachim on 1/19/2017.
 */
import javax.swing.*;
import java.io.*;
import java.util.*;
import Tools.cWindow;
public class Test1  {
    public static void main(String[] args){
       cWindow c =new cWindow();
        c.clear(3000);
        System.out.println("Hello, How are you doing?");
        c.clear(3000);
        System.err.println("Good.");
        c.clear(3000);
        System.out.println("My name is Tomas");
        c.frame.setTitle("Tom");
        c.clear(3000);
        System.out.println("Let me make this more friendly!");
        c.clear(3000);
        System.err.println("Nice to meet you");
        c.clear(3000);
        for(int x=0;x<100;x++){
            System.out.println(x);
            c.clear(500);
        }
    }
}
