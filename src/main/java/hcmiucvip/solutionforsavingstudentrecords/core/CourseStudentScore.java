package hcmiucvip.solutionforsavingstudentrecords.core;

public class CourseStudentScore {
    private String studentId, courseId;
    private Double midtermScore, assignmentScore, finalScore, totalScore;
    public CourseStudentScore(String studentId, String courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }
    public CourseStudentScore(String studentId, String courseId, Double assignmentScore,
                              Double midtermScore, Double finalScore, Double totalScore) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.midtermScore = midtermScore;
        this.assignmentScore = assignmentScore;
        this.finalScore = finalScore;
        this.totalScore = totalScore;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Double getAssignmentScore() {
        return assignmentScore;
    }

    public Double getFinalScore() {
        return finalScore;
    }

    public Double getMidtermScore() {
        return midtermScore;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setAssignmentScore(Double assignmentScore) {
        this.assignmentScore = assignmentScore;
    }

    public void setFinalScore(Double finalScore) {
        this.finalScore = finalScore;
    }

    public void setMidtermScore(Double midtermScore) {
        this.midtermScore = midtermScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }
}

