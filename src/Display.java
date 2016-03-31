import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jskarda on 3/31/16.
 */
public class Display {
    JFrame jframe;
    JTextArea stockDisplay;
    JTextArea adviceDisplay;
    JRadioButton oneStrategy;
    JRadioButton alternateStrategy;
    JRadioButton randomStrategy;
    JRadioButton ownStrategy;
    ButtonGroup radioButtons;
    JComboBox chooseStock;
    private String[] stocks = {"Apple", "Google", "Symantec", "Pacsun"};


    public static void main(String[] args){
        Display display = new Display();
        display.go();
    }

    public void go(){
        jframe = new JFrame();

        //make stock display box
        stockDisplay = new JTextArea();
        stockDisplay.setEditable(false);
        stockDisplay.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        stockDisplay.setBounds(50, 50, 700, 300);

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
                if (chooseStock.getSelectedItem() == stocks[0]) {
                    //update stockDisplay
                    //update
                } else if (chooseStock.getSelectedItem() == stocks[1]) {

                } else if (chooseStock.getSelectedItem() == stocks[2]) {

                } else if (chooseStock.getSelectedItem() == stocks[3]) {

                }
            }
        });

        //make radio buttons
        oneStrategy = new JRadioButton("Buy if increased");
        oneStrategy.setSelected(true);
        oneStrategy.setBounds(610, 425, 150, 20);
        oneStrategy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
            }
        });
        alternateStrategy = new JRadioButton("Sell if increased");
        alternateStrategy.setBounds(610, 445, 150, 20);
        alternateStrategy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
            }
        });
        randomStrategy = new JRadioButton("Random strategy");
        randomStrategy.setBounds(610, 465, 150, 20);
        randomStrategy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
            }
        });
        ownStrategy = new JRadioButton("Crazy strategy");
        ownStrategy.setBounds(610, 485, 150, 20);
        ownStrategy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
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
        jframe.add(chooseStock);
        jframe.add(adviceDisplay);
        jframe.add(oneStrategy);
        jframe.add(alternateStrategy);
        jframe.add(randomStrategy);
        jframe.add(ownStrategy);
        jframe.setSize(800, 600);
        jframe.setLayout(null);
        jframe.setVisible(true);
    }
}
