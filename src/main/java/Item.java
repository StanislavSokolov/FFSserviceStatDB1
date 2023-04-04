public class Item {
    private String supplierArticle = "";
    private int quantity = 0;
    private int quantityFull = 0;
    private int nmId = 0;
    private String subject = "";
    private int warehouseSaintPetersburg = 0;

    public int getWarehouseSaintPetersburg() {
        return warehouseSaintPetersburg;
    }

    public int getWarehouseSaintPetersburg2() {
        return warehouseSaintPetersburg2;
    }

    public int getWarehouseKoledino() {
        return warehouseKoledino;
    }

    public int getWarehouseElectrostal() {
        return warehouseElectrostal;
    }

    public int getWarehouseKazan() {
        return warehouseKazan;
    }

    public int getWarehouseOther() {
        return warehouseOther;
    }

    private int warehouseSaintPetersburg2 = 0;
    private int warehouseKoledino = 0;
    private int warehouseElectrostal = 0;
    private int warehouseKazan = 0;
    private int warehouseOther = 0;
    private int price = 0;
    private int discount = 0;
    private int promoCode = 0;
    private int total = 0;

    private boolean coincidence = false;

    public boolean isCoincidence() {
        return coincidence;
    }

    public void setCoincidence(boolean coincidence) {
        this.coincidence = coincidence;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Item(String supplierArticle, int quantity, int quantityFull, int nmId, String subject, String warehouseName) {
        this.supplierArticle = supplierArticle;
        this.quantity = quantity;
        this.quantityFull = quantityFull;
        this.nmId = nmId;
        this.subject = subject;
        if (warehouseName.equals("Санкт-Петербург")) warehouseSaintPetersburg = quantity;
        else if (warehouseName.equals("Санкт-Петербург 2")) warehouseSaintPetersburg2 = quantity;
        else if (warehouseName.equals("Коледино")) warehouseKoledino = quantity;
        else if (warehouseName.equals("Электросталь")) warehouseElectrostal = quantity;
        else if (warehouseName.equals("Казань")) warehouseKazan = quantity;
        else warehouseOther = quantity;
    }

    public void addQuantityWarehouse(int quantity, String warehouseName) {
        if (warehouseName.equals("Санкт-Петербург")) warehouseSaintPetersburg = warehouseSaintPetersburg + quantity;
        else if (warehouseName.equals("Санкт-Петербург 2")) warehouseSaintPetersburg2 = warehouseSaintPetersburg2 + quantity;
        else if (warehouseName.equals("Коледино")) warehouseKoledino = warehouseKoledino + quantity;
        else if (warehouseName.equals("Электросталь")) warehouseElectrostal = warehouseElectrostal + quantity;
        else if (warehouseName.equals("Казань")) warehouseKazan = warehouseKazan + quantity;
        else warehouseOther = warehouseOther + quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setQuantityFull(int quantityFull) {
        this.quantityFull = quantityFull;
    }

    public void setPromoCode(int promoCode) {
        this.promoCode = promoCode;
    }

    public int getPromoCode() {
        return promoCode;
    }

    public void setSupplierArticle(String supplierArticle) {
        this.supplierArticle = supplierArticle;
    }

    public void setNmId(int nmId) {
        this.nmId = nmId;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getSupplierArticle() {
        return supplierArticle;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getQuantityFull() {
        return quantityFull;
    }

    public int getNmId() {
        return nmId;
    }

    public String getSubject() {
        return subject;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }
}

