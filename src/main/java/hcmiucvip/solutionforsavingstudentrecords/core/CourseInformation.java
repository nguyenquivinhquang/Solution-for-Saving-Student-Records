package hcmiucvip.solutionforsavingstudentrecords.core;

public class CourseInformation {
    private String courseId, courseTitle, courseDescription, courseSection;
    private String teacherId;
    private Integer courseCredits, size, remaining;

    public CourseInformation(String courseId, String courseTitle, Integer courseCredits,
                             String teacherId, String courseDescription, String courseSection) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.courseSection = courseSection;
        this.courseCredits = courseCredits;
        this.teacherId = teacherId;
        this.size = 30;
        this.remaining = 30;
        cleanText();
    }
    public CourseInformation(String courseId, String courseTitle, Integer courseCredits,
                             String teacherId, String courseDescription, String courseSection,
                             Integer size, Integer remaining) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.courseSection = courseSection;
        this.courseCredits = courseCredits;
        this.teacherId = teacherId;
        this.size = size;
        this.remaining = remaining;
        cleanText();
    }
    public CourseInformation(String courseId, Integer courseCredits) {
        this.courseId = courseId;
        if (courseCredits == null) courseCredits = 0;
        this.courseCredits = courseCredits;
    }
    public void cleanText() {
        if (this.courseId != null) this.courseId = this.courseId.trim();
        if (this.courseSection != null) this.courseSection = this.courseSection.trim();
        if (this.courseTitle != null) this.courseTitle = this.courseTitle.trim();
        if (this.courseDescription != null) this.courseDescription = this.courseDescription.trim();
        if (this.courseSection != null)  this.courseSection = this.courseSection.trim();
        if (this.teacherId != null) this.teacherId = this.teacherId.trim();

    }
    public void incSize() {
        this.size += 1;
    }
    public void desSize(){
        this.size -= 1;
    }
    public void incRemain() {
        this.remaining += 1;
    }
    public void desRemain() {
        this.remaining -= 1;
    }
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
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
