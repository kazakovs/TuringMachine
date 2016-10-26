package net.kazakovs;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by reefer on 24.10.16.
 */
class Rules<T> {

    private Map<Pair, Triple> rules;

    private int currentState;
    private T currentSymbol;

    private Rules(){
        this.rules = new HashMap<Pair, Triple>();
    }

    public Rules(String[] rules){
        this();
        for (String rule : rules) {
            parseRule(rule);
        }
    }

    private void parseRule(String rule) {
        String[] inRule = rule.split("<:>")[0].split("\\|");
        String[] outRule = rule.split("<:>")[1].split("\\|");

        rules.put(
                new Pair(
                        Integer.parseInt(inRule[0]),
                        (T) inRule[1]),
                new Triple(
                        Integer.parseInt(outRule[0]),
                        Integer.parseInt(outRule[1]),
                        (T) outRule[2]));

    }

    public Rules forCurrentState(int state, T sym){
        currentState = state;
        currentSymbol = sym;
        return this;
    }

    public int getNextState(){
        return rules.get(new Pair(currentState, currentSymbol)).getState();
    }

    public T getNextSymbol(){
        return rules.get(new Pair(currentState, currentSymbol)).getSymbol();
    }

    public int getNextDirection(){
        return rules.get(new Pair(currentState, currentSymbol)).getDirection();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Pair p : rules.keySet()) {
            sb.append(p + "\t=>\t" + rules.get(p) + "\n");
        }
        return sb.toString();
    }

    private class Pair{
        private int state;
        private T symbol;

        Pair(int state, T symbol) {
            this.state = state;
            this.symbol = symbol;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            return state == pair.state && (symbol != null ? symbol.equals(pair.symbol) : pair.symbol == null);

        }

        @Override
        public int hashCode() {
            int result = state;
            result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return state + "/" + symbol;
        }
    }

    private class Triple{
        private int state;
        private int direction;
        private T symbol;

        Triple(int state, int direction, T symbol) {
            this.state = state;
            this.direction = direction;
            this.symbol = symbol;
        }

        private int getState() {
            return state;
        }

        private int getDirection() {
            return direction;
        }

        private T getSymbol() {
            return symbol;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Triple triple = (Triple) o;

            if (state != triple.state) return false;
            return direction == triple.direction && (symbol != null ? symbol.equals(triple.symbol) : triple.symbol == null);

        }

        @Override
        public int hashCode() {
            int result = state;
            result = 31 * result + direction;
            result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return state + "/" + direction + "/" + symbol;
        }
    }

}
