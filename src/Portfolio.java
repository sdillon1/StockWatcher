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

        writeToStockFile();



    }

    public void addToTextArea(String s){
        jTextArea.append(" " + s);
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

    public void printPortfolio(){
        print("moneyInTheBank: " + moneyInTheBank);

        if(stockArrayList.size() == 0){
            print("No stocks owned");
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

}
