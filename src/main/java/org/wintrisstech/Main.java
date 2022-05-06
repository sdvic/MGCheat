package org.wintrisstech;
/**********************************************************************
 * Wordle Cheat derived from Memory Game Reference
 * version 220506
 **********************************************************************/
import org.apache.commons.lang3.StringUtils;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;

import static java.awt.Font.BOLD;
public class Main extends Canvas
{
    private ArrayList<String> fiveLetterWordList = new ArrayList<>();
    private JFrame mainWindow; // GUI related instance variables
    private JButton[] buttons;
    private String userInput = "Fake user input";
    private ArrayList<String> list = new ArrayList<String>();
    private Scanner s = null;
    private String secretWord;
    Scanner sc = new Scanner(System.in);       // Scanner object
    private String[] word;
    private Runnable runner;
    Font f = new Font("Helvitica",BOLD, 33);
    public static void main(String[] args)
    {
        Runnable runner = () ->
            new Main().getGoing();
        SwingUtilities.invokeLater(runner);
    }
    private void getGoing()
    {
        mainWindow = new JFrame("Wordle Cheat");
        buttons = new JButton[30];
        mainWindow.setSize(600, 400);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLayout(new GridLayout(6, 5, 5, 5)); // Create a layout with rows = 4, 5 pixel gaps.
        for (int i = 0; i < 30; i++)  // Create the array of 30 buttons; add to JFrame.
        {
            buttons[i] = new JButton();
            int column = (i % 5);
            int row = ((i/5) % 6);
            buttons[i].setMnemonic(i); //Add ident number to button
            buttons[i].setText((row) + "" + column);
            buttons[i].addActionListener(this::actionPerformed); // Add The Action Listener To The Buttons
            mainWindow.add(buttons[i]);
        }
        mainWindow.setVisible(true);
        try {s = new Scanner(new File("/Users/vicwintriss/git/MGCheat/src/main/java/org/wintrisstech/fiveLetterWords.txt"));}
        catch (FileNotFoundException e) {throw new RuntimeException(e);}
        while (s.hasNext())
        {
            fiveLetterWordList.add(s.next());
        }
        s.close();
        //fiveLetterWordList.forEach(e -> System.out.println(e));
        System.out.println("fiveLetterWords.size() => " + fiveLetterWordList.size());
        secretWord = fiveLetterWordList.get(new Random().nextInt(5757));
        System.out.println("SecretWord => " + secretWord);
        processInput(secretWord);
    }
    private void processInput(String userInput)
    {
        String input = "";
        word = new String[5];
        String temp = "";
        StringBuilder sb = new StringBuilder(userInput);
        for (int i = 0; i < 5; i++)
        {
            buttons[i].setFont(f);
            input = sc.nextLine();
            buttons[i].setText(input);
            System.out.println("input => " + input);
            word[i] = input;
            SwingUtilities.invokeLater((runner));
        }
        sc.close();
        String str = StringUtils.join(word,"");
        Arrays.stream(word).forEach(System.out::print);
        System.out.println("\nSecret word => " + secretWord + ", word => " + str);
        if (str.equals(secretWord))
        {
            System.out.println("Hooray");
        }
    }
    public void actionPerformed(ActionEvent e)
    {
        repaint();
        JButton selectedButton = (JButton)e.getSource();
        int buttonNumber = selectedButton.getMnemonic();
        int buttonText = Integer.parseInt(selectedButton.getText());
        System.out.println("Hi there..." + buttonText + " " + buttonNumber);
        selectedButton.setForeground(Color.RED);
        selectedButton.setBackground(Color.GREEN);
        selectedButton.setOpaque(true);
    }
}
