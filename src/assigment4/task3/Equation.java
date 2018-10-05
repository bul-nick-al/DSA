package assigment4.task3;

/**
 * Created by nicholas on 20/04/2017.
 */
public class Equation {
    private String name;
    private boolean hasUnknowns;
    private int value;

    public String getName() {
        return name;
    }

    public boolean hasUnknowns() {
        return hasUnknowns;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {

        return value;
    }

    public String[] getExpression() {
        return expression;
    }

    String[] expression;

    public Equation(String[] equation) {
        name = equation[0];
        if (equation[1].length() == 1){
            hasUnknowns = false;
            value = Integer.parseInt(equation[1]);
        }
        else{
            hasUnknowns = true;
            expression = equation[1].split("");
        }
    }
}
