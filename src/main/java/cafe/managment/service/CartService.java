package cafe.managment.service;

import cafe.managment.model.Cart;

import java.util.List;

public interface CartService {

    Cart addToCart(Long productId);
    List<Cart> activedItem();

    Cart incriment(Long id);

    Cart decriment(Long id);

    Cart removeFromCart(Long id);

    Double GrandTotel();

    List<Cart> deleteFromCart();


}





