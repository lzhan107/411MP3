/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;


/**
 *
 * @author Lei
 */
public class ProductMessage {
    private Product product;
    private String timeStamp;
    private String region;

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public String getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
}
