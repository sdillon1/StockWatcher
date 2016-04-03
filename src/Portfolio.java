import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Sean on 4/3/2016.
 */

/*
 *  This class will keep track of which stocks the user owns as well as how much money he has left
 */
public class Portfolio {

    double moneyInTheBank;
    boolean isError = false;
    String errorString = "";
    JTextArea jTextArea;
    PortfolioStock stockArray [];
    ArrayList<PortfolioStock> stockArrayList;

    public Portfolio(JTextArea j){

        jTextArea = j;

        stockArrayList = new ArrayList<PortfolioStock>();


        initializePortfolio();
    }




    public void initializePortfolio(){

        readBankMoney();
        jTextArea.setText("");
        addToTextArea("Your Portfolio\n");
        addToTextArea("********************\n");
        addToTextArea("Current MoneyInDaBank: " + moneyInTheBank);

        /*addStock("PSUN", 69);
        addStock("SYMC", 23);
        addStock("PSUN", 31);*/

        readStocksOwned();

        printPortfolio();
        updateTextArea();

        writeToStockFile();



    }

    public void updateTextArea(){
        jTextArea.setText("                   ***_Portfolio_***\n\n");
        addToTextArea("         $$__YOUR_DOLLAS__$$: \n" + "$" + moneyInTheBank + "\n");
        writePortfolioToDisplay();
        if(isError == true){
            printError();
            isError = false;
        }

        writeBankMoney();

    }

    public void setError(String error){
        isError = true;
        errorString = error;
    }

    public void printError(){
        addToTextArea("\n" + errorString);
    }

    public void addToTextArea(String s){
        jTextArea.append(s + "\n");
    }

    public void writePortfolioToDisplay(){
        if(stockArrayList.size() == 0){
            print("No stocks owned");
        }else {
            for (int i = 0; i < stockArrayList.size(); i++) {
                stockArrayList.get(i).addStockToDisplay(jTextArea);
            }
        }
    }

    public void printPortfolio(){

        print("\n**********");
        print("Portfolio");
        print("moneyInTheBank: " + moneyInTheBank);

        if(stockArrayList.size() == 0){
            print("No stocks owned");
            setError("No stocks owned");
        }else {
            for (int i = 0; i < stockArrayList.size(); i++) {
                stockArrayList.get(i).printStock();
            }
        }

    }

    public void print(String s){
        System.out.println(s);
    }

    public void addStock(String symbol, int numSharesToAdd){

        int whereToAdd = getStockIndex(symbol);

        if(whereToAdd == -1){

            //symbol was not found so add stock at the end
            stockArrayList.add(new PortfolioStock(symbol, numSharesToAdd));

        }else{

            //just update the shares owned
            stockArrayList.get(whereToAdd).addMoreStock(numSharesToAdd);

        }

        print("Stock added");
        printPortfolio();
        updateTextArea();
        //writeToStockFile();

    }

    public void buyStock (String symbol, int numSharesToAdd, double price){

        int whereToAdd = getStockIndex(symbol);

        if(whereToAdd == -1){

            if(canBuy(price, numSharesToAdd) == true) {

                //symbol was not found so add stock at the end
                stockArrayList.add(new PortfolioStock(symbol, numSharesToAdd));
                moneyInTheBank = moneyInTheBank - (numSharesToAdd * price);
                print("Stock bought");
            }else{
                print("Error: not enough money");
                setError("Error: not enough money");
            }

        }else{

            if(canBuy(price, numSharesToAdd) == true) {
                //just update the shares owned
                stockArrayList.get(whereToAdd).printStock();
                stockArrayList.get(whereToAdd).addMoreStock(numSharesToAdd);
                stockArrayList.get(whereToAdd).printStock();
                moneyInTheBank = moneyInTheBank - (numSharesToAdd * price);
                print("Stock bought");
            }else{
                print("Error: not enough money");
                setError("Error: not enough money");
            }

        }


        printPortfolio();
        updateTextArea();
        writeToStockFile();

    }

    public void sellStock (String symbol, int numSharesToSell, double price){

        int whereToAdd = getStockIndex(symbol);

        if(whereToAdd == -1){

            //symbol was not found so add stock at the end
            print("Error: The stock is not there");
            setError("Error: The stock is not there");

        }else{

            if(canSell(numSharesToSell, stockArrayList.get(whereToAdd).getNumShares()) == true) {

                //just update the shares owned
                stockArrayList.get(whereToAdd).sellSomeStock(numSharesToSell);
                moneyInTheBank = moneyInTheBank + (numSharesToSell * price);
                print("Stock sold");
            }else{
                print("Error: not enough stocks owned to sell");
                setError("Error: not enough stocks owned to sell");
            }

        }


        printPortfolio();
        updateTextArea();
        writeToStockFile();

    }

    public int getStockIndex(String symbol){
        for(int i = 0; i < stockArrayList.size(); i++){
            if(stockArrayList.get(i).getSymbol().equals(symbol)){
                return i;
            }
        }

        //the stock symbol was not found in the current portfolio so add it at the end
        return -1;
    }

    public void writeToStockFile(){
        // The name of the file to open.
        String fileName = "src/portfolio_stocks.txt";

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                    new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.

            for(int i = 0; i < stockArrayList.size(); i++){
                bufferedWriter.write(stockArrayList.get(i).getSymbol());
                bufferedWriter.newLine();
                bufferedWriter.write(String.valueOf(stockArrayList.get(i).getNumShares()));
                bufferedWriter.newLine();
            }


            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

    public void readStocksOwned(){

// The name of the file to open.
        String fileName = "src/portfolio_stocks.txt";

        // This will reference one line at a time
        String sym = null;
        String numString = null;
        int num;


        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((sym = bufferedReader.readLine()) != null) {

                numString =  bufferedReader.readLine();
                num = Integer.parseInt(numString);

                addStock(sym, num);

            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

    public void readBankMoney(){

// The name of the file to open.
        String fileName = "src/bank.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                moneyInTheBank = Double.parseDouble(line);

            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

    public void writeBankMoney(){

// The name of the file to open.
        String fileName = "src/bank.txt";

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                    new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.

            for(int i = 0; i < stockArrayList.size(); i++){
                bufferedWriter.write(String.valueOf(moneyInTheBank));
                bufferedWriter.newLine();

            }


            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

    public boolean canBuy(double price, int numSharesToBuy){

        //check if there is enough money in your bank

        if(price * numSharesToBuy <= moneyInTheBank){
            //can buy
            return true;
        }else {
            return false;
        }
    }

    public boolean canSell(int numSharesToSell, int numSharesOwned){

        //check if you have any stocks to sell

        if(numSharesToSell <= numSharesOwned){
            //can sell
            return true;
        }else {
            return false;
        }
    }

}
