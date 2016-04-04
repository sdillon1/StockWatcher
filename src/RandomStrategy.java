import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sean on 3/31/2016.
 */

/*
Random Strategy:
        o	buys if ticker symbol is 4 characters
        o	sells if the time is between 1:00pm and 3:22pm
        o	holds if none of the above are true
*/

public class RandomStrategy extends Stock_Interface{

    Stock stock;
    BigDecimal last, close;
    BigDecimal changePercent;
    int changeThreshold;
    int negativeChangeThreshold;
    BigDecimal BD_changeThreshold;
    BigDecimal BD_negativeChangeThreshold;
    String strategyName = "RandomStrategy";
    String symbol;
    Time time;

    String time1, time2;
    int time_1_minutes, time_2_minutes, time_current_minutes;
    int num_of_chars_in_symbol;

    public RandomStrategy(String s, JTextArea j) throws IOException {

        print(LocalTime.now().toString());

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

         /*
         *  return 1 if should buy
         *  return 2 if should hold
         *  return 3 if should sell
         *  return -1 on error
         */

    /*    buys if ticker symbol is 4 characters
        o	sells if the time is between 1:00pm and 3:22pm
        o	holds if none of the above are true
    */

        if(num_of_chars_in_symbol == 4){

            //buy
            print(symbol + " "+ "BUY!" + " using: " + strategyName);
            printAdvice(symbol, BUY);


        }else if (checkTime() == true){

            //sell
            print(symbol + " "+ "SELL!" + " using: " + strategyName);
            printAdvice(symbol, SELL);


        }else{
            //else hold
            print(symbol + " "+ "HOLD!" + " using: " + strategyName);
            printAdvice(symbol, HOLD);

        }

    }

    @Override
    public void initializeStrategy()  {


        time1 = "13:00:00"; //1pm
        time2 = "15:32:00"; //3:32pm

        Date date = null;
        try {
            date = new SimpleDateFormat("HH:mm:ss").parse(time1);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(date);
            time_1_minutes = (calendar1.getTime().getHours() * 60) + calendar1.getTime().getMinutes();

            Date date2 = new SimpleDateFormat("HH:mm:ss").parse(time1);
            Calendar calendar2 = Calendar.getInstance();
            calendar1.setTime(date2);
            time_2_minutes = (calendar2.getTime().getHours() * 60) + calendar2.getTime().getMinutes();

            Calendar calendar3 = Calendar.getInstance();
            time_current_minutes = (calendar3.getTime().getHours() * 60) + calendar3.getTime().getMinutes();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        num_of_chars_in_symbol = symbol.length();



    }

    @Override
    public void printStuff() {

        print("Class: " + getClass());
        print("time1: " + time1);
        print("time_1_minutes: " + time_1_minutes);
        print("time2: " + time2);
        print("time_2_minutes: " + time_2_minutes);
        print("time_current_minutes: " + time_current_minutes);
        print("num_of_chars_in_symbol: " + num_of_chars_in_symbol);
        print("symbol: " + symbol);

    }

    @Override
    public void print(String s) {

        System.out.println(s);

    }

    @Override
    public void refresh() throws IOException {

    }

    public boolean checkTime(){
        //if time is between those times, return true, else fales

        if(time_current_minutes > time_1_minutes && time_current_minutes < time_2_minutes){
            return true;
        }else{
            return false;
        }
    }

}
