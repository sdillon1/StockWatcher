import yahoofinance.Stock;

import java.io.IOException;

/**
 * Created by Sean on 3/31/2016.
 */
abstract public class Stock_Interface {

    public abstract Stock getStock();
    public abstract void setStock();
    public abstract int whatShouldIDo();
    public abstract void initializeStrategy();
    public abstract void printStuff();
    public abstract void print(String s);
    public abstract void refresh() throws IOException;

}
