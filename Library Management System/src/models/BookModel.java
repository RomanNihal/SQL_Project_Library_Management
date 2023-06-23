/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author siam
 */
public class BookModel {
    private int book_id,amount ;
    private String book_name, writers_name , department_code;

    public BookModel(int book_id, int amount, String book_name, String writers_name, String department_code) {
        this.book_id = book_id;
        this.amount = amount;
        this.book_name = book_name;
        this.writers_name = writers_name;
        this.department_code = department_code;
    }

    public BookModel(int book_id, String book_name, String writers_name) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.writers_name = writers_name;
    }

    public BookModel() {
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getWriters_name() {
        return writers_name;
    }

    public void setWriters_name(String writers_name) {
        this.writers_name = writers_name;
    }

    public String getDepartment_code() {
        return department_code;
    }

    public void setDepartment_code(String department_code) {
        this.department_code = department_code;
    }
    
    
}
