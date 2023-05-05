package VEZBI.DynamicPrograming;

/*

    Find the minimal steps required to reduce a given number to 1;
    Allowed steps are:
        1.  If n % 2 == 0, divide it by 2
        2.  If n % 3 == 0, divide it by 3
        3.  If not n -= 1

 */

public class MinimalStepsToReduceToOne {

    public static int getMinSteps(int n){
        // ako n e 1 sme zavrshile => vrati nula steps required...
        int sum = 0;
        if(n == 1)  return 0;

        if(n % 3 == 0)
            sum += getMinSteps(n/3) + 1;
        if(n % 2 == 0)
            sum += getMinSteps(n/2) + 1;
        else
            sum += getMinSteps(--n) + 1;
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(getMinSteps(15));
    }
}
