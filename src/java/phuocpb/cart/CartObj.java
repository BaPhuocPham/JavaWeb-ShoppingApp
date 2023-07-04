/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phuocpb.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import phuocpb.tbl_Product.Tbl_Product_DTO;

/**
 *
 * @author BaPhuoc
 */
public class CartObj implements Serializable{
    private Map<Integer, Tbl_Product_DTO> items;

    public Map<Integer, Tbl_Product_DTO> getItems() {
        return items;
    }
    
    public boolean addItemToCart(Integer id, Tbl_Product_DTO item) {
        boolean result = false;
        //1. check data validation
        if (id == null) {
            return result;
        }
        if ( item.getQuantity() <= 0) {
            return result;
        }
        //2. check existed items
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //3. check existed item
        if (this.items.containsKey(id)) {
            int currentQuantity = items.get(id).getQuantity();
            
            int quantity = item.getQuantity() + currentQuantity;
            item.setQuantity(quantity);
        }//end item has existed
        //4. update cart items
        this.items.put(id, item);
        result = true;
        
        return result;
    }
    public boolean removeItemFromCart(Integer id, int quantity) {
        boolean result = false;
        
        //1. check data validation
        if (id == null) {
            return result;
        }
        if (quantity <= 0) {
            return result;
        }
        //2. check existed items
        if (this.items == null) {
            return result;
        }
        //3. check existed item
        if (!this.items.containsKey(id)) {
            return result;
        }
        //4. remove items
        Tbl_Product_DTO item = this.items.get(id);
        int currentQuantity = item.getQuantity();
        
        if (currentQuantity >= quantity) {
            quantity = currentQuantity - quantity;
            item.setQuantity(quantity);
        } else {
            return result;
        }
        if (quantity == 0) {
            this.items.remove(id);
            System.out.println(this.items);
            System.out.println(this.items.isEmpty());
            //sometime the collection not is null but 
            //it is empty (when print out is not good) so if empty -> null
            if (this.items.isEmpty()) {
                this.items = null;
                System.out.println(this.items);
            }
        } else {
            this.items.put(id, item);
        }
        result = true;
        
        return result;
    }
    public float getTotal() {
        float total = 0;
        if (this.items != null) {
            for (Integer key : this.items.keySet()) {
                Tbl_Product_DTO item = this.items.get(key);
                total = total + item.getPrice();
            }
        }
        return total;
    }
}
