package com.anky.xoriant.book.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Table(name = "Books")
@Entity
@SequenceGenerator(name = "seq", initialValue = 6, allocationSize = 100)
public class Book {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Id private int id;
    @NotNull private String name;
    @NotNull private double amount;

}
