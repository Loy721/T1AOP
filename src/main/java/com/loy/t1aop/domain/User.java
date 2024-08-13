package com.loy.t1aop.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "users_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "users_seq", allocationSize = 5)
    @NotNull
    private Long id;
    @NotBlank
    @Size(min = 4, max = 64)
    @Column(name = "name", nullable = false)
    private String name;
    @Email
    @NaturalId
    @Column(name = "mail", nullable = false, unique = true)
    private String mail;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
        order.setUser(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setUser(null);
    }
}
