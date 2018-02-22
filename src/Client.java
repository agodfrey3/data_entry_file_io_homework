import java.net.InetAddress;
import java.net.Socket;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Client implements Runnable {

    private Socket socket;
    private String message;

    public Client(InetAddress server_address, int port, String msg) {
        try {
            this.socket = new Socket(server_address, port);
            this.message = msg;
        }
        catch (Exception e) {
            System.out.println("Error initializing socket.\n");
            e.printStackTrace();
        }
    }

    public void run() {
        try
        {
            //Send the message to the server
            OutputStream os = this.socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            bw.write(message);
            bw.flush();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            //Closing the socket
            try
            {
                this.socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}