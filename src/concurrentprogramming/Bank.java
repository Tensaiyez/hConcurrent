package concurrentprogramming;


import java.util.concurrent.Semaphore;
//import

public class Bank {
    int semAccess = 1;
    private Semaphore sem = new Semaphore(semAccess);

    private boolean bankConnection = false;

    String name;

    // individual bank id
    private int bankId;

    // individual manufacturer
    private Manufacturer m;


    public Bank(String manufacturer) {
        this.name = manufacturer;
    }


    public void setID(int id) {
        this.bankId = id;
    }

    public int getId() {
        return this.bankId;
    }

    public Semaphore getSem() {
        return this.sem;
    }

    synchronized boolean connectionStatus() {
        return this.bankConnection;
    }

    synchronized  void acquire() {
        bankConnection = true;
        System.out.println("Acquired " + this.name);
    }

    synchronized  void release() {
        bankConnection = false;
        System.out.println("Released from " + this.name);
    }




}



