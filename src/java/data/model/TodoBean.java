/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Bean.java to edit this template
 */
package data.model;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author QuangNV
 */
public class TodoBean {
    private String name;

    public TodoBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
