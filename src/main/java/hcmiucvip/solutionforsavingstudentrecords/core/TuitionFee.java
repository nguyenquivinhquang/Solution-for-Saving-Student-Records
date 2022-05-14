package hcmiucvip.solutionforsavingstudentrecords.core;

public class TuitionFee {
    Integer semester, year;
    Double pricePerCredit, insurancePrice;

    public TuitionFee() {

    }

    public TuitionFee(Integer semester, Integer year,
                      Double pricePerCredit, Double insurancePrice) {
        this.semester = semester;
        this.year = year;
        this.pricePerCredit = pricePerCredit;
        this.insurancePrice = insurancePrice;
    }

    public Double getInsurancePrice() {
        return insurancePrice;
    }

    public Double getPricePerCredit() {
        return pricePerCredit;
    }

    public Integer getSemester() {
        return semester;
    }

    public Integer getYear() {
        return year;
    }

    public void setInsurancePrice(Double insurancePrice) {
        this.insurancePrice = insurancePrice;
    }

    public void setPricePerCredit(Double pricePerCredit) {
        this.pricePerCredit = pricePerCredit;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
