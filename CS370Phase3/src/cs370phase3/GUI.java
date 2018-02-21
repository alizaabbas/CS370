/**
 * GUI.java
 * Description: This class will use GUI to ask user for inputs to create,
 * modify and remove hashtable contents, it will provide users with advance search option
 * that will search the shopgoodwill website with the filter specified and display the items
 * to the users.
 *
 * @author Aliza Abbas
 *
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Set;

public class GUI implements ActionListener {
    private JLabel label = new JLabel("choose the option");
    private JFrame frame = new JFrame();
    
    private JButton selectButton ;
    private JButton addButton ;
    private JButton removeButton ;
    private JButton advancedSearchButton;
    private JButton contactSeller;
    private JButton addToWatch;
    public GUI() {
        
        selectButton = new JButton("SELECT");
        addButton = new JButton("ADD A NEW ITEM OR ADD QUANTITY TO EXISTING ITEM");
        removeButton = new JButton("DELETE ITEM OR DECREASE QUANTITY OF EXISTING ITEM");
        advancedSearchButton = new JButton("Click for ADVANCE SEARCH ON shopwill.com");
        contactSeller = new JButton("Leave a message for the seller");
        addToWatch = new JButton("add all items of specified price range to your watch list");
        selectButton.addActionListener(this);
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        advancedSearchButton.addActionListener(this);
        
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(60, 60, 20, 60));
        panel.setLayout(new GridLayout(0, 1));
        
        panel.add(selectButton);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(advancedSearchButton);
        panel.add(contactSeller);
        
        panel.add(label);
        
        frame.setLocationRelativeTo(null);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Inventory");
        frame.pack();
        frame.setVisible(true);
    }
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    DateTimeFormatter dt = DateTimeFormatter.ofPattern("HH:MM");
    LocalDate localDate = LocalDate.now();
    LocalTime localTime = LocalTime.now();
    
    public void actionPerformed(ActionEvent e) {
        
        /********SelectButton***********/
        if(e.getSource().equals(selectButton)){
            
            String title = JOptionPane.showInputDialog(null, "Enter the Inventory Number for your item");
            if(CS370Phase3.shopdb.containsKey(title)){
                
                JOptionPane.showMessageDialog(null, title +" | " + CS370Phase3.shopdb.get(title).toString());
                String d = dtf.format(localDate);
                String t = Integer.toString(localTime.getHour()) + ":" + Integer.toString(localTime.getMinute());
                CS370Phase3.shopdb.get(title).setDateofAuc(d);
                CS370Phase3.shopdb.get(title).setTimeOfQuery(t);
            }
            else
                JOptionPane.showMessageDialog(null, "Sorry, this item is not found in the inventory");
        }
        
        //***********ADD BUTTON / update button**********//
        else if(e.getSource().equals(addButton)){
            String invNum = JOptionPane.showInputDialog(null, "enter inventory number");
            String TitleKey = JOptionPane.showInputDialog(null, "Item Title");
            String desc = JOptionPane.showInputDialog(null, "Item Description");
            String quant = JOptionPane.showInputDialog(null, "enter quantity");
            String pri = JOptionPane.showInputDialog(null, "enter price");
            String d = dtf.format(localDate);
            String t = Integer.toString(localTime.getHour()) + ":" + Integer.toString(localTime.getMinute());
            String sell = JOptionPane.showInputDialog(null, "enter the seller place ");
            String pic = JOptionPane.showInputDialog(null, "enter the picture URL");
            
            if(CS370Phase3.shopdb.size() < CS370Phase3.Max_Capacity){
                if(CS370Phase3.shopdb.containsKey(invNum)){
                    CS370Phase3.shopdb.get(invNum).setQuantity(quant);
                    CS370Phase3.shopdb.get(invNum).setDescr(desc);
                    CS370Phase3.shopdb.get(invNum).setPrice(pri);
                    CS370Phase3.shopdb.get(invNum).setDateofAuc(d);
                    CS370Phase3.shopdb.get(invNum).setTimeOfQuery(t);
                    CS370Phase3.shopdb.get(invNum).setSellerPlace(sell);
                    CS370Phase3.shopdb.get(invNum).setPic(pic);
                    
                    String[] logEntry = new String[2];
                    logEntry[0] = "MODIFY";
                    logEntry[1] = invNum + " , " + TitleKey + " , " + quant + " , " +desc + " , " + pri + " , " +d + " , " +t + " , " +sell + " , " + pic;
                    CS370Phase3.log.add(logEntry);
                    
                }
                else{
                    CS370Phase3.shopdb.put(invNum, new inventory(TitleKey,desc, quant, pri, d, t, sell,pic));
                    
                    String[] logEntry = new String[2];
                    logEntry[0] = "INSERT";
                    logEntry[1] = invNum + " , " + TitleKey + " , " + quant + " , " +desc + " , " + pri + " , " +d + " , " +t + " , " +sell + " , " + pic;
                    CS370Phase3.log.add(logEntry);
                }
                
            }
            else
                JOptionPane.showMessageDialog(null, "The Inventory is full, can't add more items");
            
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(CS370Phase3.outputFile); //"/Users/alizaabbas/Documents/netbeans-proj/CS370Phase3/src/cs370phase3/inv.txt"
                PrintWriter printWriter = new PrintWriter(fileWriter);
                Set<String> keys = CS370Phase3.shopdb.keySet();
                for(String key: keys){
                    printWriter.println(key + " , " +
                            CS370Phase3.shopdb.get(key).getTitle() + " , " +
                            CS370Phase3.shopdb.get(key).getDescription() + " , " +
                            CS370Phase3.shopdb.get(key).getQuantity() + " , " +
                            CS370Phase3.shopdb.get(key).getPrice() + " , " +
                            CS370Phase3.shopdb.get(key).getDateofAuc() + " , " +
                            CS370Phase3.shopdb.get(key).getTimeOfQuery() + " , " +
                            CS370Phase3.shopdb.get(key).getSellerPlace() + " , " +
                            CS370Phase3.shopdb.get(key).getItemPic());
                }
                printWriter.close();
            }
            catch (IOException ex) {
                System.out.println(ex.getLocalizedMessage());
            }
            
            FileWriter fileWriter1;
            try {
                fileWriter1 = new FileWriter(CS370Phase3.logFile); //"/Users/alizaabbas/Documents/netbeans-proj/CS370Phase3/src/cs370phase3/inv.txt"
                PrintWriter printWriter1 = new PrintWriter(fileWriter1);
                int i = CS370Phase3.log.size()-1;
                while(i >= 0){
                    String[] temp = CS370Phase3.log.get(i);
                    printWriter1.println(temp[0] + " . " + temp[1] );
                    i--;
                }
                printWriter1.close();
            }
            catch (IOException ex) {
                System.out.println(ex.getLocalizedMessage());
            }
            
        }
        /**************REMOVE BUTTON**********/
        else if(e.getSource().equals(removeButton)){
            String TitleKey = JOptionPane.showInputDialog(null, "Enter the Inventory Number of the item to be removed");
            String quantity = JOptionPane.showInputDialog(null, "Enter the quantity to be removed, write 'all' to delete the entire record");
            String date = dtf.format(localDate);
            String time = Integer.toString(localTime.getHour()) + ":" + Integer.toString(localTime.getMinute());
            
            if(CS370Phase3.shopdb.containsKey(TitleKey)){
                if(quantity.equals("all")){
                    CS370Phase3.shopdb.remove(TitleKey);
                    String[] logEntry = new String[2];
                    logEntry[0] = "DELETE";
                    logEntry[1] = TitleKey + " , " + CS370Phase3.shopdb.get(TitleKey).getTitle()+ " , " +date + " , " + time ;
                    CS370Phase3.log.add(logEntry);
                    
                }
                else{
                    CS370Phase3.shopdb.get(TitleKey).setQuantityRemove(quantity);
                    CS370Phase3.shopdb.get(TitleKey).setDateofAuc(date);
                    CS370Phase3.shopdb.get(TitleKey).setTimeOfQuery(time);
                    String[] logEntry = new String[2];
                    logEntry[0] = "MODIFY";
                    logEntry[1] = TitleKey + " , " + CS370Phase3.shopdb.get(TitleKey).getTitle()+ " , " + quantity + " , " +date + " , " + time ;
                    CS370Phase3.log.add(logEntry);
                    
                }
            }
            
            else
                JOptionPane.showMessageDialog(null, "Sorry, this item is not found in the inventory, try again!");
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(CS370Phase3.outputFile);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                Set<String> keys = CS370Phase3.shopdb.keySet();
                for(String key: keys){
                    printWriter.println(key + " , " + CS370Phase3.shopdb.get(key).getTitle() + " , "+
                            CS370Phase3.shopdb.get(key).getDescription() + " , " +
                            CS370Phase3.shopdb.get(key).getQuantity() + " , " +
                            CS370Phase3.shopdb.get(key).getPrice() + " , " +
                            CS370Phase3.shopdb.get(key).getDateofAuc() + " , " +
                            CS370Phase3.shopdb.get(key).getTimeOfQuery() + " , " +
                            CS370Phase3.shopdb.get(key).getSellerPlace() + " , " +
                            CS370Phase3.shopdb.get(key).getItemPic());
                }
                printWriter.close();
            }
            catch (IOException ex) {
                System.out.println(ex.getLocalizedMessage());
            }
            
            FileWriter fileWriter2;
            try {
                fileWriter2 = new FileWriter(CS370Phase3.logFile); //"/Users/alizaabbas/Documents/netbeans-proj/CS370Phase3/src/cs370phase3/inv.txt"
                PrintWriter printWriter2 = new PrintWriter(fileWriter2);
                int i = CS370Phase3.log.size()-1;
                while(i >= 0){
                    String[] temp = CS370Phase3.log.get(i);
                    printWriter2.println(temp[0] + " . " + temp[1] );
                    i--;
                }
                printWriter2.close();
            }
            catch (IOException ex) {
                System.out.println(ex.getLocalizedMessage());
            }
            
        }
        //********* ADVANCE SEARCH BUTTON *******///
        else{
            if(e.getSource().equals(advancedSearchButton)){
                JFrame advanceframe = new JFrame();
                JTextField titleButton = new JTextField(20);
                JTextField priceLow = new JTextField(4);
                JTextField priceHigh = new JTextField(4);
                JButton OK = new JButton("ok");
                
                JPanel panel1 = new JPanel();
                panel1.setBorder(BorderFactory.createEmptyBorder(60, 60, 20, 60));
                panel1.setLayout(new GridLayout(0, 1));
                panel1.add(titleButton);
                panel1.add(priceLow);
                panel1.add(priceHigh);
                panel1.add(OK);
                panel1.add(addToWatch);
                
                advanceframe.setLocationRelativeTo(null);
                advanceframe.add(panel1, BorderLayout.CENTER);
                advanceframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                advanceframe.setTitle("Advance Search");
                advanceframe.pack();
                advanceframe.setVisible(true);
                
                titleButton.addActionListener(this);
                priceLow.addActionListener(this);
                priceHigh.addActionListener(this);
                OK.addActionListener(new ActionListener(){
                    
                    public void actionPerformed(ActionEvent event){
                        
                        if(event.getSource().equals(OK)){
                            String Title = titleButton.getText().trim();
                            String plow = priceLow.getText().trim();
                            String phigh = priceHigh.getText().trim();
                            String URL = "https://www.shopgoodwill.com/Listings?st=" + Title +"&lp=" +plow + "&hp=" + phigh;
                            GetUrlInfo info = new GetUrlInfo(URL);
                            try {
                                ArrayList<String> infoList =  info.getURLInfo();
                                String strText = "Advance Search for: " + Title + "\n";
                                for(int i = 0; i < infoList.size(); i = i+3){
                                    strText+= "Item: \n" + "Image URL: " + infoList.get(i) + "\n" +
                                            "Product Id: " + infoList.get(i+1) + "\n" +
                                            "Price: " + infoList.get(i+2) + "\n";
                                }
                                JOptionPane.showMessageDialog(null, strText);
                            } catch (IOException ex) {
                                System.out.println(ex.getLocalizedMessage());
                            }
                            advanceframe.dispose();
                        }
                    }
                });
                
            }
        }
    }
}