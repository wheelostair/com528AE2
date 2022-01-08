/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.transaction.Transactional;
import org.solent.com504.oodd.cart.dao.impl.ShoppingItemCatalogRepository;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cgallen
 */
@Service
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    private ShoppingItemCatalogRepository shoppingItemCatalogRepository;
    // note ConcurrentHashMap instead of HashMap if map can be altered while being read
    //private Map<String, ShoppingItem> itemMap = new ConcurrentHashMap<String, ShoppingItem>();

//    private List<ShoppingItem> itemlist;
//    private List<ShoppingItem> itemlist = Arrays.asList(new ShoppingItem("house", 20000.00),
//            new ShoppingItem("hen", 5.00),
//            new ShoppingItem("car", 5000.00),
//            new ShoppingItem("pet alligator", 65.00)
//    );
    public ShoppingServiceImpl() {

    }

    // initialised the hashmap
    // for (ShoppingItem item : itemlist) {
    //     itemMap.put(item.getName(), item);
    // }
    // }
    @Override
    public List<ShoppingItem> getAvailableItems() {
        return shoppingItemCatalogRepository.findAll();
    }

    @Override
    public boolean purchaseItems(ShoppingCart shoppingCart) {
        System.out.println("purchased items");
        for (ShoppingItem shoppingItem : shoppingCart.getShoppingCartItems()) {
            System.out.println(shoppingItem);
        }

        return true;
    }

    @Override
    public ShoppingItem getNewItemByName(String name) {

        ShoppingItem item = null;
        List<ShoppingItem> items = shoppingItemCatalogRepository.getItemByName(name);

        if (!items.isEmpty()) {
            item = items.get(0);
        }

//ShoppingItem templateItem = itemMap.get(name);
//        
//        if(templateItem==null) return null;
//        
//        ShoppingItem item = new ShoppingItem();
//        item.setName(name);
//        item.setPrice(templateItem.getPrice());
//        item.setQuantity(0);
//        item.setUuid(UUID.randomUUID().toString());
        return item;
    }

    @Transactional
    public ShoppingItem addNewItemByNamePrice(String name, Double price) {
        ShoppingItem item = new ShoppingItem();
        item.setName(name);
        item.setPrice(price);
        shoppingItemCatalogRepository.save(item);

        // List<ShoppingItem> items =shoppingItemCatalogRepository.addNewItemByNamePrice(name,price);
        return item;
    }

    @Override
    public ShoppingItem addNewItem(ShoppingItem shoppingItem) {

        ShoppingItem item = null;
        List<ShoppingItem> items = shoppingItemCatalogRepository.getItemByName(shoppingItem.getName());

        if (!items.isEmpty()) {
            throw new IllegalArgumentException("Item already in Database" + shoppingItem);
        }
        item = shoppingItemCatalogRepository.save(shoppingItem);
        return item;
    }

    @Override
    public List<ShoppingItem> getActivatedItems() {

        List<ShoppingItem> items = shoppingItemCatalogRepository.getActivatedItems();

        return items;
    }

    @Override
    public void deactivateItems(String uuid) {
        
        shoppingItemCatalogRepository.deactivateItems(uuid);
        
        
    }
    @Override
    public void removeStock(ShoppingItem shoppingItem,Integer amount){
        
        shoppingItemCatalogRepository.removeStock(shoppingItem.getName());
    }
};
