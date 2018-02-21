
package cs370phase1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 * CS370Phase1.java
 * Description: This class will read the file make the objects of inventory class and store
 * them in hashtable.
 * ATTN: For the text files, please attach the ones that has a format of:
 * [inventory number, description, quantity, price, date , time ] per line
 * e.g : 1, apple, 23, $3 , 10/02/17 , 1:05pm 
 * @author alizaabbas
 * 
 */
public class CS370Phase1 {
    
    /**
     * @param args the command line arguments
     */
    public static Hashtable<String, inventory> grocery;
    public static int Max_Capacity = 100;
    public static void main(String[] args) throws FileNotFoundException, IOException {
        grocery = new Hashtable<String, inventory>(100);
        
        File in = new File("/Users/alizaabbas/Documents/netbeans-proj/CS370Phase1/src/cs370phase1/inv.txt");
        Scanner scan = new Scanner(in);
        while(scan.hasNextLine()){
            
            String str = scan.nextLine();
            StringTokenizer token = new StringTokenizer(str, ",");
            String invNum = token.nextToken().trim();
            String desc = token.nextToken().trim();
            String quant = token.nextToken().trim();
            String pr = token.nextToken().trim();
            String date = token.nextToken().trim();
            String time = token.nextToken().trim();
            grocery.put(desc, new inventory(invNum, quant, pr, date, time));
        }
        
        GUI myGui = new GUI();
    }
    
    
}
