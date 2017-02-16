import java.util.*;
import java.io.*;
public class Main {
    private static List<Employee> roster =new ArrayList<>();
    static Main m=new Main();
    public static void main(String[] args){


        m.manage(false,true);

        //will add above to its own method but for testing
        System.out.println("Welcome! You currently have "+ roster.size() + " workers registered:");
            for (Employee x : roster){
            System.out.println(x.id);
            }

    }
    void manage(boolean test,boolean manage){
        File data=new File("data.txt");
        try{
            PrintWriter pw;
            if(data.exists()){
                pw =new PrintWriter(new FileWriter(data,true));
            }
            else {
                pw = new PrintWriter(new FileWriter(data));
            }
            if (test) {
                Employee emp = new Employee();
                pw.write("\n" + emp.id + ";" + emp.get("name") + ";" + emp.get("status") + ";" + emp.get("job") + ";" + emp.get("pay"));
                pw.close();
                roster.add(emp);
            }
            if (manage){

                System.out.println("Manage Mode \n To begin or if this is your first time, please enter: 1 \n If you would like to add an employee, enter 2 \n If you would like to reset your data, enter 3 \n To backup your data, enter 4");
                Scanner input=new Scanner(System.in);
                boolean error=false;
                do {
                    error=false;
                    try {
                        int in1=input.nextInt();
                        switch(in1) {
                            case 1:
                                pw = new PrintWriter(new FileWriter(data));
                                roster.clear();
                                System.out.println("Data Cleared");

                                m.memberSetup(false);
                                break;
                            default:
                                System.out.println("I'm Sorry I Did'nt Quite Catch That!");
                                error=true;

                        }

                    } catch (Exception e) {
                        System.err.println(e.getLocalizedMessage());
                        error = true;
                    }
                }while(error);
            }
        }catch(Exception e){System.err.println("Exception Caught");}
    }
    void memberSetup(boolean override){
        boolean error=false;
        boolean cont=false;
        System.out.println("Please enter the following: \n Name \n Status of Employment \n Position \n Salary \n In that order hitting enter after each one.");
        do{
           try{
               cont=false;
               error=false;
               Scanner input=new Scanner(System.in);
               String n=input.next();
               String stat=input.next();
               String p=input.next();
               Long s=input.nextLong();
               Employee emp=new Employee(n,stat,p,s);
               roster.add(emp);
               System.out.println("Created Successfully, type y to add more or n to move on!");
               boolean check1=false;

               do{
                   try {
                       check1=false;
                       String u;
                       String u1 = input.next(); //input for cont, then covert to lower
                       if (u1.isEmpty()){
                          u=u1;
                       }
                       else {
                           u = u1.toLowerCase();
                       }
                       switch (u){
                           case "y":
                           case "yes":
                               cont=true;

                               break;
                           case "n":
                               cont=false;
                               check1=false;
                               break;
                           default:
                               System.out.println("Invalid Response, please type y or nothing");
                               check1=true;
                       }
                   }catch(Exception e){check1=true;}
               }while(check1);


           }catch(Exception e){
               System.err.println("\nException Caught, check your order and try again.");
               error=true;
           }

        }while(error || cont);

        //spreadsheet block
        System.out.println("Creating Spreadsheet.");
        /* Scanner input2=new Scanner(System.in);
        String u2=input2.next();
        boolean chk1=u2.isEmpty();
        boolean chk2=u2.toLowerCase().contains("y");
        boolean chk3=u2.toLowerCase().contains("n");
        */
        //create instance of xls class
        Tools.xls xls=new Tools.xls();
        for(int x=0;x<roster.size();x++){
            System.out.println(x + " "+ roster.size());
            long pay=(Long.parseLong((roster.get(x).get("pay")))); //problem is here
            xls.book(roster.get(x).id,roster.get(x).get("name"),roster.get(x).get("status"),roster.get(x).get("job"),pay,x);

        }

    }

}