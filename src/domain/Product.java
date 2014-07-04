/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Lei
 */
public class Product {
    private String productId;
    private String productName;
    private String productDescription;
    private double weight;
    private double cost;
    
    public Product(){
        
    }
    public Product(String prodId, String prodName, String prodDesc, double wei, double co){
        productId = prodId;
        productName = prodName;
        productDescription = prodDesc;
        weight = wei;
        cost = co;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductDescription() {
        return productDescription;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
}
