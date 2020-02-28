package Bidirectional;

import java.util.*;

public class Donut {
    public ArrayList<TimHorton> tims;
    public String donutLeft;
    public String name;
    private static Scanner input;

    public Donut(String name, String donutLeft, TimHorton t1,TimHorton t2){
        this.name = name;
        this.donutLeft = donutLeft;
        tims = new ArrayList<>();
        tims.add(t1);
        tims.add(t2);
    }

    public void runOrder() {
        boolean keepGoing = true;
        String command = null;

        input = new Scanner(System.in);

        while(keepGoing) {
            displayMenu();
            command = input.next();

            if(command.equals("T1")){
                tims.get(0).runOrder();
            }
            else if (command.equals("T2")) {
                tims.get(1).runOrder();
            }
            else {
                System.out.println("We don't have this Tim Horton.");
            }
        }

        System.out.println("\nGoodbye!");
    }

    private void displayMenu() {
        System.out.println("\nChoose a Tim Horton you wanna go:");
        System.out.println("\tT1");
        System.out.println("\tT2");
    }

    public void notInThisStore(Donut d){
        System.out.println("Sorry, we don't have " + d.name + " now.");
        System.out.println("Try the other Tim Horton.");
        runOrder();
    }

    public void addTim(TimHorton t){
        if(!tims.contains(t)){
            tims.add(t);
            t.addDonut(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Donut donut = (Donut) o;
        return Objects.equals(name, donut.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
