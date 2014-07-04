/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
/**
 *
 * @author Lei
 */
public class ProductProducer implements Runnable {

    private static List<Product> productList;
    private static Queue<ProductMessage> productMessageQueue = new LinkedList<>();
    private ProductionAndConsumption drop;
    private static List<String> esternStateList = new ArrayList<>();
    private static List<String> centralStateList = new ArrayList<>();
    private static List<String> mountainStateList = new ArrayList<>();
    private static List<String> pacificStateList = new ArrayList<>();
    private static List<String> allStateList = new ArrayList<>();
    private PrintStream simulation = null;
    private long startingTime;

    public ProductProducer(ProductionAndConsumption drop) {
        this.drop = drop;
    }
    @Override
    public void run() {
        try {
            Random random = new Random();
            startingTime = System.currentTimeMillis();
            Product product;
            //Redirect the standard console output to a file
            simulation = new PrintStream("./output/mp3out.txt");
            System.setOut(simulation);
            //Set current number of active producer threads
            drop.setNumberOfActiveProducerThread(Thread.currentThread().getThreadGroup().activeCount());
            //Functionality of do-while is to test the number of active consumer threads,
            //if the number of active consumer threads decreases to 0, producer threads done executing
            do {
                try {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("<" + Thread.currentThread().getName() + "> is going to produce one product");
                    product = productList.get(random.nextInt(28));
                    Thread.sleep(10);
                    System.out.println("<" + threadName + "> sleeps...");
                    drop.produce(product);
                    System.out.println("Number of current active consumer threads is " + drop.getNumberOfActiveConsumerThread());
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            } while (drop.getNumberOfActiveConsumerThread() > 0);
            //Decrease the number of current active producer threads
            drop.setNumberOfActiveProducerThread(drop.getNumberOfActiveProducerThread() - 1);
            System.out.println("Number of current active producer threads is " + drop.getNumberOfActiveProducerThread());
            System.out.println("<" + Thread.currentThread().getName() + "> has done with producing");
            System.out.println("Total elapsed time is " + (System.currentTimeMillis() - startingTime) + " milliseconds");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } finally{
            if (simulation != null){
                simulation.close();
            }
        }
    }
    public static List<Product> getProductList() {
        return productList;
    }
    public static void setProductList(List<Product> productList) {
        ProductProducer.productList = productList;
    }
    public static List<String> getEsternStateList() {
        return esternStateList;
    }
    public static void setEsternStateList(List<String> esternStateList) {
        ProductProducer.esternStateList = esternStateList;
    }
    public static List<String> getCentralStateList() {
        return centralStateList;
    }
    public static void setCentralStateList(List<String> centralStateList) {
        ProductProducer.centralStateList = centralStateList;
    }
    public static List<String> getMountainStateList() {
        return mountainStateList;
    }
    public static void setMountainStateList(List<String> mountainStateList) {
        ProductProducer.mountainStateList = mountainStateList;
    }
    public static List<String> getPacificStateList() {
        return pacificStateList;
    }
    public static void setPacificStateList(List<String> pacificStateList) {
        ProductProducer.pacificStateList = pacificStateList;
    }
    public static List<String> getAllStateList() {
        return allStateList;
    }
    public static void setAllStateList(List<String> allStateList) {
        ProductProducer.allStateList = allStateList;
    }
    public static Queue<ProductMessage> getProductMessageQueue() {
        return productMessageQueue;
    }
    public static void setProductMessageQueue(Queue<ProductMessage> productMessageQueue) {
        ProductProducer.productMessageQueue = productMessageQueue;
    }
}
