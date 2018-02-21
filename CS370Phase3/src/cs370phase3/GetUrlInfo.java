 /**
  * Title: GetUrlInfo.java
  * Description: this class helps extract the filter items list by using regex pattern matcher
  * from the html file that it reads thru the scanner
  *
  * @author alizaabbas
  */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;

public class GetUrlInfo {
    
    private String URLstr;
    private ArrayList<String> itemInfo;
    public GetUrlInfo(String url){
        URLstr = url;
        itemInfo = new ArrayList<>();
    }
    
    public ArrayList<String> getURLInfo() throws MalformedURLException, IOException{
        URL url = new URL(URLstr);
        HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while((line = in.readLine()) != null){
            String re1="Product #: </span>([0-9]+)</div>";	// Tag 1
            String re2="(<[^>]+>)(\\$[0-9]+(?:\\.[0-9][0-9])?)(?![\\d](<[^>]+>))";// Dollar Amount 1
            String re3="(data-src=)(\".*?\")";
            
            Pattern p1 = Pattern.compile(re1,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Pattern p2 = Pattern.compile(re2,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Pattern p3 = Pattern.compile(re3,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            
            Matcher m1 = p1.matcher(line);
            Matcher m2 = p2.matcher(line);
            Matcher m3 = p3.matcher(line);
            
            if (m1.find())
            {
                String tag1=m1.group(1).trim();
                itemInfo.add(tag1);  // product
            }
            if (m2.find())
            {
                String tag2=m2.group(2).trim();
                itemInfo.add(tag2);
            }
            if (m3.find())
            {
                String tag3=m3.group(2).trim();
                itemInfo.add(tag3);
            }
            
            
        }
        in.close();
        return itemInfo;
    }
    
}
