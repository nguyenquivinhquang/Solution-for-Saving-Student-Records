package hcmiucvip.solutionforsavingstudentrecords.core;

public class ScholarInformation {
    String studentId, type;
    Integer price;
    Integer year, semester;

    public ScholarInformation(String studentId,String type,
                              Integer price, Integer year, Integer semester) {
        this.studentId = studentId;
        this.type = type;
        this.price = price;
        this.year = year;
        this.semester = semester;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getSemester() {
        return semester;
    }

    public String getStudentId() {
        return studentId;
    }

    public Integer getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }
}
