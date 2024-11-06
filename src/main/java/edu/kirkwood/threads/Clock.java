package edu.kirkwood.threads;

public class Clock extends Thread {
    @Override
    public void run()  {
        while(true) {
            System.out.println("Tick");
            try {
                Thread.sleep(1000); // Pause for 1 second
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println("BOOM!");
    }
}
