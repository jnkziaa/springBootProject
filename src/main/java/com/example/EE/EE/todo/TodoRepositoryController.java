package com.example.EE.EE.todo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TodoRepositoryController {
    private TodoService todoService;
    @Autowired
    private TodoRepository todoRepository;
    public TodoRepositoryController(TodoService todoService, TodoRepository todoRepository) {
        this.todoService = todoService;
        this.todoRepository = todoRepository;
    }
    @RequestMapping("list-todos")
    // /list-todos
    public String listAllTodos(ModelMap modelMap){
        String username = getLoggedinUsername(modelMap);
        List<Todo> todos = todoRepository.findByUsername(username);
        modelMap.put("todos", todos);
        return "listTodos";
    }


    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewToDoPage(ModelMap modelMap){
        String username = (String) modelMap.get("name");
        Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
        modelMap.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String postToDoPage(ModelMap modelMap, @Valid Todo todo, BindingResult result){
            if(result.hasErrors()){

                return "todo";
            }

        String username = getLoggedinUsername(modelMap);
        todoService.addToDo(todo.getDescription(), todo.getTargetDate(), username);
        List<Todo> todos = todoService.findByUsername(username);
        modelMap.put("todos", todos);
        return "listTodos";

    }

    @RequestMapping(value = "delete-todo")
    public String deleteToDoIndex(@RequestParam int id){
        todoService.deleteToDO(id);
        return "redirect:list-todos";

    }

    @RequestMapping(value = "update-todo")
    public String updateToDoIndex(@RequestParam int id){
        todoService.updateToDo(id);
        return "redirect:list-todos";

    }

    private String getLoggedinUsername(ModelMap modelMap){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();

    }


}
