package cs370phase1;

import java.util.Hashtable;

/**
 * inventory.java
 * Description: This class will create inventory object to get stored in hash table, it will
 * have get/set methods to modify or remove the objects
 * @author Aliza Abbas
 */
public class inventory {
     private String invNumber;
     private String quantity;
     private String price;
     private String date;
     private String time;
     
     public inventory(String num, String quant, String pr,  String d, String t){
         invNumber = num;
         quantity = quant;
         price = pr;
         time = t;
         date = d;
     }

    public inventory(String invNum, String desc, String quant, String pr, String date, String time, String sellerPlace, String pic) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
     
     public String getInventoryNumber(){
         return this.invNumber;
     }

     public String getQuantity(){
         return this.quantity;
     }
     public String getPrice(){
         return this.price;
     }
     public String getDate(){
         return this.date;
     }
     public String getTime(){
         return this.time;
     }
     
     public void setInvNum(String inv){
         this.invNumber = inv;
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
     public void setDate(String d){
         this.date = d;
     }
     public void setTime(String t){
         this.time = t;
     }
     
     public inventory findItem(Hashtable<String, inventory> hash, String name){         
                 return hash.get(name);            
     }
     public String toString(){
         
         return (invNumber + " | "  + quantity + " | " + price + " | " + date + " | " + time);
     }
             
}
