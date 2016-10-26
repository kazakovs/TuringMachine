package net.kazakovs;

import java.util.Arrays;

/**
 * Created by reefer on 24.10.16.
 */
class Tape <T> {

    private int size;
    private T[] tape;
    private int currentPos;
    private T blankSym;

    private Tape(){

    }

    public Tape(int size, T blankSym, T[] initialWords){
        this.size = size;
        this.blankSym = blankSym;
        tape = (T[]) new Object[size];
        currentPos = 0;
        int j;
        for (int i = 0; i < size; i++) {
            j = i - size/2;
            if(j>=0 && j<initialWords.length){
                tape[i] = initialWords[j];
            } else {
                tape[i] = blankSym;
            }
        }

    }

    public T getCurrentSym(){
        return tape[normalizedPos()];
    }

    public void setCurrentSym(T sym){
        tape[normalizedPos()] = sym;
    }

    public void moveRight(){
        currentPos++;
        checkPosition();
    }

    public void moveLeft(){
        currentPos--;
        checkPosition();
    }

    private int normalizedPos(){
        return size/2 + currentPos;
    }

    private void checkPosition() {
        if(normalizedPos() < 0 || normalizedPos() >= size){
            throw new IllegalStateException("Tape is over.");
        }
    }

    @Override
    public String toString() {
        return "tape=" + Arrays.toString(Arrays.
                                            stream(tape).
                                            filter(t -> !t.equals(this.blankSym)).
                                            toArray(size -> (T[])new Object[size]));
    }
}
