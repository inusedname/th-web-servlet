/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.local;
import data.model.Todo;
import data.model.TodoGroup;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author QuangNV
 */
public interface TodoDao {
    // Insert a new group into the database and return its ID
    public int insertGroup(TodoGroup group);

    // Insert a new todo item into the database and return its ID
    public int insertTodo(Todo todo);

    // Update an existing todo item in the database
    public void updateTodo(Todo newTodo);

    // Delete a todo item from the database
    public void deleteTodo(int todoId);

    // Retrieve a list of all groups from the database
    public List<TodoGroup> getAllGroups();

    // Retrieve all todos for a given group from the database
    public List<Todo> getTodosForGroup(int groupId);
}
