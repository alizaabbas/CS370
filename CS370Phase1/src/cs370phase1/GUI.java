/**
 * GUI.java 
 * Description: This class will use GUI to ask user for inputs to create, modify and remove hashtable contents.
 * 
 * @author Aliza Abbas
 * 
 */
package cs370phase1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GUI implements ActionListener {
    private JLabel label = new JLabel("choose the option");
    private JFrame frame = new JFrame();
    
    private JButton selectButton ;
    private JButton addButton ;
    private JButton removeButton ;    
    
    public GUI() {
        
        selectButton = new JButton("SELECT");
        addButton = new JButton("ADD");
        removeButton = new JButton("DELETE");
        
        selectButton.addActionListener(this);
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(60, 60, 20, 60));
        panel.setLayout(new GridLayout(0, 1));
        
        panel.add(selectButton);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(label);
        
        frame.setLocationRelativeTo(null);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Grocery Inventory");
        frame.pack();
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        
        /********SelectButton***********/
        if(e.getSource().equals(selectButton)){
            JFrame frameSelect = new JFrame();
            String desc = JOptionPane.showInputDialog(null, "Enter the description of the item");
            if(CS370Phase1.grocery.containsKey(desc)){
                JOptionPane.showMessageDialog(null, desc +" | " + CS370Phase1.grocery.get(desc).toString());
            }     
            else
                JOptionPane.showMessageDialog(null, "Sorry, this item is not found in the inventory");
        }
        
        //***********ADD BUTTON / update button**********//
        else if(e.getSource().equals(addButton)){
            String invNum = JOptionPane.showInputDialog(null, "enter inventory number");
            String desc = JOptionPane.showInputDialog(null, "Item Description");
            String quant = JOptionPane.showInputDialog(null, "enter quantity");
            String pri = JOptionPane.showInputDialog(null, "enter price");
            String d = JOptionPane.showInputDialog(null, "enter date");
            String t = JOptionPane.showInputDialog(null, "enter time");
            
            if(CS370Phase1.grocery.size() < CS370Phase1.Max_Capacity){
                if(CS370Phase1.grocery.containsKey(desc)){
                    CS370Phase1.grocery.get(desc).setQuantity(quant);
                    CS370Phase1.grocery.get(desc).setPrice(pri);
                    CS370Phase1.grocery.get(desc).setDate(d);
                    CS370Phase1.grocery.get(desc).setTime(t);
                    
                }
                else{
                    
                    CS370Phase1.grocery.put( desc, new inventory(invNum, quant, pri, d, t));
                }
                FileWriter fileWriter;
                try {
                    fileWriter = new FileWriter("/Users/alizaabbas/Documents/netbeans-proj/CS370Phase1/src/cs370phase1/inv.txt");
                    PrintWriter printWriter = new PrintWriter(fileWriter);
                    Set<String> keys = CS370Phase1.grocery.keySet();
                    for(String key: keys){
                        printWriter.println(CS370Phase1.grocery.get(key).getInventoryNumber() + " , "+
                                key + " , " + CS370Phase1.grocery.get(key).getQuantity() + " , " +
                                CS370Phase1.grocery.get(key).getPrice() + " , " +
                                CS370Phase1.grocery.get(key).getDate() + " , " +
                                CS370Phase1.grocery.get(key).getTime());
                    }
                    printWriter.close();
                }
                catch (IOException ex) {
                    System.out.println(ex.getLocalizedMessage());
                }
            }
            else
                JOptionPane.showMessageDialog(null, "The Inventory is full, can't add more items");
        }
        
        /**************REMOVE BUTTON**********/
        else {
            if(e.getSource().equals(removeButton)){
                String desc = JOptionPane.showInputDialog(null, "enter the name of the item to be removed");
                String quantity = JOptionPane.showInputDialog(null, "Enter the quantity to be removed, write all to delete the entire record");
                String date = JOptionPane.showInputDialog(null, "Enter the date:");
                String time =  JOptionPane.showInputDialog(null, "Enter current time");
                if(CS370Phase1.grocery.containsKey(desc)){
                    if(quantity.equals("all")){
                        CS370Phase1.grocery.remove(desc);
                    }
                    else{
                        CS370Phase1.grocery.get(desc).setQuantityRemove(quantity);
                        CS370Phase1.grocery.get(desc).setDate(date);
                        CS370Phase1.grocery.get(desc).setTime(time);
                    }
                }
                
                else
                    JOptionPane.showMessageDialog(null, "Sorry, this item is not found in the inventory, try again!");
                FileWriter fileWriter;
                    try {
                        fileWriter = new FileWriter("/Users/alizaabbas/Documents/netbeans-proj/CS370Phase1/src/cs370phase1/inv.txt");
                        PrintWriter printWriter = new PrintWriter(fileWriter);
                        Set<String> keys = CS370Phase1.grocery.keySet();
                        for(String key: keys){
                        printWriter.println(CS370Phase1.grocery.get(key).getInventoryNumber() + " , "+ 
                                key + " , " + CS370Phase1.grocery.get(key).getQuantity() + " , " + 
                                CS370Phase1.grocery.get(key).getPrice() + " , " + 
                                CS370Phase1.grocery.get(key).getDate() + " , " + 
                                CS370Phase1.grocery.get(key).getTime());
                        }
                        printWriter.close();
                    } 
                    catch (IOException ex) {
                          System.out.println(ex.getLocalizedMessage());
                    }
            }
        }
    }    
}