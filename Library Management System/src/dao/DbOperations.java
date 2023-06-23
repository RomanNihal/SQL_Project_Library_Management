package dao;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import models.BookModel;
import models.BorrowedBookListModel;
import models.UserModel;

public class DbOperations {


    public static boolean setDataOrDelete(String query, String message) {
        try {
            Connection connectionProvider = ConnectionProvider.getConnection();
            Statement statement = connectionProvider.createStatement();
            statement.execute(query);
            if (!message.equals("") && !message.equals("Deleted successfullyyyy")) {
                    JOptionPane.showMessageDialog(null, message);
            }

            statement.close();
            connectionProvider.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, message, JOptionPane.ERROR_MESSAGE);
            return false;
        }


    }
    
    public static boolean shouldDelete(int book_id) throws ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count=0;

        try {
            // Establish the database connection
            connection = ConnectionProvider.getConnection();

            // Prepare the SQL statement
            String sql = "SELECT COUNT(book_id) FROM borrowedlist WHERE book_id=?";
            statement = connection.prepareStatement(sql);

            // Set the value for the prepared statement
            statement.setInt(1,book_id);

            // Execute the query
            resultSet = statement.executeQuery();

            // Check if a matching record was found
            if (resultSet.next()) {
                // Retrieve the first_name and last_name columns from the result set
                count = resultSet.getInt("COUNT(book_id)");
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        String visiblity="";
        try {
            // Establish the database connection
           connection = ConnectionProvider.getConnection();

            // Prepare the SQL statement
            String sql = "SELECT booklist.visibility FROM booklist WHERE book_id=?";
            statement = connection.prepareStatement(sql);

            // Set the value for the prepared statement
            statement.setInt(1,book_id);

            // Execute the query
            resultSet = statement.executeQuery();

            // Check if a matching record was found
            if (resultSet.next()) {
                // Retrieve the first_name and last_name columns from the result set
                visiblity = resultSet.getString("visibility");
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(count==0 && visiblity.equals("NO")){
            return true;
        }
        
    return false;
    }
    


    public static boolean authenticateUser(String id, String password) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            connection = ConnectionProvider.getConnection();
            statement = connection.createStatement();

            // Construct the SQL query
            String sql = "SELECT COUNT(*) FROM studentlist WHERE student_id = " + id + " AND password = '" + password + "'";

            // Execute the query
            resultSet = statement.executeQuery(sql);

            // Check if a matching record was found
            resultSet.next();
            int count = resultSet.getInt(1);

            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public static String getUserFullName(int id) throws ClassNotFoundException {
        String fullName = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish the database connection
            connection = ConnectionProvider.getConnection();

            // Prepare the SQL statement
            String sql = "SELECT first_name, last_name FROM studentlist WHERE student_id = ?";
            statement = connection.prepareStatement(sql);

            // Set the value for the prepared statement
            statement.setInt(1, id);

            // Execute the query
            resultSet = statement.executeQuery();

            // Check if a matching record was found
            if (resultSet.next()) {
                // Retrieve the first_name and last_name columns from the result set
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                // Concatenate the first_name and last_name into a single string
                fullName = firstName + " " + lastName;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return fullName;
    }
    public static ArrayList<BorrowedBookListModel> borrowedBookList(int id) throws ClassNotFoundException {
        ArrayList<BorrowedBookListModel> bookList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish the database connection
            connection = ConnectionProvider.getConnection();

            // Create a statement object
            statement = connection.createStatement();

            // Execute the query to retrieve all data from the users table
            String sql = "SELECT a.book_id,b.book_name,a.last_date FROM `borrowedlist` as a INNER JOIN booklist as b ON a.book_id=b.book_id WHERE a.student_id="+id+" ORDER BY a.last_date ASC;";
            resultSet = statement.executeQuery(sql);

            // Iterate through the result set and create User objects
            while (resultSet.next()) {
                int book_id = resultSet.getInt("book_id");
                String book_name = resultSet.getString("book_name");
                String last_date = resultSet.getString("last_date");
               

                // Create a User object and add it to the list
                BorrowedBookListModel user = new BorrowedBookListModel(book_id,book_name, last_date);
                bookList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return bookList;
    }

       public static ArrayList<BookModel> retrieveRecommendedBooksByDept(String department) throws ClassNotFoundException {
        ArrayList<BookModel> bookList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish the database connection
            connection = ConnectionProvider.getConnection();

            // Create a statement object
            statement = connection.createStatement();

            // Execute the query to retrieve all data from the users table
            String sql = "SELECT b.book_id,b.book_name,b.writers_name FROM borrowedList AS a RIGHT JOIN bookList AS b ON a.book_id = b.book_id WHERE b.visibility='YES' and b.department_code = '"+department+"' GROUP BY a.book_id ORDER BY COUNT(a.book_id) DESC LIMIT 4;";
            resultSet = statement.executeQuery(sql);

            // Iterate through the result set and create User objects
            while (resultSet.next()) {
                int book_id = resultSet.getInt("book_id");
                
                String book_name = resultSet.getString("book_name");
                String writers_name = resultSet.getString("writers_name");
                  String depart = "";
                  int amount=0;
                // Create a User object and add it to the list
                BookModel book = new BookModel(book_id,amount,book_name, writers_name,depart);
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return bookList;
    }
       
        public static ArrayList<BookModel> retrieveBooks(String type,String word) throws ClassNotFoundException {
        String searchType = null;
            switch(type){
            //"Book Name", "Book ID", "Author's Name", "Course ID", "Department"
            case "Book Name":
                searchType="book_name";
                break;
                
            case "Book ID":
                searchType="book_id";
                break;
            case "Author's Name":
                searchType="writers_name";
                break;
                
           
            case "Department":
                searchType="department_code";
                break;
            
              
        }
            
        ArrayList<BookModel> bookList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish the database connection
            connection = ConnectionProvider.getConnection();
            
            // Create a statement object
            statement = connection.createStatement();
           
            
            word=word.toUpperCase();
           String sql= "SELECT book_id,book_name,writers_name,department_code,amount FROM `booklist` WHERE visibility='YES' and "+searchType+" LIKE '"+word+"%';";
            // Execute the query to retrieve all data from the users table
            

            resultSet = statement.executeQuery(sql);

            // Iterate through the result set and create User objects
            while (resultSet.next()) {
                int book_id = resultSet.getInt("book_id");
                String book_name = resultSet.getString("book_name");
                String writers_name = resultSet.getString("writers_name");
                  String department = resultSet.getString("department_code");
                  int amount=resultSet.getInt("amount");
                // Create a User object and add it to the list
                BookModel book = new BookModel(book_id,amount,book_name, writers_name,department);
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return bookList;
    }
    
    public static String getDepartmentOfStudent(int id) throws ClassNotFoundException {
        
       String department = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish the database connection
            connection = ConnectionProvider.getConnection();

            // Prepare the SQL statement
            String sql = "SELECT department_code FROM studentlist WHERE student_id = ?";
            statement = connection.prepareStatement(sql);

            // Set the value for the prepared statement
            statement.setInt(1, id);

            // Execute the query
            resultSet = statement.executeQuery();

            // Check if a matching record was found
            if (resultSet.next()) {
                // Retrieve the first_name and last_name columns from the result set
                department = resultSet.getString("department_code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return department;
    }
    public static int getDue(int id) throws ClassNotFoundException {
       
         
         int count=0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish the database connection
            connection = ConnectionProvider.getConnection();

            // Prepare the SQL statement
            String sql = "SELECT COUNT(book_id) FROM borrowedlist WHERE student_id = ? AND CURRENT_DATE > last_date;";
            statement = connection.prepareStatement(sql);

            // Set the value for the prepared statement
            statement.setInt(1, id);

            // Execute the query
            resultSet = statement.executeQuery();

            // Check if a matching record was found
            if (resultSet.next()) {
                // Retrieve the first_name and last_name columns from the result set
                count = resultSet.getInt("COUNT(book_id)");
            
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
   
        return (count*100);
    }
    public static int getAvailablity(int id) throws ClassNotFoundException {
        
         
        int count=0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish the database connection
            connection = ConnectionProvider.getConnection();

            // Prepare the SQL statement
            String sql = "SELECT amount FROM booklist WHERE book_id=?;";
            statement = connection.prepareStatement(sql);

            // Set the value for the prepared statement
            statement.setInt(1, id);

            // Execute the query
            resultSet = statement.executeQuery();

            // Check if a matching record was found
            if (resultSet.next()) {
                // Retrieve the first_name and last_name columns from the result set
                count = resultSet.getInt("amount");
            
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
   
        return count;
    }

    public static int getTotalBook() throws ClassNotFoundException {
       int count=0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish the database connection
            connection = ConnectionProvider.getConnection();

            // Prepare the SQL statement
            String sql = "SELECT count(book_id) FROM booklist;";
            statement = connection.prepareStatement(sql);

            // Set the value for the prepared statement
            

            // Execute the query
            resultSet = statement.executeQuery();

            // Check if a matching record was found
            if (resultSet.next()) {
                // Retrieve the first_name and last_name columns from the result set
                count = resultSet.getInt("count(book_id)");
            
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
   
        return count;
    }
    
    
    
}
