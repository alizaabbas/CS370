
package cs370phase2;
/**
 * GetURLInfo.java - This class extracts the information from the url files given in input.txt
 * output there info in outputFile.txt and saves the file on the computer 
 *This class uses the example based on the code from from the book _Java in a Nutshell_ by David Flanagan.
 * Written by David Flanagan.  Copyright (c) 1996 O'Reilly & Associates.
 * 
 * @alizaabbas
**/

import java.awt.image.BufferedImage;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

public class GetURLInfo {
    
    
    public static void printinfo(File input) throws IOException {
        int i = 0;
        
        Scanner scan = new Scanner(input);
        PrintWriter out = new PrintWriter(new FileWriter("/Users/alizaabbas/Documents/netbeans-proj/CS370Phase2/src/cs370phase2/outputFile.txt"));
        while(scan.hasNextLine()){
            BufferedImage image = null;
            
            String line = scan.nextLine();
            
            String format = line.substring(line.length()-3);
            String format2 = line.substring(line.length()-4);
            
            URL url = new URL(line);
            URLConnection connection = url.openConnection();
            if(format.equals("htm") || format.equals("txt")||format.equals("pdf")||format2.equals("html")||format2.equals("jpeg") ||format.equals("jpg")){
                
                try  {
                    out.println(connection.getURL().toExternalForm() + ":");
                    out.println("  Content Type: " + connection.getContentType());
                    out.println("  Content Length: " + connection.getContentLength());
                    out.println("  Last Modified: " + new Date(connection.getLastModified()));
                    out.println("  Expiration: " + connection.getExpiration());
                    out.println("  Content Encoding: " + connection.getContentEncoding());
                    
                }
                catch (IllegalArgumentException ex) {
                    System.out.println(ex.getLocalizedMessage());
                }
                
            }
            
            if(format.equals("htm") || format.equals("txt") ||format2.equals("html")){
                File file = new File("/Users/alizaabbas/Documents/netbeans-proj/CS370Phase2/src/cs370phase2/file"+ i + "." + format);
                file.createNewFile();
                DataInputStream in = new DataInputStream(connection.getInputStream());
                String line1 = in.readLine();
                PrintWriter outUrl = new PrintWriter(new FileWriter(file));
                int lineCounter = 0;
                while(line1 != null) {
                    outUrl.println(line1);
                    line1 = in.readLine();
                    lineCounter++;
                } // for
                outUrl.close();
                out.println("  Name of the file: " + file.getName());
                out.println("  Number of lines: " + lineCounter);
                out.println();
            }
            else if(format.equals("jpg") || format.equals("gif") ||format2.equals("jpeg")){
                image = ImageIO.read(url);
                if(format2.equals("jpeg")){
                    File file = new File("/Users/alizaabbas/Documents/netbeans-proj/CS370Phase2/src/cs370phase2/file"+ i + "." + format2);
                    file.createNewFile();
                    ImageIO.write(image, format , file);
                    out.println("  Name of the file: " + file.getName());
                }
                else{
                    File file = new File("/Users/alizaabbas/Documents/netbeans-proj/CS370Phase2/src/cs370phase2/file"+ i + "." + format);
                    file.createNewFile();
                    ImageIO.write(image, format , file);
                    out.println("  Name of the file: " + file.getName());
                }
                out.println();
            }
            else{
                File file = new File("/Users/alizaabbas/Documents/netbeans-proj/CS370Phase2/src/cs370phase2/file"+ i + "." + format);
                file.createNewFile();
                InputStream in = url.openStream();
                byte[] ba1 = new byte[1024];
                FileOutputStream fos1 = new FileOutputStream(file);
                int c;
                
                while ((c = in.read(ba1)) != -1) {
                    fos1.write(ba1,0,c);
                }
                fos1.flush();
                fos1.close();
                in.close();
                out.println("  Name of the file: " + file.getName());
                out.println();
            }
            i++;
        } // printinfo
        out.close();
    }
    public static void main(String[] args) throws IOException
    {
        File input = new File("/Users/alizaabbas/Documents/netbeans-proj/CS370Phase2/src/cs370phase2/input.txt");
        
        printinfo(input);
        
    }
} // GetURLInfo
