package edu.kirkwood.threads;

public class PrinterController {
    public static void main(String[] args) throws InterruptedException {
        Printer printer1 = new Printer("Epson 1234");
        printer1.start();
        printer1.join();
        Printer printer2 = new Printer("HP X55");
        printer2.start();
        printer2.join();
        Printer printer3 = new Printer("Brother Z23");
        printer3.start();
        printer3.join();
    }
}
