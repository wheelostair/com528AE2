/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.model.service;

import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import java.util.List;

/**
 *
 * @author cgallen
 */
public interface ShoppingService {
    
        public List<ShoppingItem> getAvailableItems();
        
        public boolean purchaseItems(ShoppingCart shoppingCart);
        
        public ShoppingItem getNewItemByName(String uuid);
        
        public ShoppingItem addNewItem(ShoppingItem shoppingItem);
        
        public List<ShoppingItem> getActivatedItems();
        
        public void deactivateItems(String uuid);
        
        public void removeStock(ShoppingItem shoppingItem, Integer amount);
        // add parameter for quanitity of stock to remove 

}
