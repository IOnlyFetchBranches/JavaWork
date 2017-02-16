package Tools;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.concurrent.*;
/**
 * Created by demarcus-joachim on 1/19/2017.
 */
public class cWindow extends WindowAdapter implements WindowListener,  ActionListener, Runnable {
    public JFrame frame;
    private JTextArea ta;
    private Thread reader1, reader2;
    private boolean quit;
    private TextField tf;

    private final PipedInputStream pin1 = new PipedInputStream();
    private final PipedInputStream pin2 = new PipedInputStream();






    public cWindow() {
        //create all components
        frame = new JFrame("Console");

        //frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = new Dimension(((int) (screenSize.getWidth()) / 2), ((int) (screenSize.getHeight()) / 2));
        int x = frameSize.width / 2;
        int y = frameSize.height / 2;
        frame.setBounds(x, y, frameSize.width, frameSize.height);


        ImageIcon icon = new ImageIcon("Tools/maxresdefault.jpg");


        ta = new JTextArea();
        ta.setEditable(false);
        JButton button = new JButton("clear");
        JLabel img=new JLabel(icon);

        frame.getContentPane().setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(ta);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);



        frame.getContentPane().add(button, BorderLayout.SOUTH);
        frame.addWindowListener(this);
        button.addActionListener(this);

        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);

        try {
            PipedOutputStream pout = new PipedOutputStream(this.pin1);
            System.setOut(new PrintStream(pout, true)); //set system to stream to pout into our thread into the console
        } catch (IOException | SecurityException iose) {
            ta.append(iose.getLocalizedMessage() + '\n' + iose.getStackTrace().toString());
        }
        try {
            PipedOutputStream pout2 = new PipedOutputStream(this.pin2);
            System.setErr(new PrintStream(pout2, true));
        } catch (IOException | SecurityException iose) {
            ta.append(iose.getLocalizedMessage() + '\n' + iose.getStackTrace().toString());
        }
        quit = false; // quit threads;

        //init read threads

        reader1 = new Thread(this);
        reader1.setDaemon(true);
        reader1.start();

        reader2 = new Thread(this);
        reader2.setDaemon(true);
        reader2.start();

        ta.setForeground(Color.WHITE);
        ta.setBackground(Color.BLACK);
        ta.append("Welcome\n // Thanks to RJHM van den Bergh's read/multithread idea\n");


    }
    //with thanks to RJHM van den Bergh , rvdb@comweb.nl (no clue about byte streaming haha)
    public synchronized String readLine(PipedInputStream in) throws IOException
    {
        String input="";
        do
        {
            int available=in.available();
            if (available==0) break;
            byte b[]=new byte[available];
            in.read(b);
            input=input+new String(b,0,b.length);
        }while( !input.endsWith("\n") &&  !input.endsWith("\r\n") && !quit);
        return input;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ta.setText("");
    }
    public synchronized void clear(int millsec) {
        try {

                wait(millsec);

            ta.setText("");
        } catch (Exception e) {
            System.out.println("Time Error");
        }
    }

    @Override
    //custom run method to satisfy abstract class
    public synchronized void run() {

        try {
            while (Thread.currentThread() == reader1) {
                try {
                    this.wait(100);}catch (Exception e) {ta.append("Caught timer Error");}
                    try{
                    if (pin1.available() != 0) {
                        String input = this.readLine(pin1); //send input stream to teh reader
                        ta.append(input);
                    }
                    if (quit) return;

                } catch (Exception ie) {
                }
            }
            while (Thread.currentThread() == reader2) {
                try {
                    this.wait(100); }catch (Exception e) {ta.append("Caught timer Error");}
                    try{
                    if (pin2.available() != 0) {
                        String input = this.readLine(pin2);
                        ta.append(input);
                    }
                    if (quit) return;

                } catch (Exception ee) {
                    ta.append("\nConsole reports an Internal error.");
                    ta.append("The error is  : " + ee);
                }
            }
        } catch (Exception eee) {
            ta.append("Error " + eee.getLocalizedMessage());
        }

    }

  // public static void main(String[] args) {


   //     cWindow c = new cWindow();


   // }

}
