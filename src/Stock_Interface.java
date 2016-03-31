import yahoofinance.Stock;

import java.io.IOException;

/**
 * Created by Sean on 3/31/2016.
 */
public interface Stock_Interface {

    public Stock getStock();
    public void setStock();
    public int whatShouldIDo();
    public void initializeStrategy();
    public void printStuff();
    public void print(String s);
    public void refresh() throws IOException;

}
