import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class SQL {
    public static void createBD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = getConnection()) {
                Statement statement = conn.createStatement();
                System.out.println("Connection to Store DB succesfull!");
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }

    public static Connection getConnection() throws SQLException, IOException {
        return DriverManager.getConnection("jdbc:mysql://localhost/ffs", "user","password");
    }

    public static void upDate(String dateCurrent, int sumSale, int sumOrder, int sumSaleMoney, String s, String shop) {
        if (shop.equals("wb")) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try (Connection conn = getConnection()) {
                    boolean check = false;
                    String cdate = "";
                    Statement statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM saleorderstat");
                    while (resultSet.next()) {
                        cdate = resultSet.getString("cdate");
                        if (cdate.equals(dateCurrent)) {
                            check = true;
                            statement.executeUpdate("UPDATE saleorderstat SET sumSale = " +  sumSale + ", sumOrder = " + sumOrder + ", sumSaleMoney = " + sumSaleMoney + ", popItem = '" + s + "' WHERE Id = " + resultSet.getInt("Id"));
                            break;
                        }
                    }
                    if (!check) {
                        statement.executeUpdate("INSERT saleorderstat(cdate, sumSale, sumOrder, sumSaleMoney, popItem) VALUES ('" + dateCurrent + "', " + sumSale + ", " + sumOrder + ", " + sumSaleMoney + ", '" + s + "')");
                    }

                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (shop.equals("ozon")) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try (Connection conn = getConnection()) {
                    boolean check = false;
                    String cdate = "";
                    Statement statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM salestat");
                    while (resultSet.next()) {
                        cdate = resultSet.getString("cdate");
                        if (cdate.equals(dateCurrent)) {
                            check = true;
                            statement.executeUpdate("UPDATE salestat SET sumSale = " +  sumSale + ", sumOrder = " + sumOrder + ", sumSaleMoney = " + sumSaleMoney + ", popItem = '" + s + "' WHERE Id = " + resultSet.getInt("Id"));
                            break;
                        }
                    }
                    if (!check) {
                        statement.executeUpdate("INSERT salestat(cdate, sumSale, sumOrder, sumSaleMoney, popItem) VALUES ('" + dateCurrent + "', " + sumSale + ", " + sumOrder + ", " + sumSaleMoney + ", '" + s + "')");
                    }

                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }

    public static void upDate(ArrayList<Product> products, String mode) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = getConnection()) {
                String odid = "";
                Statement statement = conn.createStatement();
                if (mode.equals("sales")) {
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM statofeverysalefromwb");
                    while (resultSet.next()) {
                        odid = resultSet.getString("odid");
                        for (Product p: products) {
                            if (odid.equals(p.getOdid())) {
                                p.setCoincidence(true);
                            }
                        }
                    }
                    for (Product p: products) {
                        if (!p.isCoincidence()) statement.executeUpdate("INSERT statofeverysalefromwb(cdate, ctime, csubject, supplierArticle, nmId, finishedPrice, forPay, oblastOkrugName, odid) VALUES ('" + p.getCdate() + "', '" + p.getCtime() + "', '" + p.getCsubject() + "', '"  + p.getSupplierArticle() + "', " + p.getNmId() + ", " + p.getFinishedPrice() + ", " + p.getForPay() + ", '" + p.getOblastOkrugName() + "', '" + p.getOdid() + "')");
//                        statement.executeUpdate("INSERT statofeverysalefromwb(cdate, ctime, csubject, supplierArticle, nmId, finishedPrice, forPay, oblastOkrugName, odid) VALUES ('" + p.getCdate() + "', '" + p.getCtime() + "', '" + p.getCsubject() + "', '"  + p.getSupplierArticle() + "', " + p.getNmId() + ", " + p.getFinishedPrice() + ", " + p.getForPay() + ", '" + p.getOblastOkrugName() + "', '" + p.getOdid() + "')");

                    }
                }
                if (mode.equals("orders")) {
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM statofeveryorderfromwb");
                    while (resultSet.next()) {
                        odid = resultSet.getString("odid");
                        for (Product p: products) {
                            if (odid.equals(p.getOdid())) {
                                p.setCoincidence(true);
                            }
                        }
                    }
                    for (Product p: products) {
                        if (!p.isCoincidence()) statement.executeUpdate("INSERT statofeveryorderfromwb(cdate, ctime, csubject, supplierArticle, nmId, finishedPrice, forPay, oblastOkrugName, odid) VALUES ('" + p.getCdate() + "', '" + p.getCtime() + "', '" + p.getCsubject() + "', '"  + p.getSupplierArticle() + "', " + p.getNmId() + ", " + p.getFinishedPrice() + ", " + p.getForPay() + ", '" + p.getOblastOkrugName() + "', '" + p.getOdid() + "')");
//                        statement.executeUpdate("INSERT statofeveryorderfromwb(cdate, ctime, csubject, supplierArticle, nmId, finishedPrice, forPay, oblastOkrugName, odid) VALUES ('" + p.getCdate() + "', '" + p.getCtime() + "', '" + p.getCsubject() + "', '"  + p.getSupplierArticle() + "', " + p.getNmId() + ", " + p.getFinishedPrice() + ", " + p.getForPay() + ", '" + p.getOblastOkrugName() + "', '" + p.getOdid() + "')");

                    }
                }
                if (mode.equals("salesozon")) {

                    ResultSet resultSet = statement.executeQuery("SELECT * FROM statofeveryorderfromozon");
                    while (resultSet.next()) {
                        odid = resultSet.getString("odid");
                        for (Product p: products) {
                            if (odid.equals(p.getOdid())) {
                                p.setCoincidence(true);
                            }
                        }
                    }
                    for (Product p: products) {
                        if (!p.isCoincidence()) statement.executeUpdate("INSERT statofeveryorderfromozon(cdate, ctime, csubject, supplierArticle, nmId, finishedPrice, forPay, oblastOkrugName, odid) VALUES ('" + p.getCdate() + "', '" + p.getCtime() + "', '" + p.getCsubject() + "', '"  + p.getSupplierArticle() + "', " + p.getNmId() + ", " + p.getFinishedPrice() + ", " + p.getForPay() + ", '" + p.getOblastOkrugName() + "', '" + p.getOdid() + "')");
//                        statement.executeUpdate("INSERT statofeveryorderfromozon(cdate, ctime, csubject, supplierArticle, nmId, finishedPrice, forPay, oblastOkrugName, odid) VALUES ('" + p.getCdate() + "', '" + p.getCtime() + "', '" + p.getCsubject() + "', '"  + p.getSupplierArticle() + "', " + p.getNmId() + ", " + p.getFinishedPrice() + ", " + p.getForPay() + ", '" + p.getOblastOkrugName() + "', '" + p.getOdid() + "')");

                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void upDate1(ArrayList<Item> items, String mode) {
        System.out.println("HERE");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = getConnection()) {
                int nmId = 0;
                Statement statement = conn.createStatement();
                if (mode.equals("wb")) {
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM itemcostpricewb");
                    while (resultSet.next()) {
                        nmId = resultSet.getInt("nmId");
                        for (Item i: items) {
                            if (nmId == i.getNmId()) {
                                i.setCoincidence(true);
                            }
                        }
                    }
                    for (Item i: items) {
//                        System.out.println(i.getSupplierArticle());
                        if (!i.isCoincidence()) statement.executeUpdate("INSERT itemcostpricewb(subject, supplierArticle, costprice, nmId) VALUES ('" + i.getSubject() + "', '" + i.getSupplierArticle() + "', 0, " + i.getNmId());
//                        statement.executeUpdate("INSERT itemcostpricewb(subject, supplierArticle, costprice, nmId) VALUES ('" + i.getSubject() + "', '" + i.getSupplierArticle() + "', 0, " + i.getNmId() + ")");
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
