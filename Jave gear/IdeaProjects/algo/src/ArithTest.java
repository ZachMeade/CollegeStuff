
//-------------------------------------------------------------------------

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 *  Test class for Arith.java
 *
 *  @author  Joey Curran
 *  @version 13/01/19 15:04:48
 */

@RunWith(JUnit4.class)
public class ArithTest {

    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new Arith();
    }

    //~ validatePrefix() ........................................................
    @Test
    public void testValidatePrefix()
    {
        String [] exp1 = {"+", "12", "3"};
        boolean result1= Arith.validatePrefixOrder(exp1);
        assertTrue("Testing true on simple prefix expression:", result1);

        String [] exp2 = {"+", "-", "2", "3"};
        boolean result2 = Arith.validatePrefixOrder(exp2);
        assertFalse("Testing false on invalid expression:", result2);

        String [] exp3 = {"2", "*", "5", "/", "5"};
        boolean result3 = Arith.validatePrefixOrder(exp3);
        assertFalse("Testing false on infix expression", result3);


    }
    //~ validatePostfix() ........................................................
    @Test
    public void testValidatePostfix()
    {
        String [] exp1 = {"2", "3", "+"};
        boolean result1= Arith.validatePostfixOrder(exp1);
        assertTrue("Testing true on simple prefix expression:", result1);

        String [] exp2 = {"2", "3", "+", "-"};
        boolean result2 = Arith.validatePostfixOrder(exp2);
        assertFalse("Testing false on invalid expression:", result2);

        String [] exp3 = {"2", "*", "5", "/", "5"};
        boolean result3 = Arith.validatePostfixOrder(exp3);
        assertFalse("Testing false on infix expression", result3);



        String [] exp5 = {"5", "4", "+", "3"};
        boolean result5 = Arith.validatePostfixOrder(exp5);
        assertFalse("Testing false when no operator at the end:", result5);

    }
    //~ evaluatePrefix() ........................................................
    @Test
    public void testEvaluatePrefix()
    {
        String [] exp = {"*", "+", "1", "3", "-", "12", "/", "12", "2"};
        int result = Arith.evaluatePrefixOrder(exp);
        assertEquals("Test prefix expression:", 24, result);
    }

    //~ evaluatePostfix() ........................................................
    @Test
    public void testEvaluatePostfix()
    {
        String [] exp = {"2", "3", "+", "1", "-", "4", "*", "2", "/"};
        int result = Arith.evaluatePostfixOrder(exp);
        assertEquals("Test postfix expression:", 8, result);
    }

    //~ convertPrefixToPostfix() .................................................
    @Test
    public void testConvertPrefixToPostfix()
    {
        String [] exp = {"+", "2", "3"};
        String [] expResult = {"2", "3", "+"};
        assertArrayEquals("Test simple expression:", expResult, Arith.convertPrefixToPostfix(exp));

        String [] exp2 = {"*", "-", "1", "433", "2"};
        String [] expResult2 = {"1", "433", "-", "2", "*"};
        assertArrayEquals("Test more complex expression:", expResult2, Arith.convertPrefixToPostfix(exp2));
    }

    //~ convertPostfixToPrefix() .................................................
    @Test
    public void testConvertPostfixToPrefix()
    {
        String [] exp = {"2", "3", "+"};
        String [] expResult = {"+", "2", "3"};
        assertArrayEquals("Test simple expression:", expResult, Arith.convertPostfixToPrefix(exp));

        String [] exp2 = {"1", "34", "-", "2", "*"};
        String [] expResult2 = {"*", "-", "1", "34", "2"};
        assertArrayEquals("Test more complex expression:", expResult2, Arith.convertPostfixToPrefix(exp2));
    }

    //~ convertPrefixToInfix() ...................................................
    @Test
    public void testConvertPrefixToInfix()
    {
        String [] exp = {"+", "12", "3"};
        String [] expResult = {"(", "12", "+", "3", ")"};
        assertArrayEquals("Test simple prefix expression:", expResult, Arith.convertPrefixToInfix(exp));

        String [] exp2 = {"*", "+", "2", "1", "-", "1", "3"};
        String [] expResult2 = {"(", "(", "2", "+", "1", ")", "*", "(", "1", "-", "3", ")", ")"};
        assertArrayEquals("Second test: ", expResult2, Arith.convertPrefixToInfix(exp2));

        String [] exp3 = {"*", "4", "/", "2", "+", "1", "1"};
        String [] expResult3 = {"(", "4", "*","(", "2", "/", "(", "1", "+", "1", ")", ")", ")"};
        assertArrayEquals("Third test: ", expResult3, Arith.convertPrefixToInfix(exp3));
    }

    //~ convertPostfixToInfix() .................................................
    @Test
    public void testConvertPostfixToInfix()
    {
        String [] exp = {"2", "3", "+"};
        String [] expResult = {"(", "2", "+", "3", ")"};
        assertArrayEquals("Test simple postfix expression:", expResult, Arith.convertPostfixToInfix(exp));

        String [] exp2 = {"3", "1", "-", "2", "*"};
        String [] expResult2 = {"(", "(", "3", "-", "1", ")", "*", "2", ")"};
        assertArrayEquals("Second test:", expResult2, Arith.convertPostfixToInfix(exp2));

        String [] exp3 = {"1", "5", "+", "2", "/", "47", "*"};
        String [] expResult3 = {"(", "(", "(", "1", "+", "5", ")", "/", "2", ")", "*", "47", ")"};
        assertArrayEquals("Third test: ", expResult3, Arith.convertPostfixToInfix(exp3));
    }

}
