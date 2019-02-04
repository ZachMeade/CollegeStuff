

import java.util.Scanner;

import java.util.Stack;

// -------------------------------------------------------------------------
/**
 * Utility class containing validation/evaluation/conversion operations
 * for prefix and postfix arithmetic expressions.
 *
 * @author
 * @version 1/12/15 13:03:48
 */

public class Arith
{


    //~ Validation methods ..........................................................


    /**
     *Validation method for prefix notation.
     *
     *@param prefixLiterals : an array containing the string literals hopefully in prefix order.
     *The method assumes that each of these literals can be one of:
     *- "+", "-", "*", or "/"
     *- or a valid string representation of an integer.
     *
     *@return true if the parameter is indeed in prefix notation, and false otherwise.
     **/

    public static boolean validatePostfixOrder(String postfixLiterals[])
    {
        //TODO
        int index=1;
        int startPos = postfixLiterals.length-1;

        for(int i=startPos; i>=0; i--) {

            if(postfixLiterals[i]=="-" || postfixLiterals[i]=="+" || postfixLiterals[i]=="/" || postfixLiterals[i]=="*" ) {
                index++;
            }
            else {
                index--;
                if(index<=0) {
                    if(i==0)
                    {
                        return true;
                    }
                    else return false;
                }
            }
        }
        if(index ==0) {
            return true;
        }
        else {
            return false;
        }

    }





    /**
     *Validation method for postfix notation.
     *
     *@param postfixLiterals : an array containing the string literals hopefully in postfix order.
     *The method assumes that each of these literals can be one of:
     *- "+", "-", "*", or "/"
     *- or a valid string representation of an integer.
     *
     *@return true if the parameter is indeed in postfix notation, and false otherwise.
     **/
    public static boolean validatePrefixOrder(String prefixLiterals[])
    {
        //TODO
        int count=1;
        for(int i=0; i<prefixLiterals.length; i++) {
            //	if(prefixLiterals[i]))
            if(prefixLiterals[i]=="-" || prefixLiterals[i]=="+" || prefixLiterals[i]=="/" || prefixLiterals[i]=="*" ) {
                count++;
            }
            else {
                count--;
                if(count<=0) {
                    if(i+1!=prefixLiterals.length)
                    {
                        return false;
                    }
                    else return true;
                }
            }
        }
        return false;

    }



    //~ Evaluation  methods ..........................................................



    public static int performOperation(String operation, int operand1, int operand2) {
        int answer = 0;
        if(operation=="+") {
            answer = operand1+operand2;
        }
        else if(operation=="-") {
            answer = operand1-operand2;

        }
        else if(operation=="*") {
            answer = operand1*operand2;

        }
        else if(operation=="/") {
            answer = operand1/operand2;

        }

        return answer;

    }
    /**
     *Evaluation method for prefix notation.
     *
     *@param prefixLiterals : an array containing the string literals in prefix order.
     *The method assumes that each of these literals can be one of:
     *- "+", "-", "*", or "/"
     *- or a valid string representation of an integer.
     *
     *@return the integer result of evaluating the expression
     **/
    public static int evaluatePrefixOrder(String prefixLiterals[])
    {
        //TODO
        Stack<Integer> prefix = new Stack<>();
        int answer=0;
        int startPos =prefixLiterals.length-1;
        for(int i = startPos; i >= 0; i--) {
            if(prefixLiterals[i]=="-" || prefixLiterals[i]=="+" || prefixLiterals[i]=="/" || prefixLiterals[i]=="*") {
                int operand1 = prefix.pop();
                int operand2 = prefix.pop();
                String operator = prefixLiterals[i];
                answer = performOperation(operator, operand1, operand2);
                prefix.push(answer);

            }
            else {
                int toPush = Integer.parseInt(prefixLiterals[i]);
                prefix.push(toPush);
            }
        }
        answer = prefix.pop();
        return answer;
    }






    /**
     *Evaluation method for postfix notation.
     *
     *@param postfixLiterals : an array containing the string literals in postfix order.
     *The method assumes that each of these literals can be one of:
     *- "+", "-", "*", or "/"
     *- or a valid string representation of an integer.
     *
     *@return the integer result of evaluating the expression
     **/
    public static int evaluatePostfixOrder(String postfixLiterals[])
    {
        //TODO

        Stack<Integer> postfix = new Stack<>();
        int answer=0;
        for(int i = 0; i < postfixLiterals.length; i++) {
            if(postfixLiterals[i]=="-" || postfixLiterals[i]=="+" || postfixLiterals[i]=="/" || postfixLiterals[i]=="*") {
                int operand1 = postfix.pop();
                int operand2 = postfix.pop();
                String operator = postfixLiterals[i];
                answer = performOperation(operator, operand2, operand1);
                postfix.push(answer);

            }
            else {
                int toPush = Integer.parseInt(postfixLiterals[i]);
                postfix.push(toPush);
            }
        }
        answer = postfix.pop();
        return answer;
    }


    //~ Conversion  methods ..........................................................


    /**
     *Converts prefix to postfix.
     *
     *@param prefixLiterals : an array containing the string literals in prefix order.
     *The method assumes that each of these literals can be one of:
     *- "+", "-", "*", or "/"
     *- or a valid string representation of an integer.
     *
     *@return the expression in postfix order.
     **/
    public static String[] convertPrefixToPostfix(String prefixLiterals[])
    {
        //TODO

        Stack<String> postfix = new Stack<>();
        String[] postfixLiterals = new String[prefixLiterals.length *4];
        int startPos = prefixLiterals.length - 1;
        for(int i = startPos ; i >= 0 ; i --) {
            if(prefixLiterals[i]=="-" || prefixLiterals[i]=="+" || prefixLiterals[i]=="/" || prefixLiterals[i]=="*" ) {

                String toPush = postfix.pop() + " " + postfix.pop() + " " + prefixLiterals[i] ;
                postfix.push(toPush);
            }
            else {
                postfix.push(prefixLiterals[i]);
            }
        }
        Scanner s = new Scanner(postfix.pop()).useDelimiter(" ");

        prefixLiterals = s.nextLine().split(" ");
        s.close();
        return prefixLiterals;

    }


    /**
     *Converts postfix to prefix.
     *
     *@param prefixLiterals : an array containing the string literals in postfix order.
     *The method assumes that each of these literals can be one of:
     *- "+", "-", "*", or "/"
     *- or a valid string representation of an integer.
     *
     *@return the expression in prefix order.
     **/
    public static String[] convertPostfixToPrefix(String postfixLiterals[])
    {
        //TODO 1 2 +


        String[] prefixLiterals = new String[postfixLiterals.length*10];
        Stack<String> prefix = new Stack<>();
        for ( int i = 0; i < postfixLiterals.length; i++) {
            if(postfixLiterals[i]=="-" || postfixLiterals[i]=="+" || postfixLiterals[i]=="/" || postfixLiterals[i]=="*") {
                String string1 = prefix.pop();
                String string2 = prefix.pop();
                String toPush = postfixLiterals[i] + " " + string2 + " " + string1;
                prefix.push(toPush);
            }
            else {
                prefix.push(postfixLiterals[i]);
            }
        }
        Scanner s = new Scanner(prefix.pop()).useDelimiter(" ");
        prefixLiterals = s.nextLine().split(" ");
        s.close();
        return prefixLiterals;
    }



    /**
     *Converts prefix to infix.
     *
     *@param infixLiterals : an array containing the string literals in prefix order.
     *The method assumes that each of these literals can be one of:
     *- "+", "-", "*", or "/"
     *- or a valid string representation of an integer.
     *
     *@return the expression in infix order.
     **/
    public static String[] convertPrefixToInfix(String prefixLiterals[])
    {
        // TODO
        Stack<String> infix = new Stack<>();
        String[] infixLiterals = new String[prefixLiterals.length *4];
        int startPos = prefixLiterals.length-1;
        for(int i = startPos; i >= 0; i-- ){
            if(prefixLiterals[i]=="-" || prefixLiterals[i]=="+" || prefixLiterals[i]=="/" || prefixLiterals[i]=="*" ) {
                String string1 = infix.pop();
                String string2 = infix.pop();
                String string = "(" + " " + string1 + " " + prefixLiterals[i] + " " + string2  + " " + ")";
                infix.push(string);
            }
            else {
                infix.push(prefixLiterals[i]);
            }
        }
        Scanner s = new Scanner(infix.pop()).useDelimiter(" ");
        infixLiterals = s.nextLine().split(" ");
        s.close();
        return infixLiterals;
    }


    /**
     *Converts postfix to infix.
     *
     *@param infixLiterals : an array containing the string literals in postfix order.
     *The method assumes that each of these literals can be one of:
     *- "+", "-", "*", or "/"
     *- or a valid string representation of an integer.
     *
     *@return the expression in infix order.
     **/


    public static String[] convertPostfixToInfix(String postfixLiterals[]) {
        Stack<String> infix = new Stack<>();
        String[] infixLiterals = new String[postfixLiterals.length *4];
        for (int i =0 ; i < postfixLiterals.length; i++) {
            if(postfixLiterals[i]=="-" || postfixLiterals[i]=="+" || postfixLiterals[i]=="/" || postfixLiterals[i]=="*" ) {
                String string1 = infix.pop();
                String string2 = infix.pop();
                String string = "(" + " " + string2 + " " +  postfixLiterals[i] + " " + string1 + " " + ")";
                infix.push(string);
            }
            else {
                infix.push(postfixLiterals[i]);
            }
        }

        Scanner s = new Scanner(infix.pop()).useDelimiter(" ");
        infixLiterals = s.nextLine().split(" ");
        s.close();
        return infixLiterals;
    }




}