package com.company;

/*Name: Killian Hasson      Date: 06/04/2021        Class: CS202
*
*   This is a super basic node class, nothing really new or unique about this. I had plans initially for the getRightMost
* function with the plan of shifting around in a different way when building the Parse Tree.*/

//Node class
public class node {
    private node left;//Left node
    private node right;//Right node
    private Token data;//Contains a token in each node

    //Constructor for the node, takes a token as an argument
    public node(Token temp){
        data = temp;
    }
    //Gets the left node
    public node getLeft() {
        return this.left;
    }
    //Sets the node taken as an argument to the left of the current node
    public void setLeft(node lt) {
        this.left = lt;
    }
    //Gets the right node
    public node getRight() {
        return this.right;
    }
    //Sets the node taken as an argument to the right of the current node
    public void setRight(node rt) {
        this.right = rt;
    }
    //Returns the data aka the token
    public Token getData() {
        return this.data;
    }
    //Sets the token taken as the argument as the data of the current node
    public void setData(Token t) {
        this.data = t;
    }

    //Ended up not using this function
    public node getRightMost(){
        if (this.getRight() != null){
            this.getRight();
            this.getRightMost();
        }
        return this;
    }
}