package com.company;
import java.util.Scanner;
/*Name: Killian Hasson      Date: 06/04/2021        Class: CS202
*
*   For Program 4, I created a parser that would create 3 kinds of tokens based on a string that is set up as
* an equation. Once the tokens were created I found it easiest to place them in a list so the order would stay
* as is. The list is then taken and a Parse Tree is created using these tokens, I wanted root to always be the "="
* symbol as the equation is typically thought of as being two halves. Each parent node would also be a operator
* such as "+" or "-". I also built a display function in order to  make sure it was actually building properly.
* My parser is pretty basic and theres a lot more I would have liked to implement and do.*/
public class Main {

    public static void main(String[] args) {
        // write your code here
        //Basic strings will be created for the users name and the equation they want parsed
        RBTree assignments = new RBTree();
        Scanner userInput = new Scanner(System.in);
        String Name = "";
        String equation = "";
        String resp = "";
        do {
            System.out.print("What would you like to do? Enter 1 to add a new equation, Enter 2 to display all names currently entered, Enter 3 to display the parse tree of a specific name, Enter 0 to exit: ");
            resp = userInput.nextLine();
            if (resp.charAt(0) == '1') {
                //Get the name, will be needed for the balanced tree in Program 5
                System.out.print("Enter in your name: ");
                if (userInput.hasNextLine()) {
                    Name = userInput.nextLine();
                }

                //Get the equation
                System.out.print("Enter in your Mathematical expression: ");
                if (userInput.hasNextLine()) {
                    equation = userInput.nextLine();
                    //Create a new parse tree with the equation
                    ParseTree myEquation = new ParseTree(Name, equation);
                    myEquation.tokenizer();//Call the function to turn the equation into tokens
                    //myEquation.display();//Display the Parse tree
                    assignments.insert(myEquation);
                }
            }
            if (resp.charAt(0) == '2') {
                assignments.display();
            }
            if (resp.charAt(0) == '3') {
                System.out.print("Enter the name to find: ");
                Name = userInput.nextLine();
                ParseTree to_find = assignments.find(Name);
                to_find.display();
            }

        } while (resp.charAt(0) != '0');
    }
}
    /*WHAT I USED FOR TESTING STUFF
    /*public static void main(String[] args) {

        // write your code here
        //Basic strings will be created for the users name and the equation they want parsed
        ParseTree to_find;
        RBTree assignments = new RBTree();
        Scanner userInput = new Scanner(System.in);
        ParseTree myEquation = new ParseTree("d", "4 = x");
        assignments.insert(myEquation);
        myEquation = new ParseTree("c", "x = 45");
        assignments.insert(myEquation);
        myEquation = new ParseTree("f", "x = 3");
        assignments.insert(myEquation);
        myEquation = new ParseTree("a", "x = 3");
        assignments.insert(myEquation);
        myEquation = new ParseTree("b", "x = 3");
        assignments.insert(myEquation);
        myEquation = new ParseTree("q", "x = 3");
        assignments.insert(myEquation);
        myEquation = new ParseTree("x", "x = 3");
        assignments.insert(myEquation);
        myEquation = new ParseTree("t", "x = 3");
        assignments.insert(myEquation);
        myEquation = new ParseTree("w", "x = 3");
        assignments.insert(myEquation);
        assignments.display();
        to_find = assignments.find("t");
        to_find.display();
        assignments.delete();
        assignments.display();
        /*
        String Name = "";
        String equation = "";
        String resp = "";
}
     */


