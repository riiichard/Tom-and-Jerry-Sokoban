package Bidirectional;

import java.util.*;


public class TimHorton {
    public ArrayList<Donut> donuts;
    private static Scanner input;
    Map<Donut,String> sellingDonuts = new HashMap<>();

    public TimHorton(){
        donuts = new ArrayList<>();
    }

    public void runOrder() {
        boolean keepGoing = true;
        String command = null;

        init();

        while(keepGoing) {
            displayMenu();
            command = input.next();

            if(ifValid(command)){
                doOrder(theDonut(command));
                System.out.println("Thank you! Have a good day.");
            }
            else
                System.out.println("Selection not valid...");
        }

        System.out.println("\nGoodbye!");
    }

    private Boolean ifValid(String command){
        for(int i = 0; i < 6; i++){
            if(command.equals(donuts.get(i).name)){
            return true;
            }
        }
        return false;
    }

    private Donut theDonut(String s){
        for(int i=0; i<6; i++){
            if(s.equals(donuts.get(i).name)){
                return donuts.get(i);
            }
        }
        return null;
    }

    private void doOrder(Donut d){
        String oldString = sellingDonuts.get(d);
        int oldNumber = Integer.parseInt(oldString);
        if(oldNumber>0){sellingDonuts.replace(d, Integer.toString(oldNumber - 1));}
        else{
            d.notInThisStore(d);
            removeDonut(d);
        }
    }

    private void init(){
        TimHorton t1 = new TimHorton();
        TimHorton t2 = new TimHorton();
        addSellingDonut(new Donut("AppleFritter","5",t1,t2));
        addSellingDonut(new Donut("ChocolateDip","6",t1,t2));
        addSellingDonut(new Donut("HoneyDip","2",t1,t2));
        addSellingDonut(new Donut("VanillaDip","3",t1,t2));
        addSellingDonut(new Donut("ChocolateGlazed","5",t1,t2));
        addSellingDonut(new Donut("DoubleChocolate","1",t1,t2));

        input = new Scanner(System.in);
    }

    private void addSellingDonut(Donut d){
        sellingDonuts.put(d ,d.donutLeft);
    }

    public void addDonut(Donut d){
        if(!donuts.contains(d)){
            donuts.add(d);
            d.addTim(this);
        }
    }

    public void removeDonut(Donut d){
            donuts.remove(d);
            d.tims.remove(this);
    }

    private void displayMenu() {
        System.out.println("\nHi! What would you like for today?");
        System.out.println("\nChoose from following:");
        System.out.println("\tAppleFritter");
        System.out.println("\tChocolateDip");
        System.out.println("\tHoneyDip");
        System.out.println("\tVanillaDip");
        System.out.println("\tChocolateGlazed");
        System.out.println("\tDoubleChocolate");
    }
}
