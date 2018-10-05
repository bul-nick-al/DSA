package assigment1.task1;

import java.awt.geom.Point2D;
import java.io.*;
import java.util.*;

/**
 * Created by nicholas on 30/01/2017.
 */
public class Launcher {
    public static void main (String args[]) throws IOException {
        System.out.println((char)99);
        System.out.println("Hey");
        StringTokenizer st = readTokens();
        LinkedQueue<String> linkedQueue = turnToRPN(st);
        writeString(String.format("%.2f", calculateFromPRN(linkedQueue)));
        
    }

    /**
     * read string from input file
     * @return string
     */
    private static StringTokenizer readTokens() {
        try {
            String rawString = new Scanner(new File("input.txt")).nextLine();
            rawString = rawString.replaceAll(" ","");
            return new StringTokenizer(rawString, "()+/-*", true) ;
        } catch (FileNotFoundException ex) {
            return null;
        }
    }

    /**
     * write string to output file
     * @param s string to write
     */

    private static void writeString(String s) {
        try {
            try (Writer writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("output.txt"), "ascii"))) {
                writer.write(s);
            }
        } catch (IOException ex) {
        }
    }

    /**
     * calculates an expression given in the PRN form
     * @param expression
     * @return solution of expression
     * @throws IOException
     */
    public static double calculateFromPRN(LinkedQueue<String> expression) throws IOException {
        LinkedStack<Double> stack = new LinkedStack<Double>();
        while (expression.size() > 0){
            if (isNumber(expression.first())){
                stack.push(Double.parseDouble(expression.dequeue()));
            }
            else {
                Double a = stack.pop();
                Double b = stack.pop();
                switch (expression.dequeue().charAt(0)){
                    case '+':
                        stack.push(a + b);
                        break;
                    case '-':
                        stack.push(b - a);
                        break;
                    case '*':
                        stack.push(a * b);
                        break;
                    case '/':
                        stack.push(b / a);
                        break;
                }
            }
        }
        return stack.pop();

    }

    /**
     * check whether the given string is a number
     * @param string
     * @return true if a number, false if not
     */
    public static boolean isNumber (String string){
        try
        {
            Double.parseDouble(string);
        }
        catch (NumberFormatException e ){
            return false;
        }
        return true;
    }

    /**
     * shunting yard algorithm implementation
     * @param tokens infix sequence
     * @return PRN
     */
    public static LinkedQueue<String> turnToRPN(StringTokenizer tokens){
        //creating a stack for operands and a queue for PRN sequence
        LinkedQueue<String> completedPRN = new LinkedQueue<String>();
        LinkedStack<String> operatorStack = new LinkedStack<String>();
        //iterating through all tokens
        while(tokens.hasMoreTokens()){
            String token = tokens.nextToken();
            if (isNumber(token))
                completedPRN.enqueue(token);
            else if (token.equals("(")){
                operatorStack.push(token);
            }
            else if (token.equals(")")){
                while (operatorStack.size() > 0 && !operatorStack.top().equals("(")){
                    completedPRN.enqueue(operatorStack.pop());
                }
                operatorStack.pop();
            }
            else { //I use .charAt(0) for a token because method precedenceOf requires a character as an input and a token coming to this part is definitely of size 1
                while (operatorStack.size() > 0 && precedenceOf(token.charAt(0)) < precedenceOf(operatorStack.top().charAt(0))){
                    completedPRN.enqueue(operatorStack.pop());
                }
                operatorStack.push(token);
            }
        }
        //enqueueing the operators left in the stack after all tokens are checked to the PRN queue
        while (operatorStack.size() > 0){
            completedPRN.enqueue( operatorStack.pop());
        }
        return completedPRN;
    }

    /**
     * defines the precedence of operator: the higher is the precedence, the bigger is the returning value
     * @param operator
     * @return
     */
    public static int precedenceOf(Character operator){
        switch (operator){
            case '+':
                return 0;
            case '-':
                return 0;
            case '*':
                return 1;
            case '/':
                return 1;
            default:
                return -1;
        }
    }
}