/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;
import mp3utilities.MP3Utilities;

/**
 *
 * @author Lei
 */
public class ProductConsumer implements Runnable {
    private static List<ProductMessage> collectedProductList = new ArrayList<>();
    private ProductionAndConsumption drop;
    ProductMessage productMessage = new ProductMessage();

    public ProductConsumer(ProductionAndConsumption drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        //Set the number of current active consumer threads
        drop.setNumberOfActiveConsumerThread(Thread.currentThread().getThreadGroup().activeCount());
        for (int i =0; i < 10; i++ ){
            try {
                Thread.sleep(20);
                System.out.println("<" + Thread.currentThread().getName() + "> sleeps...");
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            productMessage = drop.comsume(); 
            if (null != productMessage) {
                //Add product feeded by producer into collectedProductList
                collectedProductList.add(productMessage);
                System.out.println("Product cosumed by <"+ Thread.currentThread().getName() 
                                   +"> has been added into consumer product list, list size is " + collectedProductList.size());
            }
        }
        //Decrease the number of active consumer threads when some threads are done executing
        drop.setNumberOfActiveConsumerThread(drop.getNumberOfActiveConsumerThread() - 1);
        //If current thread is the last active thread, write all the product list into file
        if (drop.getNumberOfActiveConsumerThread() == 0){
            System.out.println("Write info into file");
            MP3Utilities.writeProductListToFile();
        }
        System.out.println("<" + Thread.currentThread().getName() + "> done with consuming");
    }
    public static List<ProductMessage> getCollectedProductList() {
        return collectedProductList;
    }
    public static void setCollectedProductList(List<ProductMessage> collectedProductList) {
        ProductConsumer.collectedProductList = collectedProductList;
    }
}
