package com.loy.t1aop.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(generator = "orders_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "orders_seq", allocationSize = 5)
    private Long id;
    @Size(max = 2048)
    @Column(name = "description")
    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;
    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
