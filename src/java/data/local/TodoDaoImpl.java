/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.local;

import data.model.Todo;
import data.model.TodoGroup;
import jakarta.annotation.Nonnull;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QuangNV
 */
public class TodoDaoImpl implements TodoDao {

    private final MySQLManager database;

    public TodoDaoImpl(@Nonnull MySQLManager database) {
        this.database = database;
    }

    @Override
    public int insertGroup(TodoGroup group) throws SQLException {
        int newId = database.insert(
                "INSERT INTO tblGroup(name, createMilli) VALUES(?, ?)",
                group.name,
                group.createMilli
        );
        return newId;
    }

    @Override
    public int insertTodo(Todo todo) throws SQLException {
        int newId = database.insert(
                "INSERT INTO tblTodo (done, groupId, name) VALUES(?, ?, ?)",
                todo.done,
                todo.groupId,
                todo.name
        );
        return newId;
    }

    @Override
    public void updateTodo(Todo newTodo) throws SQLException {
        database.update(
                "UPDATE tblTodo SET done=?, groupId=?, name=? WHERE id=?",
                newTodo.done,
                newTodo.groupId,
                newTodo.name,
                newTodo.id
        );
    }

    @Override
    public void deleteTodo(int todoId) throws SQLException {
        database.update(
                "DELETE FROM tblTodo WHERE id=?",
                todoId
        );
    }

    @Override
    public List<TodoGroup> getAllGroups() throws SQLException {
        List<TodoGroup> groups = new ArrayList<>();
        ResultSet result = database.query(
                "SELECT * FROM tblGroup"
        );
        while (result.next()) {
            groups.add(TodoGroup.parse(result));
        }
        result.close();
        return groups;
    }

    @Override
    public List<Todo> getTodosForGroup(int groupId) throws SQLException {
        List<Todo> groups = new ArrayList<>();
        ResultSet result = database.query(
                "SELECT * FROM tblTodo WHERE groupId=?",
                groupId
        );
        while (result.next()) {
            groups.add(Todo.parse(result));
        }
        result.close();
        return groups;
    }
}
