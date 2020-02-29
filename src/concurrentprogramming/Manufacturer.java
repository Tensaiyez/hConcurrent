package concurrentprogramming;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Manufacturer implements Runnable {

    Semaphore sem;
    Bank selfBank;
    Bank partnerBank;

    public Manufacturer(Bank sBank, Bank pBank, Semaphore s) {

        this.selfBank = sBank;
        this.partnerBank = pBank;
    }

//    public Manufacturer() {
//
//    }

    public int randomNum() {
        Random r = new Random();
        return r.nextInt(20);
    }


    //private Manufacturer partner = new Manufacturer();

    private String name;

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }


    public Bank getBank() {
        return selfBank;
    }


    synchronized public void produce() throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + " is currently producing");

        this.wait(randomNum());

    }

    synchronized public void sell() throws InterruptedException {

        try {
            sem.acquire();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!selfBank.connectionStatus() && partnerBank.connectionStatus()) {
            selfBank.acquire();
            partnerBank.acquire();

            System.out.println(Thread.currentThread().getName() + " is currently selling");
        }

        this.wait(randomNum());

        partnerBank.release();
        selfBank.release();

        sem.release();

        produce();
    }

        @Override
        public void run() {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 5; i++) {

            try {
               sell();

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        }

    }

