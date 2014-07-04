/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3utilities;

import domain.Product;
import domain.ProductConsumer;
import domain.ProductMessage;
import domain.ProductProducer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Lei
 */
public class MP3Utilities {
    private static List<String> esternStateList = new ArrayList<>();
    private static List<String> centralStateList = new ArrayList<>();
    private static List<String> mountainStateList = new ArrayList<>();
    private static List<String> pacificStateList = new ArrayList<>();
    private static List<String> listOfContinentalStates = new ArrayList<>();
    private static List<Product> productList = new ArrayList<>();
    private static List<ProductMessage> collectedProductList = new ArrayList<>();
    private static BufferedReader input = null;
    private static PrintWriter output = null;
    private static PrintStream simulation = null;

    //Read eastern states
    public static List populateEasternStateList() {
        try {
            input = new BufferedReader(new FileReader("./data/Eastern_State_List.txt"));
            String line;
            while (null != (line = input.readLine())) {
                esternStateList.add(line);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return esternStateList;
    }

    //Read central states
    public static List populateCentralStateList() {
        try {
            input = new BufferedReader(new FileReader("./data/Central_State_List.txt"));
            String line;
            while (null != (line = input.readLine())) {
                centralStateList.add(line);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return centralStateList;
    }

    //Read mountain states
    public static List populateMountainStateList() {
        try {
            input = new BufferedReader(new FileReader("./data/Mountain_State_List.txt"));
            String line;
            while (null != (line = input.readLine())) {
                mountainStateList.add(line);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return mountainStateList;
    }

    //Read pacific states
    public static List populatePacificStateList() {
        try {
            input = new BufferedReader(new FileReader("./data/Pacific_State_List.txt"));
            String line;
            while (null != (line = input.readLine())) {
                pacificStateList.add(line);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return pacificStateList;
    }

    //Get all states
    public static List getAllStates() {
        try {
            input = new BufferedReader(new FileReader("./data/State_List.txt"));
            String line;
            while (null != (line = input.readLine())) {
                listOfContinentalStates.add(line);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return listOfContinentalStates;
    }

    //Read all product info
    public static List getProductInfo() {
        try {
            input = new BufferedReader(new FileReader("./data/Product.txt"));
            String line;
            while (null != (line = input.readLine())) {
                String[] eachProduct = line.split(",");
                double weight = Double.parseDouble(eachProduct[3]);
                double cost = Double.parseDouble(eachProduct[4]);

                Product p = new Product();
                p.setProductId(eachProduct[0]);
                p.setProductName(eachProduct[1]);
                p.setProductDescription(eachProduct[2]);
                p.setWeight(weight);
                p.setCost(cost);
                //Add each product into the product list
                productList.add(p);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return productList;
    }

    public static Queue<ProductMessage> getProductMessageQueue() {
        return ProductProducer.getProductMessageQueue();
    }

    public static List<ProductMessage> getCollectedProductList() {
        return ProductConsumer.getCollectedProductList();
    }

    public static void writeProductListToFile() {
        try {
            output = new PrintWriter(new FileWriter("./output/collectedproductlist.txt"));
            collectedProductList = ProductConsumer.getCollectedProductList();
            output.write("ProductId\tProductName\tRegion\tWeight(ton)\tCost(million)\tProductDescription" + "\r\n");
            output.write("------------------------------------------------------------------------------------"
                       + "------------------------------------------------------------------------------------\r\n");
            for (int i = 0; i < collectedProductList.size(); i++) {
                String productId = collectedProductList.get(i).getProduct().getProductId();
                String productName = collectedProductList.get(i).getProduct().getProductName();
                String productDesc = collectedProductList.get(i).getProduct().getProductDescription();
                String weight = String.valueOf(collectedProductList.get(i).getProduct().getWeight());
                String cost = String.valueOf(collectedProductList.get(i).getProduct().getCost());
                String region = collectedProductList.get(i).getRegion();
                output.write(productId + "\t" + productName + "\t\t" + region + "\t" + weight + "\t" + cost + "\t" + productDesc + "\r\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }
}
