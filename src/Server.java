import java.io.IOException;
import java.awt.Font;
import java.io.BufferedReader;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.io.InputStreamReader;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Server implements Runnable
{
    Socket csocket;
    char threadType;

    static final String newline = "\n";
    static int first_time = 1;

    static int port_num = 3333;

    static int numOfConnections = 0;
    static int numOfMessages = 0;
    static int max_connections = 5;
    static int numOfTransactions = 0;
    static JTextArea ta = new JTextArea();
    static JFrame f = null;

    Server(Socket csocket)
    {
        this.csocket = csocket;
    }

    public static void main(String args[])// throws Exception
    {
        boolean sessionDone = false;

        ServerSocket ssock = null;

        ta.setFont(new Font("Ariel", Font.BOLD, 18));

        JScrollPane txt_more_info_pane = new JScrollPane(ta);
        txt_more_info_pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        txt_more_info_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        f = new JFrame();
        f.setSize(1400, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(txt_more_info_pane);
        f.setLocationRelativeTo(null);
        f.setTitle("Simple Socket Server Application");
        f.setVisible(true);

        try
        {
            ssock = new ServerSocket(port_num);
        }
        catch (BindException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // update the status text area to show progress of program
        try
        {
            InetAddress ipAddress = InetAddress.getLocalHost();
            ta.append("IP Address : " + ipAddress.getHostAddress() + newline);
        }
        catch (UnknownHostException e1)
        {
            e1.printStackTrace();
        }

        ta.append("Listening on port " + port_num + newline);
        ta.setCaretPosition(ta.getDocument().getLength());
        ta.repaint();

        sessionDone = false;
        while (sessionDone == false)
        {
            Socket sock = null;
            try
            {
                // blocking system call
                sock = ssock.accept();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            // update the status text area to show progress of program
            ta.append("Client Connected : " + sock.getInetAddress() + newline);
            ta.setCaretPosition(ta.getDocument().getLength());
            ta.repaint();

            new Thread(new Server(sock)).start();
        }

        try
        {
            ssock.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // This is the thread code that ALL clients will run()
    public void run()
    {
        try
        {
            boolean session_done = false;
            long threadId;
            String clientString;

            threadId = Thread.currentThread().getId();

            numOfConnections++;

            ta.append("Num of Connections = " + numOfConnections + newline);
            ta.setCaretPosition(ta.getDocument().getLength());
            ta.repaint();

            PrintStream pstream = new PrintStream (csocket.getOutputStream());
            BufferedReader rstream = new BufferedReader(new InputStreamReader(csocket.getInputStream()));

            while (session_done == false)
            {
                if (rstream.ready())   // check for any data messages
                {
                    clientString = rstream.readLine();

                    // update the status text area to show progress of program
                    ta.append("RECV : " + clientString + newline);
                    ta.setCaretPosition(ta.getDocument().getLength());
                    ta.repaint();
                    // update the status text area to show progress of program
                    ta.append("RLEN : " + clientString.length() + newline);
                    ta.setCaretPosition(ta.getDocument().getLength());
                    ta.repaint();

                    if (clientString.length() > 128)
                    {
                        session_done = true;
                        continue;
                    }

                    if (clientString.contains("quit"))
                    {
                        session_done = true;
                    }
                    else if (clientString.contains("QUIT"))
                    {
                        session_done = true;
                    }
                    else if (clientString.contains("Quit"))
                    {
                        session_done = true;
                    }
                    else if (clientString.contains("Date>"))
                    {
                        numOfMessages++;

                        // Create an instance of SimpleDateFormat used for formatting
                        // the string representation of date (month/day/year)
                        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

                        // Get the date today using Calendar object.
                        Date today = Calendar.getInstance().getTime();

                        // Using DateFormat format method we can create a string
                        // representation of a date with the defined format.
                        String reportDate = df.format(today);

                        // Print what date is today!
                        pstream.println("Num Of Messages : " + numOfMessages + "   Simple Date: " + reportDate);
                    }
                }

                Thread.sleep(500);
            }

            numOfConnections--;

            // close client socket
            csocket.close();

            // update the status text area to show progress of program
            ta.append("Child Thread : " + threadId + " : is Exiting!!!" + newline);
            ta.append("Num of Connections = " + numOfConnections);
            ta.setCaretPosition(ta.getDocument().getLength());
            ta.repaint();

        } // end while loop

        catch (SocketException e)
        {
            // update the status text area to show progress of program
            ta.append("ERROR : Socket Exception!" + newline);
            ta.setCaretPosition(ta.getDocument().getLength());
            ta.repaint();
        }
        catch (InterruptedException e)
        {
            // update the status text area to show progress of program
            ta.append("ERROR : Interrupted Exception!" + newline);
            ta.setCaretPosition(ta.getDocument().getLength());
            ta.repaint();
        }
        catch (UnknownHostException e)
        {
            // update the status text area to show progress of program
            ta.append("ERROR : Unkonw Host Exception" + newline);
            ta.setCaretPosition(ta.getDocument().getLength());
            ta.repaint();
        }
        catch (IOException e)
        {
            // update the status text area to show progress of program
            ta.append("ERROR : IO Exception!" + newline);
            ta.setCaretPosition(ta.getDocument().getLength());
            ta.repaint();
        }
        catch (Exception e)
        {
            numOfConnections--;

            // update the status text area to show progress of program
            ta.append("ERROR : Generic Exception!" + newline);
            ta.setCaretPosition(ta.getDocument().getLength());
            ta.repaint();
        }

    }  // end run thread method

}