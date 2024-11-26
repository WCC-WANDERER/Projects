package pl.polsl.lab.controller;

// Packages containing class definitions 
import java.util.Vector;
import pl.polsl.lab.model.*;
import pl.polsl.lab.view.*;

/**
 * Get input from user and send it to model for running algorithms and later on display the result on GUI.
 * 
 * @author Wing Cheung Chow
 * @version 1.2
 */
public class ArabicRomanConverter {

     /**
     * Store the model variable for access of data conversion history.
     */
     private NumConversion currentNum = new NumConversion();
       
    /**
    * Main method of application that launch GUI and allows for input and output display.  
    *
    * @param args command line parameters
    */      
    public static void main(String[] args) {
        
        // Run the GUI
        GraphicalUserInterface GUI = new GraphicalUserInterface();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUI.createAndShowGUI();              
            }
        });
    }
    
    /**
     * Convert the input data using methods in model package and display the output data in view package (GUI).
     * 
     * @param inputNum the input number in string by user in GUI
     * @return output number in string or exception message if it is thrown
     */
    public String convertData(String inputNum){
        
        //NumConversion currentNum = new NumConversion();
        String outputNum;
        
         // Conversion of input number in model package and exception handling
            try {
                currentNum.convertNum(inputNum);
            }
            catch (InputOutputException e) {
                return e.getMessage();                          
            }

        // Get and return the output number
        outputNum = currentNum.getOutputNumber(); 
        
        // Add to data history
        DataHistory data = new DataHistory(inputNum, outputNum);   // record class
        currentNum.addHistory(data);
        
        return outputNum;
    }       
    
    /**
     * Return the vector of records of conversion history for JTable model.
     * 
     * @return vector of records of conversion history
     */
    public Vector<Vector<String>> getTableModel(){
       
        return currentNum.updateTableModel();
    }
    
    /**
     * Clear the list of records of conversion history in model package.
     * 
     */
    public void clearRecords(){
       
        currentNum.clearHistory();
    }
}
