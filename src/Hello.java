import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by Sean on 3/31/2016.
 */
public class Hello {


    public static void main(String args[]) {

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


    }

}
