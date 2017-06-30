package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.repository.ReadingListRepository;
import com.sun.javafx.sg.prism.NGShape;
import  org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
/**
 * Created by Administrator on 2017/6/30.
 */
public class readingListController {
    private ReadingListRepository readingListRepository;

    @Autowired
    public void ReadingListController(ReadingListRepository readingListRepository){
        this.readingListRepository = readingListRepository;
    }

    @RequestMapping(value="/{reader}", method = RequestMethod.GET)
    public String readersBooks(@PathVariable("reader") String reader, Model model){
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null){
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader, Book book){
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/{reader}";
    }
}
