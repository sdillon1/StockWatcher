import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by Sean on 3/31/2016.
 */

/*
Buys if the stock price has increased recently and sells if the price has declined.
        o	buys if the change (last – close) is greater than 5%
        o	sells if stock price is 5% lower than previous close
        o	holds otherwise
*/


public class BuyOnPriceIncrease extends Stock_Interface {

    Stock stock;
    BigDecimal last, close;
    BigDecimal changePercent;
    int changeThreshold;
    int negativeChangeThreshold;
    BigDecimal BD_changeThreshold;
    BigDecimal BD_negativeChangeThreshold;
    String strategyName = "BuyOnPriceIncrease";
    String symbol;


    public BuyOnPriceIncrease (String s, JTextArea j) throws IOException{

        symbol = s;
        adviceDisplay = j;

        stock = YahooFinance.get(s);

        initializeStrategy();

        printStuff();

        whatShouldIDo();

    }



    @Override
    public Stock getStock() {
        return stock;
    }

    @Override
    public void setStock() {

    }

    @Override
    public int whatShouldIDo() {
        /*
         *  return 1 if should buy
         *  return 2 if should hold
         *  return 3 if should sell
         *  return -1 on error
         */

        if((changePercent.compareTo(new BigDecimal("0")) == 0) || (changePercent.compareTo(new BigDecimal("0")) == 1)) {

            int result = changePercent.compareTo(BD_changeThreshold);

            if(result == 1 || result == 0){
                //stock price increased greater than 5 percent so must buy
                print(symbol + " " + " BUY!" + " using: " + strategyName);
                printAdvice(symbol, BUY);

            }else{
                //stock price is neither greater that 5% increase or grester tahn 5% decrese so must hold
                print(symbol + " " + "JOLD!" + " using: " + strategyName);
                printAdvice(symbol, HOLD);
            }

        }else{

            int result = changePercent.compareTo(BD_negativeChangeThreshold);

            if(result == 1){
                //stock price increased greater than 5 percent so must buy
                print(symbol + " " + "HOLD!" + " using: " + strategyName);
                printAdvice(symbol, HOLD);

            }else if(result == -1 || result == 0){
                //stock price is neither greater that 5% increase or grester tahn 5% decrese so must hold
                print(symbol + " " + "SELL!" + " using: " + strategyName);
                printAdvice(symbol, SELL);

            }

        }

        return -1;
    }

    @Override
    public void initializeStrategy(){
        last = stock.getQuote().getPrice();
        close = stock.getQuote().getPreviousClose();
        changePercent = stock.getQuote().getChangeInPercent();
        changeThreshold = 5;
        negativeChangeThreshold = -5;
        BD_changeThreshold = new java.math.BigDecimal(String.valueOf(changeThreshold));
        BD_negativeChangeThreshold = new java.math.BigDecimal(String.valueOf(negativeChangeThreshold));

    }

    @Override
    public void printStuff(){
        print("last: " + last + " close: " + close + " changePercent: " + changePercent+"%");
    }

    @Override
    public void print(String s){
        System.out.println(s);
    }

    @Override
    public void refresh() throws IOException {

        print("*****__refresh__*****");

        stock = YahooFinance.get(symbol);

        initializeStrategy();

        printStuff();

        whatShouldIDo();
    }


}
