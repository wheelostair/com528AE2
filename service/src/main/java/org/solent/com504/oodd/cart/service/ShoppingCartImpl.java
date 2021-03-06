/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author cgallen
 */
public class ShoppingCartImpl implements ShoppingCart {

    final static Logger LOG = LogManager.getLogger(ShoppingCartImpl.class);

    private HashMap<String, ShoppingItem> itemMap = new HashMap<String, ShoppingItem>();

    @Override
    public List<ShoppingItem> getShoppingCartItems() {
        List<ShoppingItem> itemlist = new ArrayList();
        for (String itemUUID : itemMap.keySet()) {
            ShoppingItem shoppingCartItem = itemMap.get(itemUUID);
            itemlist.add(shoppingCartItem);
        }
        return itemlist;
    }

    @Override
    public boolean addItemToCart(ShoppingItem shoppingItem) {
        // itemMap.put(shoppingItem.getUuid(), shoppingItem);

        // ANSWER
        boolean itemExists = false;
        for (String itemUUID : itemMap.keySet()) {
            ShoppingItem shoppingCartItem = itemMap.get(itemUUID);
            if (shoppingCartItem.getName().equals(shoppingItem.getName())) {
                Integer q = shoppingCartItem.getQuantity();
                Integer s = shoppingCartItem.getStock();
                LOG.debug("q: " + q + "s: " + s);
                if (q + 1 > s) {
                    return false;
                }
                shoppingCartItem.setQuantity(q + 1);
                itemExists = true;
                break;
            }
        }
        if (!itemExists) {
            shoppingItem.setQuantity(1);
            itemMap.put(shoppingItem.getUuid(), shoppingItem);
        }
        return true;
    }

    @Override
    public void removeItemFromCart(String itemUuid) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // ANSWER
        itemMap.remove(itemUuid);
    }

    @Override
    public double getTotal() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // ANSWER
        double total = 0;

        for (String itemUUID : itemMap.keySet()) {
            ShoppingItem shoppingCartItem = itemMap.get(itemUUID);
            total = total + shoppingCartItem.getPrice() * shoppingCartItem.getQuantity();
        }

        return total;

    }

    public void removeStock(String itemUuid) {
        for (String itemUUID : itemMap.keySet()) {
            ShoppingItem shoppingCartItem = itemMap.get(itemUUID);

            shoppingCartItem.setStock(shoppingCartItem.getStock()-shoppingCartItem.getQuantity());

        }
    }

}
