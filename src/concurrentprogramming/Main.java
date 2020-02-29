package concurrentprogramming;

import java.util.concurrent.Semaphore;

public class Main {

    //public static final int numOfManu = 100;


    public static void main(String[] args) throws InterruptedException {
        final int roundLimit = 5;


        Bank[] bank = new Bank[roundLimit];

        Semaphore sem = new Semaphore(roundLimit);

        for (int i = 0; i < sem.availablePermits(); i++) {
            bank[i] = new Bank(" " + i);
        }

        for (int i = 0; i < m.length; i++) {
            Manufacturer[] m = new Manufacturer[roundLimit];
            Manufacturer selfBank = Manufacturer(bank[i],bank[m[(i+1) % bank.length]]), sem);

            //Manufacturer partnerBank = m[(i+1) % bank.length];

//            if (i == m.length -1) {
//                m[i] = new Manufacturer(selfBank, partnerBank, sem);
//            } else {
//                m[i] = new Manufacturer(partnerBank, selfBank, sem);
//            }

            for (Manufacturer manufacturer :
                    m) {
                Thread t = new Thread(manufacturer);
                t.start();

            }
        }
        
    }


}