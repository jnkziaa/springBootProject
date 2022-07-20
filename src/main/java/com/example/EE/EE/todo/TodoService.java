package com.example.EE.EE.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();



    static {
        todos.add(new Todo(1, "in28Minutes", "Learn AWS", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(2, "in28Minutes", "Learn DevOps", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(3, "in28Minutes", "Learn Full Stack Development", LocalDate.now().plusYears(3), false));

    }


    public void addToDo(String description, LocalDate date){
        todos.add(new Todo(todos.size()+1, "in28Minutes", description, date, false));
    }

    public void updateToDo(int id){
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.get(id - 1).setDone(true);
    }

    public void deleteToDO(int id){
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }
    public List<Todo> findByUsername(String username){
        return todos;
    }
}
