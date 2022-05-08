package hcmiucvip.solutionforsavingstudentrecords.core;

public class CourseInformation {
    private String courseId, courseTitle, courseDescription, courseSection;
    private String teacherId;
    private Integer courseCredits;

    public CourseInformation(String courseId, String courseTitle, Integer courseCredits,
                             String teacherId, String courseDescription, String courseSection) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.courseSection = courseSection;
        this.courseCredits = courseCredits;
        this.teacherId = teacherId;
        cleanText();
    }

    public void cleanText() {
        if (this.courseId != null) this.courseId = this.courseId.trim();
        if (this.courseSection != null) this.courseSection = this.courseSection.trim();
        if (this.courseTitle != null) this.courseTitle = this.courseTitle.trim();
        if (this.courseDescription != null) this.courseDescription = this.courseDescription.trim();
        if (this.courseSection != null)  this.courseSection = this.courseSection.trim();
        if (this.teacherId != null) this.teacherId = this.teacherId.trim();

    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseSection() {
        return courseSection;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setCourseSection(String courseSection) {
        this.courseSection = courseSection;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setCourseCredits(Integer courseCredits) {
        this.courseCredits = courseCredits;
    }

    public Integer getCourseCredits() {
        return courseCredits;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String toString() {
        return courseId + '-' + courseTitle + '-' + courseCredits + '-' + courseSection;
    }
}
