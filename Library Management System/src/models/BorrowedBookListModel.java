/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author siam
 */
public class BorrowedBookListModel {
    private int book_id;
    private String book_name,last_date;

    public BorrowedBookListModel(int book_id, String book_name, String last_date) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.last_date = last_date;
    }

    public BorrowedBookListModel() {
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getLast_date() {
        return last_date;
    }

    public void setLast_date(String last_date) {
        this.last_date = last_date;
    }
    
    
}
