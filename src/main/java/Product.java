public class Product {
    private String cdate = "";
    private String ctime = "";
    private String csubject = "";
    private String supplierArticle = "";
    private int nmId = 0;
    private int finishedPrice = 0;
    private int forPay = 0;
    private String oblastOkrugName = "";
    private boolean coincidence = false;

    public String getOdid() {
        return odid;
    }

    private String odid = "";

    public boolean isCoincidence() {
        return coincidence;
    }

    public void setCoincidence(boolean coincidence) {
        this.coincidence = coincidence;
    }

    public Product(String cdate, String csubject, String supplierArticle, int nmId, int finishedPrice, int forPay, String oblastOkrugName, String odid) {
        this.cdate = cdate.substring(0, 10);
        this.ctime = cdate.substring(11, 19);
        this.csubject = csubject;
        this.supplierArticle = supplierArticle;
        this.nmId = nmId;
        this.finishedPrice = finishedPrice;
        this.forPay = forPay;
        this.oblastOkrugName = oblastOkrugName;
        this.odid = odid;
        this.coincidence = false;
    }

    public String getCdate() {
        return cdate;
    }

    public String getCtime() {
        return ctime;
    }

    public String getCsubject() {
        return csubject;
    }

    public String getSupplierArticle() {
        return supplierArticle;
    }

    public int getNmId() {
        return nmId;
    }

    public int getFinishedPrice() {
        return finishedPrice;
    }

    public int getForPay() {
        return forPay;
    }

    public String getOblastOkrugName() {
        return oblastOkrugName;
    }

    public Product(String cdate, String csubject, String supplierArticle, int nmId, String odid) {
        this.cdate = cdate.substring(0, 10);
        this.ctime = cdate.substring(11, 19);
        this.csubject = csubject;
        this.supplierArticle = supplierArticle;
        this.nmId = nmId;
        this.odid = odid;
        this.coincidence = false;
    }
}
