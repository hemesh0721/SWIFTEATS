package com.hemesh.Model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Cart {
	private static Map<Integer,CartItem> items;
	
	public Cart() {
		this.items=new HashMap<>();
	}
	
	public static HashMap<Integer,CartItem> getItems(){
		HashMap<Integer,CartItem> set = new HashMap<>();
		for(Map.Entry<Integer,CartItem> entry:items.entrySet()) {
			set.put(entry.getKey(),entry.getValue());
		}
		return set;
	}
	
	public void addCartItem(CartItem cartItem) {
		if(items.containsKey(cartItem.getId())) {
			CartItem existingItem = items.get(cartItem.getId());
			existingItem.setQuantity(existingItem.getQuantity()+1);
		}
		else {
		items.put(cartItem.getId(), cartItem);
		}
	}
	public void updateCartItem(int itemId,int quantity) {
		if(items.containsKey(itemId)) {
			if(quantity<=1) {
				items.remove(itemId);
			}
			else {
				items.get(itemId).setQuantity(quantity-1);
			}
		}
		
	
	}
	public void deleteCartItem(int itemId) {
		items.remove(itemId);
	}
}
