import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import jdk.nashorn.internal.parser.Token;
import org.json.JSONArray;
import org.json.JSONObject;

import static java.lang.Integer.parseInt;

public class Update extends Thread {

    private final String TOKENWBSTANDART = "TOKENWBSTANDART";
    private final String TOKENWBSTATISTIC = "TOKENWBSTATISTIC";
    private final String TOKENWBADVERTISING = "TOKENWBADVERTISING";
    private final String TOKENOZON2 = "TOKENOZON2";
    private final String TOKENOZON1 = "TOKENOZON1";

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                update();
                sleep(1800000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        URL generetedURL;
        String response = null;

        generetedURL = URLRequestResponse.generateURL(2, 6, TOKENWBSTATISTIC);
        try {
            response = URLRequestResponse.getResponseFromURL(generetedURL, TOKENWBSTATISTIC);
            System.out.println(response);

            if (!response.equals("{\"errors\":[\"(api-new) too many requests\"]}")) {
                ArrayList<Product> products = new ArrayList<>();
                JSONObject jsonObject = new JSONObject("{\"price\":" + response + "}");
                for (int i = 0; i < jsonObject.getJSONArray("price").length(); i++) {
                    boolean coincidence = false;
                    String s = jsonObject.getJSONArray("price").getJSONObject(i).get("saleID").toString();
                    if (products.isEmpty()) {
                        products.add(new Product(jsonObject.getJSONArray("price").getJSONObject(i).get("date").toString(),
                                jsonObject.getJSONArray("price").getJSONObject(i).get("subject").toString(),
                                jsonObject.getJSONArray("price").getJSONObject(i).get("supplierArticle").toString(),
                                Integer.parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("nmId").toString()),
                                (int) (Float.parseFloat(jsonObject.getJSONArray("price").getJSONObject(i).get("finishedPrice").toString())),
                                (int) (Float.parseFloat(jsonObject.getJSONArray("price").getJSONObject(i).get("forPay").toString())),
                                jsonObject.getJSONArray("price").getJSONObject(i).get("oblastOkrugName").toString(),
                                jsonObject.getJSONArray("price").getJSONObject(i).get("odid").toString()));
                    } else {
                        for (Product product : products) {
                            if (product.getOdid().equals(jsonObject.getJSONArray("price").getJSONObject(i).get("odid").toString())) {
                                coincidence = true;
                            }
                        }
                        if (!coincidence) {
                            products.add(new Product(jsonObject.getJSONArray("price").getJSONObject(i).get("date").toString(),
                                    jsonObject.getJSONArray("price").getJSONObject(i).get("subject").toString(),
                                    jsonObject.getJSONArray("price").getJSONObject(i).get("supplierArticle").toString(),
                                    Integer.parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("nmId").toString()),
                                    (int) (Float.parseFloat(jsonObject.getJSONArray("price").getJSONObject(i).get("finishedPrice").toString())),
                                    (int) (Float.parseFloat(jsonObject.getJSONArray("price").getJSONObject(i).get("forPay").toString())),
                                    jsonObject.getJSONArray("price").getJSONObject(i).get("oblastOkrugName").toString(),
                                    jsonObject.getJSONArray("price").getJSONObject(i).get("odid").toString()));
                        }
                    }
                }
                SQL.upDate(products, "sales");
            }

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        generetedURL = URLRequestResponse.generateURL(2, 7, TOKENWBSTATISTIC);
        try {
            response = URLRequestResponse.getResponseFromURL(generetedURL, TOKENWBSTATISTIC);
            System.out.println(response);

            if (!response.equals("{\"errors\":[\"(api-new) too many requests\"]}")) {
                ArrayList<Product> products = new ArrayList<>();
                JSONObject jsonObject = new JSONObject("{\"price\":" + response + "}");
                for (int i = 0; i < jsonObject.getJSONArray("price").length(); i++) {
                    boolean coincidence = false;
                    String s = jsonObject.getJSONArray("price").getJSONObject(i).get("odid").toString();
                    if (products.isEmpty()) {
                        products.add(new Product(jsonObject.getJSONArray("price").getJSONObject(i).get("date").toString(),
                                jsonObject.getJSONArray("price").getJSONObject(i).get("subject").toString(),
                                jsonObject.getJSONArray("price").getJSONObject(i).get("supplierArticle").toString(),
                                Integer.parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("nmId").toString()),
                                jsonObject.getJSONArray("price").getJSONObject(i).get("odid").toString()));
                    } else {
                        for (Product product : products) {
                            if (product.getOdid().equals(jsonObject.getJSONArray("price").getJSONObject(i).get("odid").toString())) {
                                coincidence = true;
                            }
                        }
                        if (!coincidence) {
                            products.add(new Product(jsonObject.getJSONArray("price").getJSONObject(i).get("date").toString(),
                                    jsonObject.getJSONArray("price").getJSONObject(i).get("subject").toString(),
                                    jsonObject.getJSONArray("price").getJSONObject(i).get("supplierArticle").toString(),
                                    Integer.parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("nmId").toString()),
                                    jsonObject.getJSONArray("price").getJSONObject(i).get("odid").toString()));
                        }
                    }
                }
                SQL.upDate(products, "orders");
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        ArrayList<Item> stock = new ArrayList<>();
//
//        generetedURL = URLRequestResponse.generateURL(2, 5, TOKENWB1);
//        try {
//            response = URLRequestResponse.getResponseFromURL(generetedURL);
//            if (!response.equals("{\"errors\":[\"(api-new) too many requests\"]}")) {
//                JSONObject jsonObject = new JSONObject("{\"price\":" + response + "}");
//                for (int i = 0; i < jsonObject.getJSONArray("price").length(); i++) {
//                    boolean coincidence = false;
//                    if (stock.isEmpty()) {
//                        stock.add(new Item(jsonObject.getJSONArray("price").getJSONObject(i).get("supplierArticle").toString(),
//                                parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantity").toString()),
//                                parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantityFull").toString()),
//                                parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("nmId").toString()),
//                                jsonObject.getJSONArray("price").getJSONObject(i).get("subject").toString()));
//                    } else {
//                        for (Item productCurrent : stock) {
//                            if (productCurrent.getNmId() == parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("nmId").toString())) {
//                                productCurrent.setQuantity(productCurrent.getQuantity() + parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantity").toString()));
//                                productCurrent.setQuantityFull(productCurrent.getQuantityFull() + parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantityFull").toString()));
//                                coincidence = true;
//                            }
//                        }
//                        if (!coincidence) {
//                            stock.add(new Item(jsonObject.getJSONArray("price").getJSONObject(i).get("supplierArticle").toString(),
//                                    parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantity").toString()),
//                                    parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantityFull").toString()),
//                                    parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("nmId").toString()),
//                                    jsonObject.getJSONArray("price").getJSONObject(i).get("subject").toString()));
//                        }
//                        coincidence = false;
//                    }
//                }
//            }
//        } catch (IOException | URISyntaxException e) {
//            e.printStackTrace();
//        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        generetedURL = URLRequestResponse.generateURL(2, 1, TOKENWB2);
//        try {
//            response = URLRequestResponse.getResponseFromURL(generetedURL, TOKENWB2);
//            if (!response.equals("{\"errors\":[\"(api-new) too many requests\"]}")) {
//                System.out.println(response);
//                JSONObject jsonObject = new JSONObject("{\"price\":" + response + "}");
//                for (int i = 0; i < jsonObject.getJSONArray("price").length(); i++) {
//                    for (Item productCurrent : stock) {
//                        if (productCurrent.getNmId() == parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("nmId").toString())) {
//                            productCurrent.setPrice(parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("price").toString()));
//                            productCurrent.setDiscount(parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("discount").toString()));
//                            productCurrent.setPromoCode(parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("promoCode").toString()));
//                        }
//                    }
//                }
//            }
//        } catch (IOException | URISyntaxException e) {
//            e.printStackTrace();
//        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        generetedURL = URLRequestResponse.generateURL(3, 3, "0");
        try {
            response = URLRequestResponse.getResponseFromURL(generetedURL, TOKENOZON1, TOKENOZON2, 3, "0", "0");

            ArrayList<Product> products = new ArrayList<>();

            System.out.println(response);

            String answerString = "";

            JSONObject jsonObject5 = new JSONObject(response);
            for (int i = 0; i < jsonObject5.getJSONArray("result").length(); i++) {
                JSONArray jsonArray = (JSONArray) jsonObject5.getJSONArray("result").getJSONObject(i).get("products");
                JSONObject jsonObject6 = new JSONObject(String.valueOf(jsonObject5.getJSONArray("result").getJSONObject(i).get("analytics_data")));
                Product product = new Product(jsonObject5.getJSONArray("result").getJSONObject(i).get("created_at").toString(),
                        jsonArray.getJSONObject(0).get("name").toString(),
                        jsonArray.getJSONObject(0).get("sku").toString(),
                        0,
                        (int) (Float.parseFloat(jsonArray.getJSONObject(0).get("price").toString())),
                        (int) (Float.parseFloat(jsonArray.getJSONObject(0).get("price").toString()) * 0.88),
                        jsonObject6.getString("region") + " (" + jsonObject6.getString("city") + ")",
                        jsonArray.getJSONObject(0).get("order_id").toString());
                answerString = answerString
                        + "\n"
                        + "Наименование: "
                        + jsonArray.getJSONObject(0).get("name").toString()
                        + "\n"
                        + "Артикул: "
                        + jsonArray.getJSONObject(0).get("offer_id").toString()
                        + "\n"
                        + "Количество: "
                        + jsonArray.getJSONObject(0).get("quantity").toString()
                        + "\n"
                        + "Цена: "
                        + jsonArray.getJSONObject(0).get("price").toString()
                        + "\n"
                        + "Склад отгрузки: "
                        + jsonObject6.getString("warehouse_name")
                        + "\n"
                        + "Регион доставки: "
                        + jsonObject6.getString("region") + " (" + jsonObject6.getString("city") + ")"
                        + "\n"
                        + "Дата: "
                        + jsonObject5.getJSONArray("result").getJSONObject(i).get("created_at").toString()
                        + "\n";

                products.add(product);
            }

            if (!products.isEmpty()) SQL.upDate(products, "salesozon");

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }


    }
}

