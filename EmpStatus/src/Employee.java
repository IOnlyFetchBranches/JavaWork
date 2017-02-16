import java.util.*;
import java.io.*;
public class Employee {
    public int id;
    private double[] hours={0,0,0,0,0,0,0};
    private String name,position,status;
    private long salary;

    Employee(){
        id=genId(4);
        name="None";
        position="default";
        salary=10000;
    }
    Employee(String n, String stat,String p, long s){
        id=genId(4);
        name=n;
        position=p;
        salary=s;
        status=stat;
    }
    private int genId(int base){
        StringBuilder sb=new StringBuilder();
        int[] gen=new int[base];
        for (int x =0; x<base;x++){
            gen[x]=(int)((Math.random()*9)+0);
            sb.append(gen[x]);



        }
        sb.insert(0,100);
        int ids=Integer.parseInt(sb.toString());
        //System.out.println(id);
        return ids;
   }
   public String get(String type){
        String t=type;
        switch(t){
            case "name":


                return name;
            case "salary":
            case "pay":
            case "wage":
                String pay=salary + " ";

                return pay;
            case "status":

                return status;
            case "job":

                return position;
            default:
                return "void";
        }
   }


}
