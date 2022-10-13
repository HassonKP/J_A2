package com.company;
import java.util.concurrent.SynchronousQueue;
/*Name: Killian Hasson      Date: 06/06/21      Class:CS202
*   This is the RBTree class which really only adds, displays, finds a specific node, and deletes the tree. I tried to
* do things recursively but it was far too complicated especially when trying to fix the tree and rotate subtrees. I
* spent hours reading to better understand rb trees and how to actually implement one, the fix insert function needs
* relied heavily on getting each nodes parent and color which was something that I had to add to the RBnode class late
* into the implementation. The RB tree works in practice at least for small basic trees I didn't truly test every
* possibility as with it being done iteratively I was really getting caught up in a lot of the different conditionals.
*
*
* */

public class RBTree {
    //only data member is root
    RBnode root;

    //Constructor to set root to null
    public RBTree(){
        root = null;
    }

    //Insert function for taking a parse tree and creating a new node for it
    public void insert(ParseTree to_add) {
        //Create a new node with the passed in Parse Tree obj
        RBnode new_node = new RBnode(to_add);
        //Temp node for linking later on
        RBnode temp = null;
        RBnode curr = root;
        //Try to navigate through the tree using curr node
        while (curr != null) {
            //set temp to curr
            temp = curr;
            if (new_node.getData().compareTo(curr.getData()) < 0) {
                curr = curr.getLeft();
            } else {
                curr = curr.getRight();
            }
        }
        //set temp to parent of new node
        new_node.setPrev(temp);
        //If parent doesnt exist, list empty
        if (temp == null) {
            //set root to the new node
            root = new_node;
            //else we need to decide if new node goes to the right or left
        } else if (new_node.getData().compareTo(temp.getData()) < 0) {
            temp.setLeft(new_node);
        } else {
            temp.setRight(new_node);
        }
        // if new node is a root node set color and return
        if (new_node.getPrev() == null){
            new_node.setColor(0);
            return;
        }
        //If the new nodes parents parent is null just return
        if (new_node.getPrev().getPrev() == null) {
            return;
        }

        //call the fix the tree function
        fixTree(new_node);
    }

    //Based on where the RBnode is inserted the function will need to correct the RB tree and utilize correct rotation
    //Had to do this iteratively in order to actually follow my extremely basic understanding of this
    private void fixTree(RBnode new_node){
        //Depending on the current tree shape temp will be given different positions
        RBnode temp;
        //While the new nodes parent is Red
        while (new_node.getPrev().getColor() == 1) {
            //Check if new nodes parent is a right child
            if (new_node.getPrev() == new_node.getPrev().getPrev().getRight()) {
                //set temp to uncle
                temp = new_node.getPrev().getPrev().getLeft(); //set temp to uncle
                //If the new nodes uncle exists and its color is also red we need to change the uncles color
                if (temp != null && temp.getColor() == 1) {
                    //Uncle changed to black
                    temp.setColor(0);
                    //the node being added has its parent changed to black as well
                    new_node.getPrev().setColor(0);
                    //Ensure the grandparent is red
                    new_node.getPrev().getPrev().setColor(1);
                    new_node = new_node.getPrev().getPrev();
                } else {
                    //Check if new node is a left child of their parent
                    if (new_node == new_node.getPrev().getLeft()) {
                        //We will need to rotate right using the parents position
                        new_node = new_node.getPrev();
                        rotateR(new_node);
                    }
                    //Now ensure the parents and grandparents are the correct color
                    new_node.getPrev().setColor(0);
                    new_node.getPrev().getPrev().setColor(1);
                    //A right rotation was made using the added nodes parent make a left rotation using grandparent
                    rotateL(new_node.getPrev().getPrev());
                }
                //Else if new nodes parent wasn't a right child
            } else {
                temp = new_node.getPrev().getPrev().getRight();
                //If new nodes uncle exists and is red
                if (temp != null && temp.getColor() == 1) {
                    //Uncle changed to black
                    temp.setColor(0);
                    //the node being added has its parent changed to black as well
                    new_node.getPrev().setColor(0);
                    //Grandparent is set to red
                    new_node.getPrev().getPrev().setColor(1);
                    //Grandparent
                    new_node = new_node.getPrev().getPrev();
                } else {
                    //Is new node a right child
                    if (new_node == new_node.getPrev().getRight()) {
                        //Rotate left using new nodes parent
                        new_node = new_node.getPrev();
                        rotateL(new_node);
                    }
                    //Parent is black
                    new_node.getPrev().setColor(0);
                    //Grandparent is red
                    new_node.getPrev().getPrev().setColor(1);
                    //Rotate right using grandparent
                    rotateR(new_node.getPrev().getPrev());
                }
            }
            //Check if grandparent is root, since color is changed return
            if (new_node == root) {
                return;
            }
        }
        //Make sure root is black
        root.setColor(0);
    }

    //Function to iteratively rotate right to fix the RB tree
    public void rotateR(RBnode new_node) {
        //Using temp for the left child
        RBnode temp = new_node.getLeft();
        //Setting the right child of temp to be new nodes left
        new_node.setLeft(temp.getRight());
        //Ensure that new nodes left child has a right child
        if (temp.getRight() != null) {
            //Properly link the right child to the new node
            temp.getRight().setPrev(new_node);
        }
        //Ensure that the nodes are properly linked
        temp.setPrev(new_node.getPrev());
        //If the new node doesn't have a parent
        if (new_node.getPrev() == null) {
            root = temp;
            //Check if new node is a right child
        } else if (new_node == new_node.getPrev().getRight()) {
            //Set temp to c
            new_node.getPrev().setRight(temp);
        } else {
            //Set temp to be left child of parent
            new_node.getPrev().setLeft(temp);
        }
        //Complete rotation
        temp.setRight(new_node);
        new_node.setPrev(temp);
    }

    //Function to properly rotate this subtree left
    public void rotateL(RBnode new_node) {
        //set temp to the new nodes right
        RBnode temp = new_node.getRight();
        //Swap temps left child to new nodes right
        new_node.setRight(temp.getLeft());
        //If temps left exists
        if (temp.getLeft() != null) {
            //Properly link
            temp.getLeft().setPrev(new_node);
        }
        //Set temp to new nodes parent
        temp.setPrev(new_node.getPrev());
        //If new node doesnt have a parent
        if (new_node.getPrev() == null) {
            root = temp;
            //check if new node is a left child
        } else if (new_node == new_node.getPrev().getLeft()) {
            new_node.getPrev().setLeft(temp);
        } else {
            new_node.getPrev().setRight(temp);
        }
        //Finish rotation
        temp.setLeft(new_node);
        new_node.setPrev(temp);
    }

    //Find function calls the recursive version and returns the found Parse Tree
    public ParseTree find(String Name){
        return find(this.root, Name);
    }

    //The recursive find function, takes in a name and returns the parse tree that has that name
    public ParseTree find(RBnode curr, String Name) {
        //Look for matching name
        if (curr.getData().compareTo(Name) == 0) {
            return curr.getTree();
        }
        //Below if statements are to properly traverse the RB tree for the name
        if (curr.getData().compareTo(Name) < 0) {
            //System.out.println("Going RIGHT");
            return find(curr.getRight(), Name);
        }
        if (curr.getData().compareTo(Name) > 0) {
            //System.out.println("Going LEFT");
            return find(curr.getLeft(), Name);
        }

        return curr.getTree();
    }

    //Display function
    public void display() {
        if (this.root == null) return;
        System.out.println("Red Black Tree:");
        this.display(this.root, "", "");
    }
    //Display function trying to implement a pointer type of way to actually represent the Parse tree to insure that
    //The tree is built properly
    public void display(RBnode temp, String prefix, String pointer) {
        if (temp == null) return;
        if (temp.getData() != null) {
            System.out.println(prefix + pointer + " " + temp.getData() + " " + temp.getColor());
        }
        if (temp.getLeft() != null) {
            //System.out.println(prefix + "/");
            this.display(temp.getLeft(), prefix + "   ", ((temp.getRight() != null) ? "├──" : "└──"));
        }
        if (temp.getRight() != null) {
            //System.out.println(prefix + "\\");
            this.display(temp.getRight(), prefix + "   ", "└──");
        }
    }

    //Call the recursive delete function passing in root
    public void delete(){
        if(root == null){
            System.out.println("The RB Tree has been deleted.");
        }
        delete(this.root);
    }

    //Delete the RB tree using depth first traversal
    public void delete(RBnode curr){
        if(curr.getRight() != null) {
            delete(curr.getRight());
        }
        else if(curr.getLeft() != null){
            delete(curr.getLeft());
        }
        curr.setLeft(null);
        curr.setRight(null);
        curr.setPrev(null);
        root = null;
    }
}
