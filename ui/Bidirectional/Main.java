package Bidirectional;

public class Main {

    public static void main(String[] args) {
        TimHorton t1 = new TimHorton();
        TimHorton t2 = new TimHorton();
        Donut d1 = new Donut("AppleFritter","5",t1,t2);
        Donut d2 = new Donut("ChocolateDip","6",t1,t2);
        Donut d3 = new Donut("HoneyDip","2",t1,t2);
        Donut d4 = new Donut("VanillaDip","3",t1,t2);
        Donut d5 = new Donut("ChocolateGlazed","5",t1,t2);
        Donut d6 = new Donut("DoubleChocolate","1",t1,t2);
        t1.addDonut(d1);
        t1.addDonut(d2);
        t1.addDonut(d3);
        t1.addDonut(d4);
        t1.addDonut(d5);
        t1.addDonut(d6);
        t2.addDonut(d1);
        t2.addDonut(d2);
        t2.addDonut(d3);
        t2.addDonut(d4);
        t2.addDonut(d5);
        t2.addDonut(d6);
        Donut d = new Donut("","",t1,t2);
        d.runOrder();
    }
}
