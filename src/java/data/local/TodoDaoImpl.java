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
    public int insertGroup(TodoGroup group) {
        int newId = database.insert(
                "INSERT INTO tbl_group(name, createMilli) VALUES(?, ?)",
                group.name,
                group.createMilli
        );
        return newId;
    }

    @Override
    public int insertTodo(Todo todo) {
        int newId = database.insert(
                "INSERT INTO tbl_todo (done, groupId, name) VALUES(?, ?, ?)",
                todo.done,
                todo.groupId,
                todo.name
        );
        return newId;
    }

    @Override
    public void updateTodo(Todo newTodo) {
        database.update(
                "UPDATE tbl_todo SET done=?, groupId=?, name=? WHERE id=?",
                newTodo.done,
                newTodo.groupId,
                newTodo.name,
                newTodo.id
        );
    }

    @Override
    public void deleteTodo(int todoId) {
        database.update(
                "DELETE FROM tbl_todo WHERE id=?",
                todoId
        );
    }

    @Override
    public List<TodoGroup> getAllGroups() {
        List<TodoGroup> groups = new ArrayList<>();

        try (ResultSet result = database.query("SELECT * FROM tbl_group")) {
            while (result.next()) {
                groups.add(TodoGroup.parse(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    @Override
    public List<Todo> getTodosForGroup(int groupId) {
        List<Todo> groups = new ArrayList<>();
        try (ResultSet result = database.query(
                "SELECT * FROM tbl_todo WHERE groupId=?",
                groupId)) {
            while (result.next()) {
                groups.add(Todo.parse(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }
}
