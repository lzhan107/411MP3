/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package driver;

import domain.ProductConsumer;
import domain.ProductProducer;
import domain.ProductionAndConsumption;
import mp3utilities.MP3Utilities;

/**
 *
 * @author Lei
 */
public class MP3 {
/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Get all the data
        ProductProducer.setProductList(MP3Utilities.getProductInfo());
        ProductProducer.setEsternStateList(MP3Utilities.populateEasternStateList());        
        ProductProducer.setCentralStateList(MP3Utilities.populateCentralStateList());
        ProductProducer.setMountainStateList(MP3Utilities.populateMountainStateList());
        ProductProducer.setPacificStateList(MP3Utilities.populatePacificStateList());
        ProductProducer.setAllStateList(MP3Utilities.getAllStates());
        //Create object for product-consumer simulation
        ProductionAndConsumption drop = new ProductionAndConsumption();
        //Create two producers
        ProductProducer producer1 = new ProductProducer(drop);
        ProductProducer producer2 = new ProductProducer(drop);
        //Create four consumers
        ProductConsumer easternStates = new ProductConsumer(drop);
        ProductConsumer centralStates = new ProductConsumer(drop);
        ProductConsumer mountainStates = new ProductConsumer(drop);
        ProductConsumer pacificStates = new ProductConsumer(drop);
        //Create two thread groups
        ThreadGroup producerGroup = new ThreadGroup("ProducerGroup");
        ThreadGroup consumerGroup = new ThreadGroup("ConsumerGroup");
        //Start all the threads
        (new Thread(producerGroup,producer1,"Producer1")).start();
        (new Thread(producerGroup,producer2,"Producer2")).start();
        (new Thread(consumerGroup,easternStates,"EasternStates")).start();
        (new Thread(consumerGroup,centralStates,"CentralStates")).start();
        (new Thread(consumerGroup,mountainStates,"MountainStates")).start();
        (new Thread(consumerGroup,pacificStates,"PacificStates")).start();
    }
}
