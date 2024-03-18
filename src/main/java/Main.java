import java.sql.*;
import  java.util.Date;


public class Main {

    static public String url = "jdbc:postgresql://localhost:5432/A3 - Q1"; // may need to update according to your specific postgresql setup
    static public String user = "postgres"; // may need to update according to your specific postgresql setup
    static public String password= " "; // must use password related to your postgresql servers/databases

    /*
        Function: getAllStudents()
        Purpose: Retrieves and displays all records from the students table.
        Parameters: N/A
    */

    static void getAllStudents(){

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students");
            ResultSet resultSet = statement.getResultSet();

            while(resultSet.next()){
                System.out.print(resultSet.getInt("student_id") + " " + resultSet.getString("first_name") + " " + resultSet.getString("last_name") + " " + resultSet.getString("email") + " " +  resultSet.getDate("enrollment_date") + "\n" );
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    /*
    Function: addStudent(String first_name, String last_name, String email, String enrollment_date)
    Purpose: Inserts a new student record into the students table in database.
    Parameters:
        - String first_name is the first name of new student
        - String last_name is the last name of the new student
        - String email is the email of the new student
        - String enrollment_date is the enrollment date of the new student
     */
    static void addStudent(String first_name, String last_name, String email, String enrollment_date){
        String query = "INSERT INTO students(first_name, last_name, email, enrollment_date) " +
                "VALUES(?,?,?,?)";


        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            // set statements to fill in the question marks in the above query string
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setString(3, email);
            statement.setDate(4, java.sql.Date.valueOf(enrollment_date));
            statement.executeUpdate();

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    /*
    Function: updateStudentEmail(int student_id, String new_email)
    Purpose:  Updates the email address for a student in the database with the specified student_id.
    Parameters:
        - int student_id is the id of student to be updated
        - String new_email is the email to replace current email
     */
    static void updateStudentEmail(int student_id, String new_email){

        var query = "UPDATE students SET email = ? WHERE student_id = ?";

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(query);

            // set statements to fill in the question marks in the above query string
            statement.setString(1, new_email);
            statement.setInt(2, student_id);
            statement.executeUpdate();

        }
        catch (Exception e){
            System.out.println(e);
        }

    }


    /*
        Function: deleteStudent(int student_id)
        Purpose: Deletes the table record of the student with the specified student_id.
        Parameters:
            - int student_id is the id of student to be deleted
     */
    static void deleteStudent(int student_id){

        var query = "DELETE FROM students WHERE student_id = ?";

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(query);

            // set statements to fill in the question mark in the above query string
            statement.setInt(1,student_id);
            statement.executeUpdate();


        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public static void main(String[] args){
        getAllStudents();
        updateStudentEmail(3, "BEAM@ex.com");
        addStudent("Johan", "Cruyf", "barca@wow.com",   "1971-10-10");
        deleteStudent(2);


    }
}

