package pl.polsl.lab.view;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import pl.polsl.lab.controller.*;

/**
 * Read the input number and shows the output number in desired notation (Arabic to Roman or vice versa).
 * 
 * @author Wing Cheung Chow
 * @version 1.2
 */
public class GraphicalUserInterface extends JPanel implements ActionListener {
    
    /**
     * Panel for the upper part of the program.
     */
    private JPanel upperPanel;

    /**
     * Panel for the lower part of the program.
     */
    private JPanel lowerPanel;

    /**
     * Button to convert input number.
     */
    private JButton convertButton;

    /**
     * Button to clear conversion history.
     */
    private JButton clearButton;

    /**
     * Input text field for user input.
     */
    private JTextField inputTextField;

    /**
     * Output text field shown to user.
     */
    private JTextField outputTextField;

    /**
     * Label to indicate the field for input.
     */
    private JLabel inputLabel;

    /**
     * Label to indicate the field for output.
     */
    private JLabel outputLabel;

    /**
     * Split the pane into two panels.
     */
    private JSplitPane splitPane;

    /**
     * Table to show the input and output number generated.
     */
    private JTable table;

    /**
     * Menu bar to allow user to use the menu.
     */
    private JMenuBar menuBar;

    /**
     * Menu with different options available to user.
     */
    private JMenu menu;

    /**
     * Run the program to convert input data to output data.
     */
    private JMenuItem run;

    /**
     * Clear the conversion history.
     */
    private JMenuItem clearHistory;

    /**
     * Close the application.
     */
    private JMenuItem close;

    /**
     * Store the controller class variable for access to model package.
     */
    private ArabicRomanConverter conversionHandler = new ArabicRomanConverter();
    
    /**
     * Display the input number for conversion.
     */
    private String inputNumber;
    
    /**
     * Display the output number on GUI.
     */
    private String outputNumber;

    /**
     *  Model that contains data and columns.
     */
    private DefaultTableModel model;
    
    /**
     * Data from input and output for every conversion are saved here.
     */
    private Vector<Vector<String>> data;

    /**
     * Names for the columns in table.
     */
    private Vector<String> header;
 
    /**
     * Initialize the components for GUI.
     * 
     */
    public GraphicalUserInterface() {
    	
        // Panels on display
        upperPanel = new JPanel();
        lowerPanel = new JPanel();
        
        // Input and output fields
        inputLabel = new JLabel("Input number: ");
        inputTextField = new JTextField(20);
        outputLabel = new JLabel("Converted number: ");
        outputTextField = new JTextField(20);
        
        // Table
        data = new Vector<>();
        header = new Vector<>();
        header.add("Input");     // column name
        header.add("Output");    // column name
        model = new DefaultTableModel(data, header);
        table = new JTable(model);   // Set the model for table
        JScrollPane sp = new JScrollPane(table);
              
        // Menubar and menu
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");  // context description      
        menu.setMnemonic(KeyEvent.VK_A);  // keyboard shortcut (ALT+A)
        menu.getAccessibleContext().setAccessibleDescription("The menu for different access of the program.");
        menu.setToolTipText("This is the menu.");
        
        // Menu items
        run = new JMenuItem("Run the program");  // context description
        run.setMnemonic(KeyEvent.VK_Q);   // keyboard shortcut (ALT+Q)
        run.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK)); // keyboard shortcut (ALT+1)
        run.getAccessibleContext().setAccessibleDescription("Convert the input number to designated notation.");
        run.setToolTipText("This option is convert the input data.");  // tool tip
        run.setActionCommand("Run");
        run.addActionListener(this);  // listener
        menu.add(run);
        
        clearHistory = new JMenuItem("Clear the history");  // context description
        clearHistory.setMnemonic(KeyEvent.VK_W);  // keyboard shortcut (ALT+W)
        clearHistory.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK)); // keyboard shortcut (ALT+2)
        clearHistory.getAccessibleContext().setAccessibleDescription("Clear the input and output history.");
        clearHistory.setToolTipText("This option is to clear the data history.");  // tool tip
        clearHistory.setActionCommand("ClearHistory");
        clearHistory.addActionListener(this);  // listener
        menu.add(clearHistory);
        
        menu.addSeparator();
        
        close = new JMenuItem("Close");  // context description
        close.setMnemonic(KeyEvent.VK_E);  // keyboard shortcut (ALT+E)
        close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK)); // keyboard shortcut (ALT+3)
        close.getAccessibleContext().setAccessibleDescription("Close the program.");
        close.setToolTipText("This option is to close the program.");  // tool tip
        close.setActionCommand("Close");
        close.addActionListener(this);  // listener
        menu.add(close);
        
        menuBar.add(menu);
        
    	// Prepare image icons
        ImageIcon convertButtonIcon = createImageIcon("/images/button-icon-png-21071.png");
        ImageIcon clearButtonIcon = createImageIcon("/images/button-icon-png-21047.png");
        
	// Button
        convertButton = new JButton("Convert", convertButtonIcon);   // context description
        Font font = convertButton.getFont().deriveFont(Font.PLAIN);
        convertButton.setFont(font);
        convertButton.setForeground(new Color(0xffffdd));
        convertButton.setPreferredSize(new Dimension(100, 50));
        convertButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        convertButton.setHorizontalTextPosition(AbstractButton.CENTER);
        convertButton.setMnemonic(KeyEvent.VK_D);   // keyboard shortcut (ALT+D)
        convertButton.setActionCommand("Convert");
        convertButton.addActionListener(this);  // listener
        convertButton.setToolTipText("This button converts the input Arabic numeral to Roman numveral and vice versa.");  // tool tip
        
        clearButton = new JButton("Clear", clearButtonIcon);   // context description 
        clearButton.setFont(font);
        clearButton.setForeground(new Color(0xffffdd));
        clearButton.setPreferredSize(new Dimension(80, 50));
        clearButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        clearButton.setHorizontalTextPosition(AbstractButton.CENTER);
        clearButton.setMnemonic(KeyEvent.VK_F);   // keyboard shortcut (ALT+F)
        clearButton.setActionCommand("Clear");
        clearButton.addActionListener(this);  // listener
        clearButton.setToolTipText("This button clear the history of input and output."); // tool tip
           
        // Adding components to the container
        // Box layout for upper panel and Border layout for lower panel
        // Split pane to combine two panels
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.PAGE_AXIS));
        upperPanel.add(inputLabel);
        upperPanel.add(inputTextField);
        upperPanel.add(outputLabel);
        upperPanel.add(outputTextField);
        upperPanel.add(convertButton);
    
        lowerPanel.add(sp, BorderLayout.CENTER);
        lowerPanel.add(clearButton, BorderLayout.EAST);
        
        splitPane = new JSplitPane(SwingConstants.HORIZONTAL, upperPanel, lowerPanel);
        splitPane.setOneTouchExpandable(true); 
        splitPane.setDividerLocation(200); 
        
        add(splitPane);       
    }     
        
    /**
     * Get the input number from text field and use the method in controller package to do the conversion.
     * And then check the result, if exception is thrown, error message pop out and table is not updated.
     * If everything good, the input and output number generated are shown in the table of conversion history.
     */
    public void actionAndUpdate() {
        
        // Input validation and exception handling
        if (!inputTextField.getText().isEmpty()) {
            
            inputNumber = inputTextField.getText();
            outputTextField.setText("");  // prevent using previous string
            
            // Conversion
            outputNumber = conversionHandler.convertData(inputNumber);
            
            if (outputNumber.contains("try again")) { 
                
                // Display error message after exception caught
                JOptionPane.showMessageDialog(null, outputNumber, "Exception", JOptionPane.ERROR_MESSAGE);      
            }
            else {
                
                // Set the text in output textfield
                outputTextField.setText(outputNumber);
                
                // Update the table model by calling the controller
                data.clear();
                data.addAll(conversionHandler.getTableModel());
                model.fireTableDataChanged();   // Notify the table model when data is updated          
            } 
        }     
    }
    
    /** 
     * Method that handle an ActionEvent event.
     * @param e upcoming ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
    	
        // Check which type of event occured
        if ("Convert".equals(e.getActionCommand()) ||"Run".equals(e.getActionCommand())) {
            
            // Data conversion
            actionAndUpdate();  
        }
        else if ("Clear".equals(e.getActionCommand()) ||"ClearHistory".equals(e.getActionCommand())) {
            
            // Reset the table content
            model.setRowCount(0); 
            data.clear();
            conversionHandler.clearRecords();           
        }
        else if ("Close".equals(e.getActionCommand())) {
            
            // Confirmation before closing the application
            int response = JOptionPane.showConfirmDialog(null, "Would You Like to Exit?", "Warning", 
                    JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
            }
        }
    }

    /** 
     * Method for loading an image file and creating an icon.
     * @param path image file path
     * @return ImageIcon or null if the path is wrong
     */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = GraphicalUserInterface.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
     /**	
      * Create and show the GUI. 
      * To ensure security, the following method should be called from the thread dispatching the event.  
      */
    public void createAndShowGUI() {   
        
        // Setting nice appearance of windows
        JFrame.setDefaultLookAndFeelDecorated(false);

	// Create and prepare window
        JFrame frame = new JFrame("Aracbic-Roman Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	// Create and prepare panel
        GraphicalUserInterface newContentPane = new GraphicalUserInterface();
        
        // Make panel and menubar visible 
        frame.setJMenuBar(newContentPane.menuBar);
        newContentPane.setOpaque(true); 
        frame.setContentPane(newContentPane);  

        // Display window
        frame.pack();
        frame.setVisible(true);
        frame.setSize(700, 700);        
    }
}
