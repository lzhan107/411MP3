/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Lei
 */
public class ProductionAndConsumption {

    private ProductMessage[] productMessageBuffer = new ProductMessage[6];
    private static int occupiedCells = 0;
    private static ProductMessage readValue;
    private int numberOfActiveConsumerThread;
    private int numberOfActiveProducerThread;
    private DateFormat formatter = new SimpleDateFormat("MMM/dd/yyyy HH:mm:ss");

    public synchronized void produce(Product product) {
        //Test the whether productMessageBuffer is full, if the buffer is full, producers wait
        while (occupiedCells == productMessageBuffer.length) {//Buffer is full, Producer cannot produce products
            System.out.println("Product buffer is full. <"+ Thread.currentThread().getName() + "> waits...");
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        ProductMessage prodMsg = new ProductMessage();
        /**
         * Below if-else-if structure tests whether each productMessageBuffer cell is null,
         * if yes, producer provides one product with region, timestamp to one productMessageBuffer cell,
         * add the productMessageObject into ProductMessageQueue,
         * increases the number of occupied cells by one,
         * notify all the waiting threads.
         *
         * if not and occupied cells are four, producers wait.
         */
        if (productMessageBuffer[0] == null) {
                Date currentTimeStamp = new Date();
                prodMsg.setProduct(product);
                prodMsg.setRegion("EasternStates");
                prodMsg.setTimeStamp(formatter.format(currentTimeStamp));
                ProductProducer.getProductMessageQueue().add(prodMsg);
                productMessageBuffer[0] = prodMsg;
                System.out.println("<" + Thread.currentThread().getName() + "> has produced one product for <" 
                                   + productMessageBuffer[0].getRegion() + ">, Created at " 
                                   + productMessageBuffer[0].getTimeStamp() + " ( BufferCell  " + 0 + ")");
                System.out.println("ProductMessage produced by <" + Thread.currentThread().getName() 
                                   + "> has been added into ProductMessageQueue, queue size is " + ProductProducer.getProductMessageQueue().size());
                ++occupiedCells;
                notifyAll();
        } else if (productMessageBuffer[1] == null) {
                Date currentTimeStamp = new Date();
                prodMsg.setProduct(product);
                prodMsg.setRegion("CentralStates");
                prodMsg.setTimeStamp(formatter.format(currentTimeStamp));
                ProductProducer.getProductMessageQueue().add(prodMsg);
                productMessageBuffer[1] = prodMsg;
                System.out.println("<" + Thread.currentThread().getName() + "> has produced one product for <" 
                                   + productMessageBuffer[1].getRegion() + ">, Created at " 
                                   + productMessageBuffer[1].getTimeStamp() + " ( BufferCell  " + 1 + ")");
                System.out.println("ProductMessage produced by <" + Thread.currentThread().getName() 
                                   + "> has been added into ProductMessageQueue, queue size is " + ProductProducer.getProductMessageQueue().size());
                ++occupiedCells;
                notifyAll();
        } else if (productMessageBuffer[2] == null) {
                Date currentTimeStamp = new Date();
                prodMsg.setProduct(product);
                prodMsg.setRegion("MountainStates");
                prodMsg.setTimeStamp(formatter.format(currentTimeStamp));
                ProductProducer.getProductMessageQueue().add(prodMsg);
                productMessageBuffer[2] = prodMsg;
                System.out.println("<" + Thread.currentThread().getName() + "> has produced one product for <" 
                                   + productMessageBuffer[2].getRegion() + ">, Created at " 
                                   + productMessageBuffer[2].getTimeStamp() + " ( BufferCell  " + 2 + ")");
                System.out.println("ProductMessage produced by <" + Thread.currentThread().getName() 
                                   + "> has been added into ProductMessageQueue, queue size is " + ProductProducer.getProductMessageQueue().size());
                ++occupiedCells;
                notifyAll();
        } else if (productMessageBuffer[3] == null) {
                Date currentTimeStamp = new Date();
                prodMsg.setProduct(product);
                prodMsg.setRegion("PacificStates");
                prodMsg.setTimeStamp(formatter.format(currentTimeStamp));
                ProductProducer.getProductMessageQueue().add(prodMsg);
                productMessageBuffer[3] = prodMsg;
                System.out.println("<" + Thread.currentThread().getName() + "> has produced one product for <" 
                                   + productMessageBuffer[3].getRegion() + ">, Created at " 
                                   + productMessageBuffer[3].getTimeStamp() + " ( BufferCell  " + 3 + ")");
                System.out.println("ProductMessage produced by <" + Thread.currentThread().getName() 
                                   + "> has been added into ProductMessageQueue, queue size is " + ProductProducer.getProductMessageQueue().size());
                ++occupiedCells;
                notifyAll();
        }
        
        /**
         * Extra buffer cells in order to prevent the situation that 
         * producer threads terminates after all consumer threads are done executing 
         * while all the buffer cells are full. Producers are not able to terminate
         * in such condition. If all consumer threads are done executing while the buffer cells
         * are full, producers can still produce products into these extra cells.
         */
        if (this.numberOfActiveConsumerThread < 1 && productMessageBuffer[4] == null) {
            Date currentTimeStamp = new Date();
            prodMsg.setProduct(product);
            prodMsg.setRegion("PacificStates");
            prodMsg.setTimeStamp(formatter.format(currentTimeStamp));
            productMessageBuffer[4] = prodMsg;
            notifyAll();
        }
        if (this.numberOfActiveConsumerThread < 1 && productMessageBuffer[5] == null) {
            prodMsg.setProduct(product);
            prodMsg.setRegion("PacificStates");
            productMessageBuffer[5] = prodMsg;
            notifyAll();
        }
    }

    public synchronized ProductMessage comsume() {
        //Test whether occupied cells are empty, if empty, comsumers wait.
        while (occupiedCells == 0) {
            System.out.println("Product buffer is empty. <" + Thread.currentThread().getName() +"> waits...");
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        String threadName = Thread.currentThread().getName();
        System.out.println("<" + threadName + "> is goting to consume one product");

        /**
         * Below if-else-if structure test the thread name, then checks
         * whether product is available in the cell, if available, "consumes" the product
         * by assigning the cell value as "null", decreases the occupied number of cells,
         * notify all waiting threads. If not available, consumer threads wait.
         */
        if (Thread.currentThread().getName().equals("EasternStates")) {
            if (productMessageBuffer[0] != null) {
                readValue = productMessageBuffer[0];
                System.out.println("<" + Thread.currentThread().getName() + "> Consumer consumes one <" 
                                   + productMessageBuffer[0].getRegion() + "> product");
                productMessageBuffer[0] = null;
                --occupiedCells;
                System.out.println("Occupied Cells -> " + occupiedCells);
                notifyAll();
                return readValue;
            } else {
                System.out.println("There is no product avaiable for <" + Thread.currentThread().getName() 
                                   + "> ,please wait for your product...");
                try {
                    wait();
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
                return null;
            }
        } else if (Thread.currentThread().getName().equals("CentralStates")) {
            if (productMessageBuffer[1] != null) {
                readValue = productMessageBuffer[1];
                System.out.println("<" + Thread.currentThread().getName() + "> Consumer consumes one <" 
                                   + productMessageBuffer[1].getRegion() + "> product");
                productMessageBuffer[1] = null;
                --occupiedCells;
                System.out.println("Occupied Cells -> " + occupiedCells);
                notifyAll();
                return readValue;
            } else {
                System.out.println("There is no product avaiable for <" + Thread.currentThread().getName() 
                                   + ">, please wait for your product...");
                try {
                    wait();
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
                return null;
            }
        } else if (Thread.currentThread().getName().equals("MoutainStates")) {
            if (productMessageBuffer[2] != null) {
                readValue = productMessageBuffer[2];
                System.out.println("<" + Thread.currentThread().getName() + "> Consumer consumes one <" 
                                   + productMessageBuffer[2].getRegion() + "> product");
                productMessageBuffer[2] = null;
                --occupiedCells;
                System.out.println("Occupied Cells -> " + occupiedCells);
                notifyAll();
                return readValue;
            } else {
                System.out.println("There is no product avaiable for <" + Thread.currentThread().getName() 
                                   + ">, please wait for your product...");
                try {
                    wait();
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
                return null;
            }
        } else if (Thread.currentThread().getName().equals("PacificStates")) {
            if (productMessageBuffer[3] != null) {
                readValue = productMessageBuffer[3];
                System.out.println("<" + Thread.currentThread().getName() + "> Consumer consumes one <" 
                                   + productMessageBuffer[3].getRegion() + "> product");
                productMessageBuffer[3] = null;
                --occupiedCells;
                System.out.println("Occupied Cells -> " + occupiedCells);
                notifyAll();
                return readValue;
            } else {
                System.out.println("There is no product avaiable for <" + Thread.currentThread().getName() 
                                   + ">, please wait for your product...");
                try {
                    wait();
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
                return null;
            }
        } else {
            return null;
        }
    }
    public int getNumberOfActiveConsumerThread() {
        return numberOfActiveConsumerThread;
    }
    public void setNumberOfActiveConsumerThread(int numberOfActiveConsumerThread) {
        this.numberOfActiveConsumerThread = numberOfActiveConsumerThread;
    }
    public int getNumberOfActiveProducerThread() {
        return numberOfActiveProducerThread;
    }
    public void setNumberOfActiveProducerThread(int numberOfActiveProducerThread) {
        this.numberOfActiveProducerThread = numberOfActiveProducerThread;
    }
}
