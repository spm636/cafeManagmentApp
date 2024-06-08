package cafe.managment.serviceImpl;

import cafe.managment.model.Cart;
import cafe.managment.model.Product;
import cafe.managment.repository.CartRepository;
import cafe.managment.repository.ProductRepository;
import cafe.managment.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public Cart addToCart(Long productId) {
        Cart carts=cartRepository.findProductFromCart(productId);
        if(carts!=null){
            int quantity=carts.getQuanity()+1;
            carts.setQuanity(quantity);

            carts.setTotel(quantity*carts.getProduct().getSalePrice());

            return cartRepository.save(carts);
        }
        else {
            Cart cart = new Cart();
            Product product = productRepository.findById(productId).orElse(null);
            cart.setQuanity(1);
            cart.setProduct(product);
            if(product==null){
                cart.setTotel(null);
            }
            else {
                cart.setTotel(product.getSalePrice());
            }
            cart.setActive(true);
            return cartRepository.save(cart);

        }
    }

    @Override
    public List<Cart> activedItem() {
        return cartRepository.activatedItem();
    }

    public CartServiceImpl() {
        super();
    }

    @Override
    public Cart incriment(Long id) {
        Cart cart=cartRepository.findById(id).orElse(null);
        int quantity=cart.getQuanity() +1;
        double price=cart.getProduct().getSalePrice();
        cart.setQuanity(quantity);
        cart.setTotel(price*quantity);
        return cartRepository.save(cart);
    }

    @Override
    public Cart decriment(Long id) {
        Cart cart=cartRepository.findById(id).orElse(null);
        if(cart.getQuanity()==1){
            cart.setQuanity(cart.getQuanity());
        }
        else {
            int quantity=cart.getQuanity() - 1;
            double price=cart.getProduct().getSalePrice();
            cart.setQuanity(quantity);
            cart.setTotel(price*quantity);
        }
        return cartRepository.save(cart);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public Cart removeFromCart(Long id) {
        Cart cart=cartRepository.findById(id).orElse(null);
        cart.setActive(false);
        return cartRepository.save(cart);
    }

    @Override
    public Double GrandTotel() {
        return cartRepository.grandTotel();
    }

    @Override
    public List<Cart>  deleteFromCart() {
        List<Cart> carts=cartRepository.activatedItem();
        for(Cart c:carts){
            c.setActive(false);
            cartRepository.save(c);
        }
        return carts;
    }
}
