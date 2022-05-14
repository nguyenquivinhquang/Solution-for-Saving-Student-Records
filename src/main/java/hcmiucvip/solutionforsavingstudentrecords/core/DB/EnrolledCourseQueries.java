package hcmiucvip.solutionforsavingstudentrecords.core.DB;

import hcmiucvip.solutionforsavingstudentrecords.core.CourseStudentScore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;

public class EnrolledCourseQueries extends Querier {
    private static Connection connection = DatabaseConnectionManager.getDBConnection();

    public EnrolledCourseQueries() {
        super(connection, "Enrolled_Class");
    }

    public CourseStudentScore getStudentScore(String studentId, String courseId) {
        CourseStudentScore courseStudentScore;
        String SQL = String.format("SELECT [In_class]\n" +
                "      ,[Midterm]\n" +
                "      ,[Final]\n" +
                "      ,[Total]\n" +
                "      ,[Course_Id]\n" +
                "      ,[Student_Id]\n" +
                "FROM [StudentRecord].[dbo].[Enrolled_Class]\n" +
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

    public ObservableList<CourseStudentScore> getStudentScoreList(String studentId) {
        ObservableList<CourseStudentScore> courseStudentScores = FXCollections.observableArrayList();
        String SQL = String.format("SELECT [In_class]\n" +
                "      ,[Midterm]\n" +
                "      ,[Course_Id]\n" +
                "      ,[Final]\n" +
                "      ,[Course_Id]\n" +
                "      ,[Student_Id]\n" +
                "      ,[Course_Id]\n" +
                "      ,[Section]\n" +
                "      ,[Teacher_Id]\n" +
                "FROM [StudentRecord].[dbo].[Enrolled_Class]\n" +
                "Where Student_Id = '%s';", studentId);
        System.out.println(SQL);
        ResultSet res = runGetQuery(SQL);
        try {
            while (res.next()) {
                CourseStudentScore courseStudentScore = new CourseStudentScore(studentId,
                        res.getString("Course_Id").trim(),
                        res.getDouble("In_class"),
                        res.getDouble("Midterm"),
                        res.getDouble("Final"),
                        (double) 0);
            courseStudentScore.setTotalScore(countTotalScore(res.getDouble("In_class"),
                    res.getDouble("Midterm"),
                    res.getDouble("Final"),
                    res.getString("Course_Id"),
                    res.getString("Section"),
                    res.getString("Teacher_Id")));
                courseStudentScores.add(courseStudentScore);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bug on getStudentScoreList");
        }
        return courseStudentScores;
    }

    public double countTotalScore(Double inClass, Double midterm, Double finalScore,
                                  String courseId, String section, String teacherId) {
        CourseQueries courseQueries = new CourseQueries();
        Double midtermPercentage = courseQueries.getPercentage(courseId, section, teacherId,"Midterm_percentage") ;
        Double inclassPercentage = courseQueries.getPercentage(courseId, section, teacherId,"Inclass_percentage") ;
        Double finalPercentage = courseQueries.getPercentage(courseId, section, teacherId,"Final_percentage") ;

        return inclassPercentage * inClass + midtermPercentage * midterm + finalPercentage * finalScore;
    }

    public void updateScore(Double inClassScore, Double midtermScore,
                            Double finalScore, String teacherId,
                            String courseId, String section, String studentID) {
        String SQL = "UPDATE Enrolled_Class set " +
                "In_class=%.2f, " +
                "Midterm=%.2f, " +
                "Final=%.2f " +
                " Where Student_Id = '%s' and Course_Id = '%s'" +
                " and Section = '%s' and Teacher_Id='%s';";
        SQL = String.format(SQL, inClassScore, midtermScore, finalScore,
                studentID, courseId, section, teacherId);
        System.out.println(SQL);
        runSetQuery(SQL);
    }
}
