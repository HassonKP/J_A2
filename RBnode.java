package com.company;
/*Name: Killian Hasson      Date: 06/05/21      Class:CS202
*
*   This class is set up for a red black node which will need to have a left and right node, a parent node to help with
* managing a RB tree and checking colors, a integer for color, and a ParseTree obj as the nodes data. I started originally
* with just the typical left right and data but realized there would need to be much more to this as comparing data and
* checking colors of nodes is extremely important. I had to even call a Parse Tree function inside of my getdata function
* and wish I would have labeled this as something else as it got confusing at points when getData returned a string and
* getTree returns the data basically.
*
* */

public class RBnode{
private ParseTree data;//every node has a ParseTree object
//1 - Red
//0 - Black
private int color;//Color of the RBnode to make sure things are correct
private RBnode prev;//parent node
private RBnode left;//left node
private RBnode right;//right node

    //Basic RBnode constructor taking in a Parse Tree Obj
    public RBnode(ParseTree temp)
    {
        data = temp;
        color = 1;
        prev = null;
        left = null;
        right = null;
    }
    //Return the name of the parse tree
    public String getData(){
        //Call the get name function to return the name string
        return this.data.getName();
    }
    //A function to return just a parse tree object of the RBnode
    public ParseTree getTree(){
        return data;
    }
    //Return the parent node
    public RBnode getPrev() {
        return prev;
    }
    //Set and change the color of the node
    public void setColor(int color) {
        this.color = color;
    }
    //Return the nodes color, will be an int
    public int getColor(){
        return this.color;
    }
    //set an existing nodes data to a new Parse Tree
    public void setData(ParseTree temp){
        data = temp;
    }
    //Set a nodes parent to another node
    public void setPrev(RBnode pn){
        this.prev = pn;
    }
    //Assign a node to the nodes left
    public void setLeft(RBnode lt) {
        this.left = lt;
    }
    //Assign a node to the nodes right
    public void setRight(RBnode rt) {
        this.right = rt;
    }
    //Return the nodes left node
    public RBnode getLeft() {
        return left;
    }
    //Return the nodes right node
    public RBnode getRight(){
        return right;
    }
}
