package com.company;
import java.util.LinkedList;
/*Name: Killian Hasson      Date: 06/04/2021        Class:CS202
*
*   This class and its functions are the main portion of the program, it will do all the work required to properly
* parse the equation entered in main. There will be basically 3 steps to this, step 1 is to go through each index in
* the equation string and create tokens for each index, step 2 each token is added into a linked list, step three
* the Parse function went through the list and a tree was built where order and set up depended on the kind of token
* found in each index of the list. Finally a display function was made in order to properly display the parse tree and
* verify it was built and set up correctly.*/

public class ParseTree {
    private String name;
    private String input;//This is the equation
    private LinkedList<Token> list = new LinkedList<Token>();//List of tokens to help make the parse tree easier
    private node root;//Node used for the creation of the Parse Tree

    //Basic constructor for the Parse Tree
    public ParseTree(String Name, String equation) {
        name = Name;
        input = equation;
        root = null;
        //this.tokenizer();
    }
    //Function to return the name attached to the parse tree
    public String getName(){
        return this.name;
    }

    //This function is the first to be used, It will create tokens based on the index in the string and add them into
    //The list
    public void tokenizer() {
        //int root_index = input.indexOf('=');
        //For loop to go through each index in the string input
        for (int i = 0; i < input.length(); i++) {
            //Temp char holder
            char test_char = input.charAt(i);
            //Conditional to find variables in the equation and create a VarToken
            if (Character.isLetter(test_char)) {
                Token temp = new VarToken(Character.toString(test_char));
                list.add(temp);//Add the VarToken to the list
            }
            //For this we will need to check if how many indexes long the digit is, 1 vs 10 vs 100
            //Maybe once a digit in found keep checking till the first index returns not a digit?
            else if (Character.isDigit(test_char)) {
                Token temp = new IntToken(Character.toString(test_char));
                list.add(temp);
            } else if (Character.isWhitespace(test_char)) {
                // Ignore whitespaces
            } else {
                //Operators are difficult to account for since they arent ints or chars in this program so just a base
                //else should suffice
                Token temp = new OpToken(Character.toString(test_char));
                list.add(temp);
            }
        }
        parse();//Call the parse function
    }

    //The parse function will properly use the tokens created before to create a parse tree
    public void parse() {
        //The node added in previous iteration
        node temp = null;
        //The temp node's parent node
        node temp_parent = null;
        boolean found_root = false;
        //Go through each index in the list
        for (int i = 0; i < list.size(); i++) {
            //At this index set a temp base token to the correct token
            Token t = list.get(i);
            //If the tree is empty just assign the first token to root
            if (temp == null) {
                root = new node(t);
                temp = root;
                continue;
            }
            //Any time an Operator Token is found it will have to be the parent, couldn't have a OpToken as a leaf.
            if (t instanceof OpToken) {
                //We know that if the OpToken is "=" and is found this will need to become the root
                if (temp_parent != null && !found_root) {
                    //Continue building left side until = is found
                    //Replace the root
                    root = new node(t);
                    root.setLeft(temp_parent);
                    temp = root;
                    //Conditional for once the OpToken found is actually "="
                    if (t.getInfo().equals("=")) {
                        // = is the root of our tree
                        found_root = true;
                    }
                } else if (temp_parent == null) {
                    //Replace the root
                    root = new node(t);
                    root.setLeft(temp);
                    temp = root;
                } else {
                    node temp2 = new node(t);
                    temp2.setLeft(temp);
                    temp = temp2;
                    temp_parent.setRight(temp2);
                }
            } else {
                //The other tokens arent necesarrily as important as far as placement in a tree goes, the Variables and
                //and integers can both be leafs
                temp.setRight(new node(t));
                temp_parent = temp;
                temp = temp.getRight();
            }
        }
    }


    //Base display function, tried implementing a unique way of handling a display function
    public void display() {
        if (this.root == null) return;
        System.out.println("parse tree for " + name);
        this.display(this.root, "", "");
    }
    //Display function trying to implement a pointer type of way to actually represent the Parse tree to insure that
    //The tree is built properly
    public void display(node temp, String prefix, String pointer) {
        if (temp == null) return;
        if (temp.getData() != null) {
            System.out.println(prefix + pointer + " " + temp.getData().getInfo());
        }
        if (temp.getLeft() != null) {
            this.display(temp.getLeft(), prefix + "   ", ((temp.getRight() != null) ? "├──" : "└──"));
        }
        if (temp.getRight() != null) {
            this.display(temp.getRight(), prefix + "   ", "└──");
        }
    }


}
