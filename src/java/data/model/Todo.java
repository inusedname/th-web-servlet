/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.model;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author QuangNV
 */
public class Todo {

    public final static String tblName = "tbl_todo";

    public final int id;
    public final int groupId;
    public final String name;
    public final boolean done;
    
    public String getName() {
        return name;
    }

    public Todo(int groupId, String name) {
        id = 0;
        this.groupId = groupId;
        this.name = name;
        done = false;
    }

    public Todo(int groupId, String name, boolean done) {
        id = 0;
        this.groupId = groupId;
        this.name = name;
        this.done = done;
    }

    private Todo(int id, int groupId, String name, boolean done) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
        this.done = done;
    }
    
    @Nullable
    public static Todo parse(@Nonnull ResultSet sqlResult) {
        try {
            int id = sqlResult.getInt("id");
            String name = sqlResult.getString("name");
            int groupId = sqlResult.getInt("groupId");
            boolean done = sqlResult.getBoolean("done");
            
            return new Todo(id, groupId, name, done);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
