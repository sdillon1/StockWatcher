/**
 * Created by Sean on 4/3/2016.
 */
public class PortfolioStock {

    String symbol;
    int numShares;

    public PortfolioStock(String s, int n){
        symbol = s;
        numShares = n;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getNumShares() {
        return numShares;
    }

    public void setNumShares(int numShares) {
        this.numShares = numShares;
    }

    public void addMoreStock(int sharesToAdd){
        numShares = numShares + sharesToAdd;
    }

    public void sellSomeStock(int sharesToSell){
        numShares = numShares - sharesToSell;
    }

    public void printStock(){
        System.out.println("Symbol: " + symbol + " Shares: " + numShares);
    }
}
