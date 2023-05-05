package lab4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    static int presmetaj(char c[], int l, int r) {
        // Vasiot kod tuka
        Stack<Character> bracesAndOperations = new Stack<>();
        Stack<Integer> operands = new Stack<>();
        while (l != r+1){
            if(c[l] == '('){
                bracesAndOperations.push(c[l]);
            }
            if(c[l] == '+' || c[l] == '-'){
                bracesAndOperations.push(c[l]);
            }
            if(Character.isDigit(c[l])){
                if(bracesAndOperations.peek() == '+' || bracesAndOperations.peek() == '-'){
                    int num1 = operands.pop();
                    int num2 = c[l] - '0';
                    int sum = 0;
                    if(bracesAndOperations.peek() == '+') sum = num1 + num2;
                    if(bracesAndOperations.peek() == '-') sum = num1 - num2;

                    operands.push(sum);
                    bracesAndOperations.pop();
                }
                else operands.push(c[l] - '0');
            }
            if(c[l] == ')'){
                if(bracesAndOperations.peek() == '(')   bracesAndOperations.pop();
                else{
                    int num1 = operands.pop();
                    int num2 = operands.pop();
                    int sum = num1 + num2;
                    operands.push(sum);
                    bracesAndOperations.pop();
                }
            }
            l++;
        }
        if(operands.size() > 1){
            return operands.pop() + operands.pop();
        }
        return operands.pop();
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);

        br.close();

    }

}

