package com.library.controller;

import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "index2";
    }

    @PostMapping("/add")
    public String addBook(@RequestParam String title,
                          @RequestParam String author) {

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setStatus("Available");

        bookRepository.save(book);

        return "redirect:/";
    }

    @GetMapping("/issue/{id}")
    public String issueBook(@PathVariable int id) {

        Book book = bookRepository.findById(id).orElse(null);

        if (book != null) {
            book.setStatus("Issued");
            bookRepository.save(book);
        }

        return "redirect:/";
    }

    @GetMapping("/return/{id}")
    public String returnBook(@PathVariable int id) {

        Book book = bookRepository.findById(id).orElse(null);

        if (book != null) {
            book.setStatus("Available");
            bookRepository.save(book);
        }

        return "redirect:/";
    }
}