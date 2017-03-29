/*
 * 
 */

package dfademo;

import java.util.Scanner;
import java.io.IOException;
/**
 *
 * @author Robert Morris
 * This is a program to test out a simple DFA machine. It excepts a input strings 
 * from the user and checks them againts the defined language of L={a^n b:n>=0}
 * Since this program is designed to test only strings with 'a' and 'b' in them, 
 * it throws an IOException if the user inputs strings containing any other characters.
 */
public class DFAdemo {

    /**
     * 
     */
    public static String input;
    
    public static void main( String[] args ) throws Exception {
        
        Scanner  scanner = new Scanner( System.in );
        message( 1 );
        boolean run = true;
        
        while( run ){ // loop program
            
            input = scanner.next().toLowerCase();
            
            if( input.equals( "0" ) ){ // check to see if user would like to quit test
                
                run = false; 
                
            }
            
            else{
                
                if( dfaMachineTest( input ) ){ // TEST STRING (TRUE if excepted || FALSE if not excepted)
                    
                    System.out.println( "Thank You." );
                    System.out.println( "Now testing the input: '" +  input + "' ..." );
                    System.out.println( "The string: '" + input + "' was excepted." ); 
                    
                }

                else{
                    
                    System.out.println( "The string: '" + input + "' was not excepted." );
                    
                }
                
                message( 2 );
            }
        }
        
        System.out.println( "Thank you testing this DFA, designed by Rober Morris." );
    }
    
    public static boolean dfaMachineTest( String inString ) throws IOException, Exception { // machine states test
        /* excepts a user strings, converts it to a character array, checks the characters
            against the definded language and returns true or false if the langauge is excepted */
        Exception IOException = null;
        char[] string = inString.toCharArray();
        int loopLen = string.length;
        char letter;
        int state = -1;
        
        for( int i = 0; i < loopLen; i++ ){
            letter = string[i];
            
            // MACHINE STATES BELOW 
            if( state == -1 ){ // NO STATE
                
                if( letter == 'a' ){
                    state = 0; // 'a' is encountered
                }
                
                else if( letter == 'b' ){
                    break; // machine only excepts where the first letter is an 'a'
                }
                else{
                    throw IOException;// anything other than an 'a' or 'b' is encountered
                }
            }
            
            else if(state == 0){ // STATE q0
                
                if( letter == 'b' ){
                    state = 1; // a 'b' is encountered
                }
                
                else if(letter == 'a'){
                    // remain in state 0 
                }
                
                else{

                    throw IOException;// anything other than an 'a' or 'b' is encountered
                }
                
            }
            
            else if( state == 1 ){ // FINAL STATE q1 ( Excepted Strings End Here)
                
                if( letter == 'a' ){
                    state = 2; // a 'a' is encountered
                }
                
                else if( letter == 'b' ){
                    state = 2; // a 'b' is encountered
                }
                
                else{
                
                    throw IOException;// anything other than an 'a' or 'b' is encountered
                }
                
            }
            
            else if( state == 2 ){ // STATE q2
                // There is no way back to state 1 
                if( letter == 'a' ){
                    // pass
                }
                    
                else if( letter == 'b' ){
                    // pass
                }
                else{
                    throw IOException;// anything other than an 'a' or 'b' is encountered
                }
            }
        }
        return state == 1; // return true if string is excepted 
    }
    
    public static void message( int message ){
        /* FUNCTION TO DISPLAY MESSAGES TO USER */
        if( message == 1 ){
            
            String language = "L={a^n b:n>=0}" ;
            System.out.println( "This simple program tests strings against the language defind as: " );
            System.out.println( "            " + language + "          " );
            System.out.println( "Please input a mixed string of 'a' and 'b' to test the DFA, " );
            System.out.println( "Or type '0' to exit: " );
            
        }
        
        else if ( message == 2 ){
            
            System.out.println( "Please enter another string of 'a's and 'b's to test the DFA," );
            System.out.println( "Or type '0' to exit: " );
                    
        }
    }
    
}
