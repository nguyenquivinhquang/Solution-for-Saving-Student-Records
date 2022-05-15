package hcmiucvip.solutionforsavingstudentrecords.core;

public class SemesterBilling {
    Integer year, semester, credits;
    Double pricePerCredit, insurancePrice, total;
    Double moneyReceived;

    public SemesterBilling() {

    }

    public SemesterBilling(Integer year, Integer semester, Integer credits,
                           Double pricePerCredit, Double insurancePrice, Double moneyReceived) {
        this.year = year;
        this.semester = semester;
        this.credits = credits;
        this.pricePerCredit = pricePerCredit;
        this.insurancePrice = insurancePrice;
        this.moneyReceived = moneyReceived;

    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public void setPricePerCredit(Double pricePerCredit) {
        this.pricePerCredit = pricePerCredit;
    }

    public void setInsurancePrice(Double insurancePrice) {
        this.insurancePrice = insurancePrice;
    }

    public Integer getSemester() {
        return semester;
    }

    public Integer getYear() {
        return year;
    }

    public Double getPricePerCredit() {
        return pricePerCredit;
    }

    public Double getInsurancePrice() {
        return insurancePrice;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getTotal() {
        return  String.format("%.2f",pricePerCredit * credits + insurancePrice);
    }
}