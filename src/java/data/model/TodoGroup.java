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
public class TodoGroup {

    public final static String tblName = "tbl_todo";

    public final int id;
    public final String name;
    public final long createMilli;

    public TodoGroup(String name) {
        this.id = 0;
        this.name = name;
        this.createMilli = System.currentTimeMillis();
    }

    private TodoGroup(int id, String name, long createMilli) {
        this.id = id;
        this.name = name;
        this.createMilli = createMilli;
    }
    
    @Nullable
    public static TodoGroup parse(@Nonnull ResultSet sqlResult) {
        try {
            int id = sqlResult.getInt("id");
            String name = sqlResult.getString("name");
            long createMilli = sqlResult.getInt("createMilli");
            
            return new TodoGroup(id, name, createMilli);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
