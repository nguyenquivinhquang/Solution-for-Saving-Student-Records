package hcmiucvip.solutionforsavingstudentrecords.core.DB;

import hcmiucvip.solutionforsavingstudentrecords.core.CourseStudentScore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EnrolledCourseQueries extends Querier {
    private static Connection connection = DatabaseConnectionManager.getDBConnection();

    public EnrolledCourseQueries() {
        super(connection, "Enrolled_Course");
    }

    public CourseStudentScore getStudentScore(String studentId, String courseId) {
        CourseStudentScore courseStudentScore;
        String SQL = String.format("SELECT [In_class]\n" +
                "      ,[Midterm]\n" +
                "      ,[Final]\n" +
                "      ,[Total]\n" +
                "      ,[Course_Id]\n" +
                "      ,[Student_Id]\n" +
                "FROM [StudentRecord].[dbo].[Enrolled_Course]\n" +
                "Where Student_Id = '%s' and Course_Id = '%s';", studentId, courseId);
        ResultSet res = runGetQuery(SQL);
        try {
            courseStudentScore = new CourseStudentScore(studentId,
                    courseId, res.getDouble("In_class"),
                    res.getDouble("Midterm"),
                    res.getDouble("Final"),
                    res.getDouble("Total"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bug on getStudentScore");
            return null;
        }
        return courseStudentScore;
    }

    public ObservableList<CourseStudentScore> getStudenScoreList(String studentId) {
        ObservableList<CourseStudentScore> courseStudentScores = FXCollections.observableArrayList();
        String SQL = String.format("SELECT [In_class]\n" +
                "      ,[Midterm]\n" +
                "      ,[Course_Id]\n" +
                "      ,[Final]\n" +
                "      ,[Total]\n" +
                "      ,[Course_Id]\n" +
                "      ,[Student_Id]\n" +
                "FROM [StudentRecord].[dbo].[Enrolled_Course]\n" +
                "Where Student_Id = '%s';", studentId);
        System.out.println(SQL);
        ResultSet res = runGetQuery(SQL);
        try {
            while (res.next()) {
                courseStudentScores.add(new CourseStudentScore(studentId,
                        res.getString("Course_Id").trim(),
                        res.getDouble("In_class"),
                        res.getDouble("Midterm"),
                        res.getDouble("Final"),
                        res.getDouble("Total")));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bug on getStudentScore");
        }
        return courseStudentScores;
    }
}
