package hcmiucvip.solutionforsavingstudentrecords.core.DB;

import hcmiucvip.solutionforsavingstudentrecords.core.CourseInformation;
import hcmiucvip.solutionforsavingstudentrecords.core.CourseStudentScore;
import hcmiucvip.solutionforsavingstudentrecords.core.StudentInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentQueries extends Querier {
    private static Connection connection = DatabaseConnectionManager.getDBConnection();
    private String tableStudentScoreName = "";

    public StudentQueries() {
        super(connection, "Student");

    }

    public ObservableList<StudentInformation> getStudentList() {
        ObservableList<StudentInformation> studentInformations = FXCollections.observableArrayList();
        try {
            String SQL = "Select Student_Id,First_name, Last_name, Birth_day, Academic_year,Bid,Username, Mail from Student;";
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(SQL);
            if (!res.isBeforeFirst()) return studentInformations;
            while (res.next()) {
                StudentInformation student = new StudentInformation(
                        res.getString("Student_Id").trim(),
                        res.getString("First_name").trim(),
                        res.getString("Last_name").trim(),
                        res.getInt("Academic_year"),
                        res.getString("Birth_day").trim(),
                        res.getString("Mail").trim()
                );
                if (res.getString("Bid") != null)
                    student.setbId(res.getString("Bid").trim());
                studentInformations.add(student);
                System.out.println(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentInformations;
    }

    public StudentInformation getStudentInformation(String studentId) {
        StudentInformation student;
        try {
            String SQL = "Select Student_Id,First_name, Last_name, Birth_day, Academic_year,Bid,Username, Mail from Student where Student_Id = '%s';";
            SQL = String.format(SQL, studentId);
            System.out.println(SQL);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(SQL);
            res.next();

            student = new StudentInformation(
                    res.getString("Student_Id").trim(),
                    res.getString("First_name").trim(),
                    res.getString("Last_name").trim(),
                    res.getInt("Academic_year"),
                    res.getString("Birth_day").trim(),
                    res.getString("Mail").trim()
            );
            if (res.getString("Bid") != null)
                student.setbId(res.getString("Bid"));
            System.out.println(student);
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bug on getStudentInfor");
        }

        return null;
    }

    public void updateFirstName(String studentId, String newValue) {
        updateRowString("Student_Id", studentId, "First_name", newValue);
    }

    public void updateLastName(String studentId, String newValue) {
        updateRowString("Student_Id", studentId, "Last_name", newValue);
    }

    public void updateBirthday(String studentId, String newValue) {
        updateRowString("Student_Id", studentId, "Birth_day", newValue);
    }

    public void updateAcademicYear(String studentId, Integer newValue) {
        updateRowInteger("Student_Id", studentId, "Academic_year", newValue);
    }

    public void updateBid(String studentId, String newValue) {
        changeValCol(studentId, "Bid", newValue);
    }

    public void updateMail(String studentId, String newValue) {
        changeValCol(studentId, "Mail", newValue);
    }

    public void updatePassword(String studentId, String newValue) {
        changeValCol(studentId, "Password", newValue);
    }

    private void changeValCol(String studentId, String col, String newValue) {
        updateRowString("Student_Id", studentId, col, newValue);
    }

    public boolean insertMultiValues(ArrayList<Pair<String, Object>> insertValues) {
        return this.insertMultiValues(this.tableName, insertValues);
    }

    public void deleteStudent(String student) {
        String SQL = String.format("DELETE FROM Student Where Username='%s'", student);
        System.out.println(SQL);
        runSetQuery(SQL);
    }

    public boolean existStudentId(String username) {
        return this.existUsername("Student", username);
    }

    //    public boolean existUsername(String username) {
//        String SQL = "SELECT Username FROM  [dbo].[Student]  WHERE Student_id = '%s' ";
//        SQL = String.format(SQL, username);
//        System.out.println(SQL);
//        ResultSet res = runGetQuery(SQL);
//        if (res == null) return false;
//        try {
//            if (res.isBeforeFirst()) return true;
//        } catch (Exception e) {
////            e.printStackTrace();
//            return false;
//        }
//        return false;
//    }
    public ArrayList<CourseStudentScore> courseStudentScore(String studentId) {
        ArrayList<CourseStudentScore> records = new ArrayList<>();
        String SQL = "Select Course_Id, Final, Total, In_class, Midterm from Enrolled_Course where Student_Id = '%s'";

        SQL = String.format(SQL, studentId);

        ResultSet res = runGetQuery(SQL);
        try {
            while (res.next()) {
                records.add(new CourseStudentScore(
                        studentId,
                        res.getString("Course_Id").trim(),
                        res.getDouble("In_class"),
                        res.getDouble("Midterm"),
                        res.getDouble("Final"),
                        res.getDouble("Total")
                ));

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bug on courseStudentScore");

        }
        return records;
    }

    public ObservableList<CourseInformation> getCurrentRunningCourse(String studentId) {
        ObservableList<CourseInformation> courseInformations = FXCollections.observableArrayList();

        String SQL = "select Course_Id from Enrolled_Course\n" +
                "where Student_Id='%s';";
        SQL = String.format(SQL, studentId);
        System.out.println(SQL);
        ResultSet res = runGetQuery(SQL);
        try {
            while (res.next()) {
                CourseInformation course = new CourseInformation();
                course.setCourseId(res.getString("Course_Id").trim());
                courseInformations.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bug on getCurrentRunningCourse");
        }
        return courseInformations;
    }
}
