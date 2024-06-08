package cafe.managment.controller;

import cafe.managment.model.Cart;
import cafe.managment.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/addToCart/{productId}")
    public ResponseEntity<Cart> addItemToCart(@PathVariable Long productId){
        Cart cart=cartService.addToCart(productId);
        return ResponseEntity.ok(cart);
    }
    @GetMapping("/showCart")
    public ResponseEntity<List<Cart>> showCarts(){
        List<Cart> cart=cartService.activedItem();
        if(cart==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }
    @PutMapping("/incrimentQuantity/{id}")
    public ResponseEntity<Cart> incrimentQunatity(@PathVariable Long id){
        Cart cart=cartService.incriment(id);
        if(cart==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }
    @PutMapping("/decrimentQuantity/{id}")
    public ResponseEntity<Cart> decrimentQunatity(@PathVariable Long id){
        Cart cart=cartService.decriment(id);
        if(cart==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }
    @PutMapping("/removeFromCrt/{id}")
    public ResponseEntity<Cart> remove(@PathVariable Long id){
        Cart cart=cartService.removeFromCart(id);
        if(cart==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }
    @GetMapping("/cartTotel")
    public ResponseEntity<Double>  grandTotel(){
        Double totel=cartService.GrandTotel();
        return ResponseEntity.ok(totel);
    }
    @GetMapping("/deleteCart")
    public ResponseEntity<List<Cart>> deleteAll(){
        List<Cart> carts=cartService.deleteFromCart();
        if(carts==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carts);
    }

}

