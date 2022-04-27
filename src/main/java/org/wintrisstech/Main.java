package org.wintrisstech;
/**********************************************************************
 * Vic's version 10.0
 * Refactor 4/26/2022
 * version 220426
 **********************************************************************/
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
public class Main extends Canvas
{
    private JFrame mainWindow; // GUI related instance variables
    private JButton[] buttons;
    public static void main(String[] args)
    {
        Runnable runner = () -> {
            new Main().getGoing();
        };
        SwingUtilities.invokeLater(runner);
    }
    private void getGoing()
    {
        Toolkit t = Toolkit.getDefaultToolkit();
        /**************************************************************
         * Instantiate classes, set up windows and panels.
         **************************************************************/
        mainWindow = new JFrame("Wordle Cheat");
        buttons = new JButton[30];
        mainWindow.setSize(600, 400);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLayout(new GridLayout(6, 5, 5, 5)); // Create a layout with rows = 4, 5 pixel gaps.
        /**********************************************************************************
         * Make an array of buttons and add them to the JFrame; add button listeners
         **********************************************************************************/
        for (int i = 0; i < 30; i++)  // Create the array of 30 buttons; add to JFrame.
        {
            buttons[i] = new JButton();
            buttons[i].setMnemonic(i); //Add ident number to button
            buttons[i].addActionListener(this::actionPerformed); // Add The Action Listener To The Buttons
            buttons[i].setVisible(true);
            mainWindow.add(buttons[i]);
        }
        mainWindow.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        repaint();
        System.out.println("Hi there..." + e);
    }
}
