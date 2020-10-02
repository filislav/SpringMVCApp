package ru.filislav.spring.SpringMVCApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.filislav.spring.SpringMVCApp.dao.PersonDao;
import ru.filislav.spring.SpringMVCApp.models.Person;

@Controller
@RequestMapping("/people")
public class PoepleController {

    private final PersonDao personDao;

    @Autowired
    public PoepleController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people",personDao.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person",personDao.show(id));
        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person")Person person){
        personDao.save(person);
        return "redirect:/people";

    }
}
