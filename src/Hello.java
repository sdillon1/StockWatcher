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
            Stock stock = YahooFinance.get("INTC");

            BigDecimal price = stock.getQuote().getPrice();
            BigDecimal change = stock.getQuote().getChangeInPercent();
            BigDecimal peg = stock.getStats().getPeg();
            BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();

            stock.print();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
