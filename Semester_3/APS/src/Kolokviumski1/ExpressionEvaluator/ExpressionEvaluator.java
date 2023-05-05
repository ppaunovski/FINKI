package Kolokviumski1.ExpressionEvaluator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ExpressionEvaluator {

    public static int evaluateExpression(String expression){
        // Vasiot kod tuka
        String []parts = expression.split("");
        Stack<Integer> stack = new Stack<>();


        int number = 0;
        for(int i=0; i<parts.length; i++){
            number = 0;
            if(parts[i].equals("+")){
                continue;
            }
            if(parts[i].equals("*")){
                Integer num1 = stack.pop();
                i++;
                while (i<parts.length && Character.isDigit(parts[i].charAt(0))){
                    number = number*10 + Integer.parseInt(parts[i]);
                    i++;
                }
                i--;
                Integer res = num1 * number;
                stack.push(res);
            }
            else {
                while (i<parts.length && Character.isDigit(parts[i].charAt(0))){
                    number = number*10 + Integer.parseInt(parts[i]);
                    i++;
                }
                i--;
                stack.push(number);
            }
        }

        int sum = 0;
        while (!stack.isEmpty()){
            sum += stack.pop();
        }
        return sum;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }

}