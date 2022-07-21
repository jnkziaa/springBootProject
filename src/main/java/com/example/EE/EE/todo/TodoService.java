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
        todos.add(new Todo(1, "johnjake14", "Learn AWS", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(2, "johnjake14", "Learn DevOps", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(3, "johnjake14", "Learn Full Stack Development", LocalDate.now().plusYears(3), false));

    }


    public void addToDo(String description, LocalDate date, String username){
        todos.add(new Todo(todos.size()+1, username, description, date, false));
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
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }
}
