package com.example.demo.Service;

import com.example.demo.Model.Book;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();
    private Long nextId = 1L; // Sử dụng Long để quản lý ID tự tăng

    public List<Book> getAllBooks() {
        return books;
    }

    public void addBook(Book book) {
        book.setId(nextId++); // Tự động gán ID khi thêm mới
        books.add(book);
    }

    public Optional<Book> getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst(); // Trả về Optional để tránh lỗi Null
    }

    // Fix lại để nhận 2 tham số: ID và đối tượng dữ liệu mới
    public void updateBook(Long id, Book updatedBook) {
        getBookById(id).ifPresent(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
        });
    }

    public void deleteBook(Long id) {
        books.removeIf(book -> book.getId().equals(id)); // Xóa dựa trên Long ID
    }
}