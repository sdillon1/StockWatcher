import yahoofinance.Stock;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Sean on 3/31/2016.
 */
abstract public class Stock_Interface {

    public static final int BUY = 1;
    public static final int HOLD = 2;
    public static final int SELL = 3;

    JTextArea adviceDisplay;

    public abstract Stock getStock();
    public abstract void setStock();
    public abstract int whatShouldIDo();
    public abstract void initializeStrategy();
    public abstract void printStuff();
    public abstract void print(String s);
    public abstract void refresh() throws IOException;

    public void printAdvice(String stockSymbol, int whichOne){
        //whichOne will be 1 2 or 3 for buy hold sell

        String advice = null;

        if(whichOne == BUY) {
            advice = "BUY";
        }else if(whichOne == HOLD){
            advice = "HOLD";
        }else if(whichOne == SELL){
            advice = "SELL";
        }

        print("An old wise man said to " + advice + " the stock: " + stockSymbol);
        adviceDisplay.setText("");
        adviceDisplay.append("An old wise man said to " + advice + " the stock: " + stockSymbol);
    }

}
