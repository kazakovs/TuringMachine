package net.kazakovs;

import java.io.*;

/**
 * Created by reefer on 24.10.16.
 */
public class Machine<T> {

    private Tape<T> tape;
    private Rules rules;
    private int currentState;

    private Machine(int state, T blankSym, T[] symbols){
        this.tape = new Tape<>(symbols.length*4, blankSym, symbols);
        this.currentState = state;
    }

    public Machine(String[] rules, T blankSym, T[] symbols){
        this(1, blankSym, symbols);
        this.rules = new Rules(rules);
    }

    public Machine(String filename, T blankSym, T[] symbols){
        this(1, blankSym, symbols);
        String[] rules = Utils.parseFile(filename);
        this.rules = new Rules(rules);
    }

    public Machine(InputStream inputStream, T blankSym, T[] symbols) {
        this(1, blankSym, symbols);
        String[] rules = Utils.parseFile(inputStream);
        this.rules = new Rules(rules);
    }



    public int getCurrentState(){
        return currentState;
    }

    public void updateState(){
        Rules rule = rules.forCurrentState(this.currentState, tape.getCurrentSym());
        tape.setCurrentSym((T) rule.getNextSymbol());
        switch (rule.getNextDirection()){
            case 1 : tape.moveRight();
                break;
            case -1 : tape.moveLeft();
                break;
        }
        this.currentState = rule.getNextState();
    }

    public String printTape() {
        return tape.toString();
    }

    @Override
    public String toString() {
        return  "rules=\n" + rules +
                "currentState=" + currentState;
    }
}
