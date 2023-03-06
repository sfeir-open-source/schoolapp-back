package com.sfeiropensource.schoolapp.controller;

import com.sfeiropensource.schoolapp.model.School;
import com.sfeiropensource.schoolapp.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("schools")
public class SchoolController {

    @Autowired
    private SchoolRepository repo;

    @PostMapping("/add")
    public String add(@RequestBody School school){
        repo.save(school);
        return "Added Successfully";
    }

    @GetMapping("/findAll")
    public List<School> getBooks() {
        return repo.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id){
        repo.deleteById(id);
        return "Deleted Successfully";
    }
}
