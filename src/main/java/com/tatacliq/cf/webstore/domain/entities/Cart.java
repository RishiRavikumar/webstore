package com.tatacliq.cf.webstore.domain.entities;

import com.tatacliq.cf.webstore.domain.interfaces.ICart;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Cart implements ICart {
    private List<CartProduct> products = new ArrayList<>();
    private List<Item> items = new ArrayList<>();

    @Override
    public boolean addProduct(Product product, long quantity) {
        if(getCartProduct(product.getProductId()) != null) return false;
        products.add(new CartProduct(product, quantity));
        return true;
    }

    @Override
    public boolean setProductQuantity(Product product, long quantity) {
        products
                .stream()
                .filter((cartProduct -> product.equals(cartProduct.getProduct())))
                .forEach(cartProduct -> cartProduct.setQuantity(quantity));
        return true;
    }

    @Override
    public boolean removeProduct(long productId) {
        List<CartProduct> newProducts = products
                .stream()
                .filter(cartProduct -> productId != cartProduct.getProduct().getProductId())
                .collect(Collectors.toList());
        if(newProducts.size() == products.size()) {
            return false;
        } else {
            products = newProducts;
            return true;
        }
    }

    @Override
    public Product getCartProduct(long productId) {
        CartProduct product1 = products
                .stream()
                .filter(cartProduct -> productId == cartProduct.getProduct().getProductId())
                .findFirst()
                .orElse(null);
        return product1 == null ? null : product1.getProduct();
    }

    @Override
    public boolean addItem(Item item) {
        Item item1 = items
                .stream()
                .filter(item2 -> item.getItemId() == item2.getItemId())
                .findAny()
                .orElse(null);
        if(item1 == null){
            items.add(item);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeItem(long itemId) {
        List<Item> newItems = items
                .stream()
                .filter(item1 -> itemId != item1.getItemId())
                .collect(Collectors.toList());

        if(newItems.size() == items.size()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Item getCartItem(long itemId) {
        return items
                .stream()
                .filter(item1 -> itemId == item1.getItemId())
                .findAny()
                .orElse(null);
    }

    @Override
    public long getItemCount() {
        return items.size();
    }

    @Override
    public long getItemTotal() {
        double total = 0;

        for (Item item: items) {
            CartProduct cartProduct = products.stream()
                    .filter(cProduct -> item.getProductId() == cProduct.getProduct().getProductId())
                    .findAny()
                    .orElse(null);
            total += cartProduct == null ? 0 : item.getPrice() * cartProduct.getQuantity();
        }

        return (long) total;
    }

    @Override
    public List<Item> getProductIds() {
        return items;
    }
}
