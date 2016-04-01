import java.math.BigDecimal;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;


import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Sean on 3/31/2016.
 */
public class Hello {


    public static void main(String args[]) throws IOException {


        String symbol = "PSUN";
        String symbol2 = "SYMC";
        BuyOnPriceIncrease bi = new BuyOnPriceIncrease(symbol);
        BuyOnPriceDecrease bd = new BuyOnPriceDecrease(symbol2);
        RandomStrategy r = new RandomStrategy(symbol);

        Timer timer = new Timer();
        TimerTask myTask = new TimerTask() {
            @Override
            public void run() {
                // whatever you need to do every 2 seconds
                try {
                    bi.refresh();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        //timer.schedule(myTask, 2000, 2000);

        Timer timer2 = new Timer();
        TimerTask myTask2 = new TimerTask() {
            @Override
            public void run() {
                // whatever you need to do every 2 seconds
                try {
                    bd.refresh();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        //timer2.schedule(myTask, 2000, 2000);

/*
        try {
            Stock stock = YahooFinance.get("PSUN");

            BigDecimal price = stock.getQuote().getPrice();
            BigDecimal change = stock.getQuote().getChangeInPercent();
            BigDecimal peg = stock.getStats().getPeg();
            BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();

            stock.print();


            System.out.println("The current price of " + stock.getName() + " is: " + price);

        } catch (IOException e) {
            e.printStackTrace();
        }

     */


    }

    public void refresh(BuyOnPriceIncrease b) throws IOException {
        b.refresh();
    };

    public static void print(String s){
        System.out.println(s);
    }

}
