
import java.util.Hashtable;

//import java.util.Hashtable;

/**
 * inventory.java
 * Description: This class will create inventory object to get stored in hash table, it will
 * have get/set methods to modify or remove the objects
 * @author Aliza Abbas
 */
public class inventory {
     private String title;
     private String desc;
     private String quantity;
     private String price;
     private String dateofAuc;
     private String timeOfQuery;
     private String sellerPlace;
     private String itemPic;
     public inventory(String tit, String des ,String quant, String pr,  String d, String t, String sell, String pic){
         title = tit;
         quantity = quant;
         price = pr;
         timeOfQuery = t;
         dateofAuc = d;
         sellerPlace = sell;
         itemPic = pic;
         desc = des;
     }
      public String getDescription(){
         return this.desc;
     }
     public String getSellerPlace(){
         return this.sellerPlace;
     
     }public String getItemPic(){
         return this.itemPic;
     }
     
     public String getTitle(){
         return this.title;
     }

     public String getQuantity(){
         return this.quantity;
     }
     public String getPrice(){
         return this.price;
     }
     public String getDateofAuc(){
         return this.dateofAuc;
     }
     public String getTimeOfQuery(){
         return this.timeOfQuery;
     }
     
     public void setTitle(String inv){
         this.title = inv;
     }

     public void setQuantity(String q){
         int temp = Integer.parseInt(this.quantity) + Integer.parseInt(q);
         this.quantity = Integer.toString(temp);
     }
     public void setQuantityRemove(String q){
         int temp = Integer.parseInt(this.quantity) - Integer.parseInt(q);
         this.quantity = Integer.toString(temp);
     }
     public void setPrice(String p){
         this.price = p;
     }
     public void setDateofAuc(String d){
         this.dateofAuc = d;
     }
     public void setTimeOfQuery(String t){
         this.timeOfQuery = t;
     }
     
     public void setSellerPlace(String sellP){
         this.sellerPlace = sellP;
     }
    
     public void setPic(String pic){
         this.itemPic = pic;
     }
     public void setDescr(String des){
         this.desc = des;
     }
     
     public inventory findItem(Hashtable<String, inventory> hash, String name){         
                 return hash.get(name);            
     }
     public String toString(){
         
         return (title + " | "+desc + " | "  + quantity + " | " + price + " | " + dateofAuc + " | " + timeOfQuery + " | " + sellerPlace + " | " + itemPic);
     }
             
}
