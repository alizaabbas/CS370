 /**
  * Title: CS370Phase3.java
  * Description: This class scans the input files and stores in the hash table,
  * generates a GUI class helping to create the inventory
  * @author alizaabbas
  */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CS370Phase3 {
    public static Hashtable<String, inventory> shopdb;
    public static int Max_Capacity = 100;
    public static String outputFile = "";
    public static String logFile = "";
    public static String inputFile = "";
    public static ArrayList<String[]> log;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException{
        shopdb = new Hashtable<String, inventory>(100);
        log = new ArrayList<String[]>();
        inputFile = args[0];
        outputFile = args[2];
        logFile = args[1];
        File in1 = new File(inputFile);
        Scanner scan1 = new Scanner(in1);
        while(scan1.hasNextLine()){
            
            String str = scan1.nextLine();
            if(!str.trim().equals("")){
                StringTokenizer token = new StringTokenizer(str, ",");
                String invNum = token.nextToken().trim();
                String title = token.nextToken().trim();
                String desc = token.nextToken().trim();
                String quant = token.nextToken().trim();
                String pr = token.nextToken().trim();
                String date = token.nextToken().trim();
                String time = token.nextToken().trim();
                String sellerPlace = token.nextToken().trim();
                String pic = token.nextToken().trim();
                shopdb.put(invNum, new inventory(title,desc, quant, pr, date, time,sellerPlace,pic ));
            }
        }
        File in = new File(outputFile);
        Scanner scan = new Scanner(in);
        while(scan.hasNextLine()){
            
            String str = scan.nextLine();
            if(!str.trim().equals("")){
                StringTokenizer token = new StringTokenizer(str, ",");
                String invNum = token.nextToken().trim();
                String title = token.nextToken().trim();
                String desc = token.nextToken().trim();
                String quant = token.nextToken().trim();
                String pr = token.nextToken().trim();
                String date = token.nextToken().trim();
                String time = token.nextToken().trim();
                String sellerPlace = token.nextToken().trim();
                String pic = token.nextToken().trim();
                shopdb.put(invNum, new inventory(title,desc, quant, pr, date, time,sellerPlace,pic ));
            }
        }
        File out = new File(logFile);
        Scanner scanOut = new Scanner(out);
        while(scanOut.hasNextLine()){
            String[] listOfLogs = new String[2];
            String str1 = scanOut.nextLine();
            System.out.println(str1);
            if(!str1.trim().equals("")){
                StringTokenizer token = new StringTokenizer(str1, ".");
                String transName = token.nextToken().trim();
                String parameter = token.nextToken().trim();
                listOfLogs[0] = transName;
                listOfLogs[1] = parameter;
                log.add(listOfLogs);
            }
        }
        
        GUI myGui = new GUI();
    }
    
}
