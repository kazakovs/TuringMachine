package net.kazakovs;

public class TuringMachine{

    public static void main(String[] args) {

        Machine<String> machine = new Machine<>(
                Utils.readFileInputStreamFromResources("net/kazakovs/rules.txt"),
                " ",
                new String[]{"1", "a", "1", "a", "a", "1"} );
        System.out.println(machine.printTape());
        while(machine.getCurrentState()!=0){
            machine.updateState();
            System.out.println(machine.printTape());
        }

    }

}