import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by Sean on 3/31/2016.
 */
public class CustomStrategy extends Stock_Interface {


    Stock stock;
    String strategyName = "CustomStrategy";
    String symbol;
    String BUY_SYMBOL = "GOOG", SELL_SYMBOL = "AAPL";

    public CustomStrategy (String s, JTextArea j) throws IOException{

        symbol = s;
        adviceDisplay = j;

        stock = YahooFinance.get(s);

        initializeStrategy();

        printStuff();

        whatShouldIDo();

    }


    @Override
    public Stock getStock() {
        return null;
    }

    @Override
    public void setStock() {

    }

    @Override
    public void whatShouldIDo() {

        if(symbol.equals(BUY_SYMBOL)){

            //stock price increased greater than 5 percent so must buy
            print(symbol + " " + " BUY!" + " using: " + strategyName);
            printAdvice(symbol, BUY);

        }else if(symbol.equals(SELL_SYMBOL)){

            //stock price increased greater than 5 percent so must buy
            print(symbol + " " + " SELL!" + " using: " + strategyName);
            printAdvice(symbol, SELL);

        }else{
            //stock price increased greater than 5 percent so must buy
            print(symbol + " " + " HOLD!" + " using: " + strategyName);
            printAdvice(symbol, HOLD);
        }


    }

    @Override
    public void initializeStrategy() {

    }

    @Override
    public void printStuff() {

    }

    @Override
    public void print(String s) {
        System.out.println(s);
    }

    @Override
    public void refresh() throws IOException {

    }
}
