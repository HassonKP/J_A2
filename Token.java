package com.company;

/*Name: Killian Hasson      Date: 06/04/2021        Class: CS202
*
*   The token class is basic, the derived classes could in theory differentiate between kinds of info I can only really
* think of the OpToken using this feature. Perhaps the OpToken will be able to say which operator it actually is, but
* this could also be handled in the ParseTree with good conditionals.*/

//Base class for a token
public class Token {
    protected String info;
    public Token(String temp) {
        this.info = temp;
    }
    public String getInfo() {
        return this.info;
    }
    public void setInfo(String s) {
        this.info = s;
    }
}

//Derived class for a variable token any variable found in the equation
class VarToken extends Token{
    public VarToken(String temp){
        super(temp);
    }
}

//Derived class for an integer token and numbers
class IntToken extends Token{
    public IntToken(String temp){
        super(temp);
    }

}

//Derived class for an operator token "+" "-" "/"
class OpToken extends Token{
    public OpToken(String temp){
        super(temp);
    }
}