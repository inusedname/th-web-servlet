/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import data.local.TodoDao;
import data.model.Todo;
import data.model.TodoGroup;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QuangNV
 */
public class TodoController {

    public static int GROUP_ID = 1;

    private TodoDao dao;
    private final List<Todo> todos = new ArrayList<>();

    public List<Todo> getTodos() {
        return todos;
    }

    public TodoController(TodoDao dao) {
        this.dao = dao;
        GROUP_ID = prepopulatingDb();
    }

    public void fetchTodos() {
        List<Todo> fetched = dao.getTodosForGroup(GROUP_ID);
        todos.clear();
        todos.addAll(fetched);
    }

    public int insert(String name) {
        int newId = dao.insertTodo(new Todo(GROUP_ID, name));
        fetchTodos();
        return newId;
    }

    public void remove(int todoId) {
        dao.deleteTodo(todoId);
        fetchTodos();
    }

    public void edit(Todo newTodo) {
        dao.updateTodo(newTodo);
        fetchTodos();
    }

    private Integer prepopulatingDb() {
        List<TodoGroup> groups = dao.getAllGroups();
        if (groups.isEmpty()) {
            int newGroupId = dao.insertGroup(new TodoGroup("Sample"));
            return newGroupId;
        }
        return groups.get(0).id;
    }
}
