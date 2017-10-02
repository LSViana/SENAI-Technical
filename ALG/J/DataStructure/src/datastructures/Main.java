package datastructures;

import datastructures.business.Customer;
import datastructures.vector.Vector;

public class Main {

    public static void main(String[] args) {
        testVector();
    }

    private static void testVector() {
        Vector<Customer> customers = new Vector<>(2);
        Customer thayto = new Customer("Thayto");
        Customer lucas = new Customer("Lucas");
        Customer felipe = new Customer("Felipe");
        customers.addRange(new Customer[] { thayto, felipe });
        customers.printData();
        customers.add(lucas, 2);
        customers.printData();
    }
}