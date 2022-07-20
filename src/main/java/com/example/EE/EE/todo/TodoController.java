package com.example.EE.EE.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // /list-todos
    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap modelMap){
        List<Todo> todos = todoService.findByUsername("johnjake14");
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

        todoService.addToDo(todo.getDescription(), todo.getTargetDate());
        List<Todo> todos = todoService.findByUsername("johnjake14");
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


}
