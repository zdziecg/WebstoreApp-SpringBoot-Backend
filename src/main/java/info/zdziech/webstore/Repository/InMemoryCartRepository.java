package info.zdziech.webstore.Repository;

import info.zdziech.webstore.Model.Cart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
    public class InMemoryCartRepository implements CartRepository{
        private Map<Long, Cart> listOfCarts;
        public InMemoryCartRepository() {
            listOfCarts = new HashMap<Long, Cart>();
        }
        public Cart create(Cart cart) {
            if(listOfCarts.keySet().contains(cart.getCartId())) {
                throw new IllegalArgumentException(String.format("Nie można utworzyć koszyka. Koszyk o wskazanym  id (%) już istnieje.",cart.getCartId()));
            }
            listOfCarts.put(cart.getCartId(), cart);
            return cart;
        }
        public Cart read(Long cartId) {
            return listOfCarts.get(cartId);
        }
        public void update(Long cartId, Cart cart) {
            if(!listOfCarts.keySet().contains(cartId)) {
                throw new IllegalArgumentException(String.format("Nie można zaktualizować koszyka. Koszyk o wskazanym id (%) nie istnieje.",cartId));
            }
            listOfCarts.put(cartId, cart);
        }
        public void delete(Long cartId) {
            if(!listOfCarts.keySet().contains(cartId)) {
                throw new IllegalArgumentException(String.format("Nie można usunąć koszyka. Koszyk o wskazanym id (%) nie istnieje.",cartId));
            }
            listOfCarts.remove(cartId);
        }
    }

