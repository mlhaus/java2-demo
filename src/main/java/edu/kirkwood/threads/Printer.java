package edu.kirkwood.threads;

public class Printer extends Thread {

    public Printer(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Starting '" + this.getName() + "' thread.");
        System.out.println("Ending '" + this.getName() + "' thread.");
    }
}
