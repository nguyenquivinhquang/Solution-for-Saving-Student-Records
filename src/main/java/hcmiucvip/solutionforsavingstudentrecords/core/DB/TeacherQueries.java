package hcmiucvip.solutionforsavingstudentrecords.core.DB;

import hcmiucvip.solutionforsavingstudentrecords.core.CourseStudentScore;
import hcmiucvip.solutionforsavingstudentrecords.core.StudentInformation;
import hcmiucvip.solutionforsavingstudentrecords.core.TeacherInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class TeacherQueries extends Querier {
    private static Connection connection = DatabaseConnectionManager.getDBConnection();

    public TeacherQueries() {
        super(connection, "Teacher");

    }


    public boolean existTeacherId(String teacherId) {
//        return true;
        return existValueRows(this.tableName, "Teacher_Id", teacherId);
    }

    public ObservableList<TeacherInformation> getTeacherList() {
        ObservableList<TeacherInformation> teacherInformation = FXCollections.observableArrayList();
        try {
            String SQL = "Select Teacher_Id,First_name, Last_name, Department ,Username, Mail from Teacher;";
            Statement statement = connection.createStatement();
            System.out.println(SQL);
            ResultSet res = statement.executeQuery(SQL);
            if (!res.isBeforeFirst()) return teacherInformation;
            while (res.next()) {
                TeacherInformation teacher = new TeacherInformation(
                        res.getString("Teacher_Id").trim(),
                        res.getString("First_name").trim(),
                        res.getString("Last_name").trim(),
                        res.getString("Username").trim(),
                        res.getString("Department").trim(),
                        res.getString("Mail").trim()
                );

                teacherInformation.add(teacher);
                System.out.println(teacher);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return teacherInformation;
    }

    public void insertMultiValues(ArrayList<Pair<String, Object>> insertValues) {
        this.insertMultiValues(this.tableName, insertValues);
    }

    public void updateFirstName(String teacherId, String newValue) {
        updateRowString("Teacher_Id", teacherId, "First_name", newValue);
    }

    public void updateLastName(String teacherId, String newValue) {
        updateRowString("Teacher_Id", teacherId, "Last_name", newValue);
    }

    public void updateDepartment(String teacherId, String newValue) {
        updateRowString("Teacher_Id", teacherId, "Department", newValue);
    }

    public void updateMail(String teacherId, String newValue) {
        updateRowString("Teacher_Id", teacherId, "Mail", newValue);

    }

    public void deleteTeacher(String teacher) {
        String SQL = String.format("DELETE FROM Teacher Where Username='%s'", teacher);
        System.out.println(SQL);
        runSetQuery(SQL);
    }

    public ArrayList<String> getTeacherClass(String teacherId) {
        ArrayList<String> classes = new ArrayList<>();

        String SQL = "SELECT  [Section]\n" +
                "      ,[Teacher_Id]\n" +
                "      ,[Course_Id]\n" +
                "      ,[Size]\n" +
                "      ,[Remaining]\n" +
                "  FROM [StudentRecord].[dbo].[Class]\n" +
                "  where Teacher_Id = '%s';";
        SQL = String.format(SQL, teacherId);
        ResultSet res = runGetQuery(SQL);
        try {
            while (res.next()) {
                String courseId = res.getString("Course_Id").trim();
                String section = res.getString("Section").trim();
                classes.add(courseId + '-' + section);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getTeacherClass");
        }
        return classes;
    }

    public ObservableList<CourseStudentScore> getStudentRecordList(String teacherId, String courseId, String section) {
        ObservableList<CourseStudentScore> studentScores = FXCollections.observableArrayList();
        String SQL = "SELECT TOP (1000) [In_class]\n" +
                "      ,[Midterm]\n" +
                "      ,[Final]\n" +
                "      ,[Total]\n" +
                "      ,[Student_Id]\n" +
                "      ,[Section]\n" +
                "      ,[Teacher_Id]\n" +
                "      ,[Course_Id]\n" +
                "  FROM [StudentRecord].[dbo].[Enrolled_Course]\n" +
                "  where Teacher_Id = '%s' and Section = '%s' and Course_Id ='%s';";
        SQL = String.format(SQL, teacherId, section, courseId);
        System.out.println(SQL);
        ResultSet res = runGetQuery(SQL);
        try {
            while (res.next()) {
                studentScores.add(new CourseStudentScore(
                        res.getString("Student_Id").trim(),
                        courseId.trim(),
                        res.getDouble("In_class"),
                        res.getDouble("Midterm"),
                        res.getDouble("Final"),
                        res.getDouble("Total")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bug on getStudentRecordList");
        }
        return studentScores;
    }
}
