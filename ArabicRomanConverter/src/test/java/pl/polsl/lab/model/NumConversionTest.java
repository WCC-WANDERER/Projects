package pl.polsl.lab.model;

import java.util.Vector;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for public methods of NumConversion class.
 * 
 * @author Wing Cheung Chow
 * @version 1.2
 * 
 */
public class NumConversionTest {
    
    private NumConversion testConversion;
      
    public NumConversionTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {            
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        testConversion = new NumConversion();  
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    // Testing public methods in NumConversion class
    @Test
    public void testIsStringInteger() {
        
        assertEquals(testConversion.isStringInteger("100"), true, "Integer should be here!");
        assertEquals(testConversion.isStringInteger("abc"), false, "String should be here!");       
    }
    
    @Test
    public void testConvertNum() {

        assertDoesNotThrow(
        () -> testConversion.convertNum("300"), 
        "The exception should not be thrown");

        assertEquals(testConversion.getOutputNumber(), "CCC", "Arabic to Roman conversion is incorrect!");
        
        assertDoesNotThrow(
        () -> testConversion.convertNum("MMX"), 
        "The exception should not be thrown");
        assertEquals(testConversion.getOutputNumber(), "2010", "Roman to Arabic conversion is incorrect!"); 
        
        InputOutputException ArabicInputException = assertThrows(
                InputOutputException.class,
                () -> testConversion.convertNum("-100"),
                "The exception should be thrown");
        assertEquals("Invalid input parameter. Please try again.", ArabicInputException.getMessage());
        
        InputOutputException RomanInputException = assertThrows(
                InputOutputException.class,
                () -> testConversion.convertNum("IIX"),
                "The exception should be thrown");
        assertEquals("Invalid input parameter. Please try again.", RomanInputException.getMessage());
    }
    
    @Test
    public void testArabicToRoman () {

        assertDoesNotThrow(
        () -> testConversion.arabicToRoman(300), 
        "The exception should not be thrown");

        assertEquals(testConversion.getOutputNumber(), "CCC", "Arabic to Roman conversion is incorrect!");

        InputOutputException RomanOutputException = assertThrows(
                InputOutputException.class,
                () -> testConversion.arabicToRoman(7399),
                "The exception should be thrown");
        assertEquals("An unexpected error occurred. Please try again.", RomanOutputException.getMessage());  
    }
    
    @Test
    public void testRomanToArabic () {

        assertDoesNotThrow(
        () -> testConversion.romanToArabic("CCC"), 
        "The exception should not be thrown");

        assertEquals(testConversion.getOutputNumber(), "300", "Roman to Arabic conversion is incorrect!");

        InputOutputException ArabicOutputException = assertThrows(
                InputOutputException.class,
                () -> testConversion.romanToArabic("MMMMM"),
                "The exception should be thrown");
        assertEquals("An unexpected error occurred. Please try again.", ArabicOutputException.getMessage());  
    }
    
    @Test
    public void testAddHistory () {

        testConversion = new NumConversion();
        DataHistory data = new DataHistory("100", "C");   // record class
        testConversion.addHistory(data);
        
        assertEquals(testConversion.getHistory().get(0), data, "The history stored is not the same!");
    }
    
    @Test
    public void testClearHistory () {

        testConversion.clearHistory();
        assertEquals(testConversion.getHistory().isEmpty(), true, "The list should be empty after clearing history!");
    }
    
    @Test
    public void testUpdateTableModel () {
        
        Vector<Vector<String>> actual = new Vector<>();
        
        testConversion = new NumConversion();
        DataHistory data = new DataHistory("100", "C");   // record class
        testConversion.addHistory(data);
        actual = testConversion.updateTableModel();
        
        Vector<Vector<String>> expected = new Vector<>();
        Vector<String> temp = new Vector<>();
        temp.add("100"); temp.add("C");
        expected.add(temp);
        
        // Check list of single element
        assertEquals(actual.get(0).get(0), expected.get(0).get(0), "The list of records of conversion history are not the same!");   
        assertEquals(actual.get(0).get(1), expected.get(0).get(1), "The list of records of conversion history are not the same!");
              
        // Check list of multiple elements
        DataHistory data2 = new DataHistory("10", "X");   // record class
        testConversion.addHistory(data2);
        DataHistory data3 = new DataHistory("1", "I");   // record class
        testConversion.addHistory(data3);
        actual = testConversion.updateTableModel();
        
        Vector<String> temp2 = new Vector<>();
        temp2.add("10"); temp2.add("X"); 
        expected.add(temp2);
        Vector<String> temp3 = new Vector<>();
        temp3.add("1"); temp3.add("I");
        expected.add(temp3);
                     
        if (actual.size() == expected.size()) {
            for (int i = 0; i < actual.size(); i++) {
             
                assertEquals(actual.get(i).get(0), expected.get(i).get(0), "The list of records of conversion history are not the same!");   
                assertEquals(actual.get(i).get(1), expected.get(i).get(1), "The list of records of conversion history are not the same!");
            }
        }
    }    
}
