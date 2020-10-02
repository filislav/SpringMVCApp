package ru.filislav.spring.SpringMVCApp.dao;

import org.springframework.stereotype.Component;
import ru.filislav.spring.SpringMVCApp.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private static int PEOPLE_COUNT=0;
    private List<Person> people;
    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT,"Slava"));
        people.add(new Person(++PEOPLE_COUNT,"Sergey"));
        people.add(new Person(++PEOPLE_COUNT,"Kolya"));
    }
    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        return people.stream().filter(e->e.getId()==id).findAny().orElse(null);

    }
    public void save(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

}
