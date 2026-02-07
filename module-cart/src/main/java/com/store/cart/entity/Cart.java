package com.store.cart.entity;

import com.store.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
    @SequenceGenerator(name = "cart_seq", sequenceName = "cart_seq", allocationSize = 50)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long buyerId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    public Cart(Long buyerId) {
        this.buyerId = buyerId;
    }

    public void addItem(Long productId, String productName, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProductId().equals(productId)) {
                item.changeQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        cartItems.add(new CartItem(this, productId, productName, quantity));
    }

    public void removeItem(Long productId) {
        cartItems.removeIf(item -> item.getProductId().equals(productId));
    }

    public void clear() {
        cartItems.clear();
    }
}
