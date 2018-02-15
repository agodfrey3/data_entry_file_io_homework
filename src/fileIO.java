import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class fileIO
{
    public void wrTransactionData(String fn, String ln, String ad, String city,
                                  String state, String zip, String email)
    {
        // output the gold header information
        FileWriter fwg = null;
        try
        {
            fwg = new FileWriter("transactionLog.txt", true);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        BufferedWriter bwg = new BufferedWriter(fwg);
        PrintWriter outg   = new PrintWriter(bwg);

        String timeStamp = new SimpleDateFormat("MM-dd-yyyy HH.mm.ss").format(new Date());

        outg.println(timeStamp + " : "+ fn + " " + ln + " "
                + ad + " " + city + " " + state + " " + zip + " "
                + email);

        outg.close();
    }
}