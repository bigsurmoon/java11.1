package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Book book1 = new Book(1, "1984", 500, "George Orwell");
    private Book book2 = new Book(2, "Atlas Shrugged", 1000, "Ayn Rand");
    private Book book3 = new Book(3, "Crime and Punishment", 700, "Fyodor Dostoevsky");
    private Smartphone smartphone1 = new Smartphone(4, "Galaxy s22", 164000, "Samsung");
    private Smartphone smartphone2 = new Smartphone(5, "Iphone 13", 95500, "Apple");
    private Smartphone smartphone3 = new Smartphone(6, "Nokia 3310", 5000, "Nokia");

    @BeforeEach
    public void setUp() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);
    }

    @Test
    public void shouldSearch1984() {
        Product[] expected = manager.searchBy("1984");
        Product[] actual = new Product[]{book1};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAynRand() {
        Product[] expected = manager.searchBy("Ayn Rand");
        Product[] actual = new Product[]{book2};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearch3310() {
        Product[] expected = manager.searchBy("Nokia 3310");
        Product[] actual = new Product[]{smartphone3};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchSamsung() {
        Product[] expected = manager.searchBy("Samsung");
        Product[] actual = new Product[]{smartphone1};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchVoid() {
        Product[] expected = manager.searchBy("");
        Product[] actual = new Product[]{book1, book2, book3, smartphone1, smartphone2, smartphone3};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchWithoutMatch() {
        Product[] actual = manager.searchBy("LG");
        Product[] expected = new Product[] {};
        assertArrayEquals(expected, actual);
    }
}