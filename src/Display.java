import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by jskarda on 3/31/16.
 */
public class Display {
    JFrame jframe;
    JTextArea stockDisplay;
    JTextArea stockInfoDisplay;
    JTextArea adviceDisplay;
    JTextArea yourPortfolio;
    JRadioButton oneStrategy;
    JRadioButton alternateStrategy;
    JRadioButton randomStrategy;
    JRadioButton ownStrategy;
    ButtonGroup radioButtons;
    JComboBox chooseStock;
    JButton btnAddStock;
    private String[] stocks = {"Apple", "Google", "Symantec", "Pacsun"};

    BuyOnPriceIncrease buyOnPriceIncrease;
    BuyOnPriceDecrease buyOnPriceDecrease;
    RandomStrategy randomStrategyStrategy;
    String currentSymbol;
    Portfolio portfolio;

    public static void main(String[] args){
        Display display = new Display();
        display.go();
    }

    public void updateStockDisplay(Stock stock){
        stockDisplay.setText("");
        stockInfoDisplay.setText("");
        stockDisplay.append("Stock Symbol:" + '\n');
        stockInfoDisplay.append(stock.getSymbol() + '\n');
        stockDisplay.append("Current price of the stock:" + '\n');
        stockInfoDisplay.append("" + stock.getQuote().getPrice() + '\n');
        stockDisplay.append("Last trade day for the stock:" + '\n');
        stockInfoDisplay.append("" + stock.getQuote().getLastTradeDateStr() + '\n');
        stockDisplay.append("Last time of trade for the stock:" + '\n');
        stockInfoDisplay.append(""+ stock.getQuote().getLastTradeTimeStr() + '\n');
        stockDisplay.append("Price at which the stock opened the day:" + '\n');
        stockInfoDisplay.append(""+ stock.getQuote().getOpen() + '\n');
        stockDisplay.append("Highest price of the stock for the day:" + '\n');
        stockInfoDisplay.append(""+ stock.getQuote().getDayHigh() + '\n');
        stockDisplay.append("Lowest price of the stock for the day:" + '\n');
        stockInfoDisplay.append(""+ stock.getQuote().getDayLow() + '\n');
        stockDisplay.append("Price at which the stock closed the last day:" + '\n');
        stockInfoDisplay.append(""+ stock.getQuote().getDayHigh() + '\n');
        stockDisplay.append("Amount of stocks that were traded during the day:" + '\n');
        stockInfoDisplay.append(""+ stock.getQuote().getVolume() + '\n');
        stockDisplay.append("Company name:" + '\n');
        stockInfoDisplay.append(""+ stock.getName() + '\n');
        stockDisplay.append("Percent gain: \n");
        stockInfoDisplay.append("" + stock.getQuote().getChangeInPercent() + "%");
    }
    public void go(){
        jframe = new JFrame();

        //make stock display box
        stockDisplay = new JTextArea();
        stockDisplay.setEditable(false);
        stockDisplay.setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK));
        stockDisplay.setBounds(50, 50, 350, 300);

        stockInfoDisplay = new JTextArea();
        stockInfoDisplay.setEditable(false);
        stockInfoDisplay.setBorder(new MatteBorder(1, 0, 1, 1, Color.BLACK));
        stockInfoDisplay.setBounds(400, 50, 150, 300 ); //width was 350

        yourPortfolio = new JTextArea();
        yourPortfolio.setEditable(false);
        yourPortfolio.setBorder(new MatteBorder(1, 0, 1, 1, Color.BLACK));
        yourPortfolio.setBounds(500, 50, 200, 300 ); //250 left in width
        yourPortfolio.setText("dongus");

        portfolio = new Portfolio(yourPortfolio);


        //make advice display box
        adviceDisplay = new JTextArea();
        adviceDisplay.setEditable(false);
        adviceDisplay.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        adviceDisplay.setBounds(50, 425, 550, 100);

        //make dropdown
        chooseStock = new JComboBox(stocks);
        chooseStock.setBounds(50, 375, 150, 25);
        chooseStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(chooseStock.getSelectedItem());
                Stock stock;
                if (chooseStock.getSelectedItem() == stocks[0]) {
                    //update stockDisplay
                    //update
                    try {
                        stock = YahooFinance.get("AAPL");
                        updateStockDisplay(stock);
                    }catch(Exception ex){

                    }
                } else if (chooseStock.getSelectedItem() == stocks[1]) {
                    try {
                        stock = YahooFinance.get("GOOG");
                        updateStockDisplay(stock);
                    }catch(Exception ex){

                    }
                } else if (chooseStock.getSelectedItem() == stocks[2]) {
                    try {
                        stock = YahooFinance.get("SYMC");
                        updateStockDisplay(stock);
                    }catch(Exception ex){

                    }
                } else if (chooseStock.getSelectedItem() == stocks[3]) {
                    try {
                        stock = YahooFinance.get("PSUN");
                        updateStockDisplay(stock);
                    }catch(Exception ex){

                    }
                }
            }
        });

        btnAddStock= new JButton("Add 1 share");
        btnAddStock.setBounds(250, 375, 200, 50);
        btnAddStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //add 1 stock to currently selected stock
                portfolio.addStock(getSelectedSymbol(), 1);
            }
        });

        //make radio buttons
        oneStrategy = new JRadioButton("Buy if increased");
        oneStrategy.setSelected(true);
        oneStrategy.setBounds(610, 425, 150, 20);
        oneStrategy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //buy on price increase
                currentSymbol = getSelectedSymbol();
                try {
                    buyOnPriceIncrease = new BuyOnPriceIncrease(currentSymbol, adviceDisplay);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        alternateStrategy = new JRadioButton("Sell if increased");
        alternateStrategy.setBounds(610, 445, 150, 20);
        alternateStrategy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentSymbol = getSelectedSymbol();

                try {
                    buyOnPriceDecrease = new BuyOnPriceDecrease(currentSymbol, adviceDisplay);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
        randomStrategy = new JRadioButton("Random strategy");
        randomStrategy.setBounds(610, 465, 150, 20);
        randomStrategy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentSymbol = getSelectedSymbol();

                try {
                    randomStrategyStrategy = new RandomStrategy(currentSymbol, adviceDisplay);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
        ownStrategy = new JRadioButton("Crazy strategy");
        ownStrategy.setBounds(610, 485, 150, 20);
        ownStrategy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentSymbol = getSelectedSymbol();
            }
        });




        //make button group
        radioButtons = new ButtonGroup();
        radioButtons.add(oneStrategy);
        radioButtons.add(alternateStrategy);
        radioButtons.add(randomStrategy);
        radioButtons.add(ownStrategy);


        //add components to display
        jframe.add(stockDisplay);
        jframe.add(stockInfoDisplay);
        jframe.add(yourPortfolio);
        jframe.add(chooseStock);
        jframe.add(btnAddStock);
        jframe.add(adviceDisplay);
        jframe.add(oneStrategy);
        jframe.add(alternateStrategy);
        jframe.add(randomStrategy);
        jframe.add(ownStrategy);
        jframe.setSize(800, 600);
        jframe.setLayout(null);
        jframe.setVisible(true);
    }

    public String getSelectedSymbol(){

        String returnString = null;

        if (chooseStock.getSelectedItem() == stocks[0]) {

            print (chooseStock.getSelectedItem().toString());
            returnString = "AAPL";

        } else if (chooseStock.getSelectedItem() == stocks[1]) {

            print (chooseStock.getSelectedItem().toString());
            returnString = "GOOG";

        } else if (chooseStock.getSelectedItem() == stocks[2]) {

            print (chooseStock.getSelectedItem().toString());
            returnString = "SYMC";

        } else if (chooseStock.getSelectedItem() == stocks[3]) {

            print (chooseStock.getSelectedItem().toString());
            returnString = "PSUN";

        }

        return returnString;

    }

    public void print(String s){
        System.out.println(s);
    }

    public void printAdvice(int whichOne){

        //whichOne will be 1 2 or 3 for buy hold sell

        String advice = null;

        if(whichOne == 1) {
            advice = "BUY";
        }else if(whichOne == 2){
            advice = "HOLD";
        }else if(whichOne == 3){
            advice = "SELL";
        }

        print("An old wise man said to " + advice + " the stock");

    }

}
