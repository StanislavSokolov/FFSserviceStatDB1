import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import static java.lang.Integer.parseInt;

public class Update extends Thread {

    private final String TOKENWBSTANDART = "TOKENWBSTANDART";
    private final String TOKENOZON1 = "TOKENOZON1";
    private final String TOKENOZON2 = "TOKENOZON2";


    @Override
    public void run() {

        int count = 0;

        super.run();
        while (true) {
            try {
                update(count);
                sleep(900000);
                if (count > 4) count = 0; else count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update(int count) {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        URL generetedURL;
        String response = null;

        if (count == 0) {
            generetedURL = URLRequestResponse.generateURL(2, 5, TOKENWBSTANDART);
            try {
                response = URLRequestResponse.getResponseFromURL(generetedURL, TOKENWBSTANDART);
                System.out.println(response);
                if (!response.equals("{\"errors\":[\"(api-new) too many requests\"]}")) {
                    ArrayList<Item> items = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject("{\"price\":" + response + "}");
                    for (int i = 0; i < jsonObject.getJSONArray("price").length(); i++) {
                        boolean coincidence = false;
                        if (items.isEmpty()) {
                            items.add(new Item(jsonObject.getJSONArray("price").getJSONObject(i).get("supplierArticle").toString(),
                                    parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantity").toString()),
                                    parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantityFull").toString()),
                                    parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("nmId").toString()),
                                    jsonObject.getJSONArray("price").getJSONObject(i).get("subject").toString(),
                                    jsonObject.getJSONArray("price").getJSONObject(i).get("warehouseName").toString()));
                        } else {
                            for (Item itemCurrent : items) {
                                if (itemCurrent.getNmId() == parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("nmId").toString())) {
                                    itemCurrent.setQuantity(itemCurrent.getQuantity() + parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantity").toString()));
                                    itemCurrent.setQuantityFull(itemCurrent.getQuantityFull() + parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantityFull").toString()));
                                    itemCurrent.addQuantityWarehouse(parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantity").toString()), jsonObject.getJSONArray("price").getJSONObject(i).get("warehouseName").toString());
                                    coincidence = true;
                                }
                            }
                            if (!coincidence) {
                                items.add(new Item(jsonObject.getJSONArray("price").getJSONObject(i).get("supplierArticle").toString(),
                                        parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantity").toString()),
                                        parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantityFull").toString()),
                                        parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("nmId").toString()),
                                        jsonObject.getJSONArray("price").getJSONObject(i).get("subject").toString(),
                                        jsonObject.getJSONArray("price").getJSONObject(i).get("warehouseName").toString()));
                            }
                            coincidence = false;
                        }
                    }

                    if (!items.isEmpty())  {
                        generetedURL = URLRequestResponse.generateURL(2, 1, TOKENWBSTANDART);
                        try {
                            response = URLRequestResponse.getResponseFromURL(generetedURL, TOKENWBSTANDART);
                            if (!response.equals("{\"errors\":[\"(api-new) too many requests\"]}")) {
                                System.out.println(response);
                                JSONObject jsonObject1 = new JSONObject("{\"price\":" + response + "}");
                                for (int i = 0; i < jsonObject1.getJSONArray("price").length(); i++) {
                                    for (Item itemCurrent : items) {
                                        if (itemCurrent.getNmId() == parseInt(jsonObject1.getJSONArray("price").getJSONObject(i).get("nmId").toString())) {
                                            itemCurrent.setPrice(parseInt(jsonObject1.getJSONArray("price").getJSONObject(i).get("price").toString()));
                                            itemCurrent.setDiscount(parseInt(jsonObject1.getJSONArray("price").getJSONObject(i).get("discount").toString()));
                                            itemCurrent.setPromoCode(parseInt(jsonObject1.getJSONArray("price").getJSONObject(i).get("promoCode").toString()));
                                        }
                                    }
                                }
                            }
                        } catch (IOException | URISyntaxException e) {
                            e.printStackTrace();
                        }
                        SQL.upDate1(items, "wb");
                    }
                }

            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            generetedURL = URLRequestResponse.generateURL(2, 6, TOKENWBSTANDART);
            try {
                response = URLRequestResponse.getResponseFromURL(generetedURL, TOKENWBSTANDART);
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
                                    jsonObject.getJSONArray("price").getJSONObject(i).get("warehouseName").toString(),
                                    jsonObject.getJSONArray("price").getJSONObject(i).get("regionName").toString(),
                                    jsonObject.getJSONArray("price").getJSONObject(i).get("gNumber").toString()));
                        } else {
                            for (Product product : products) {
                                if (product.getOdid().equals(jsonObject.getJSONArray("price").getJSONObject(i).get("gNumber").toString())) {
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
                                        jsonObject.getJSONArray("price").getJSONObject(i).get("warehouseName").toString(),
                                        jsonObject.getJSONArray("price").getJSONObject(i).get("regionName").toString(),
                                        jsonObject.getJSONArray("price").getJSONObject(i).get("gNumber").toString()));
                            }
                        }
                    }
                    SQL.upDate(products, "sales");
                }

            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            generetedURL = URLRequestResponse.generateURL(2, 7, TOKENWBSTANDART);
            try {
                response = URLRequestResponse.getResponseFromURL(generetedURL, TOKENWBSTANDART);
                System.out.println(response);

                if (!response.equals("{\"errors\":[\"(api-new) too many requests\"]}")) {
                    ArrayList<Product> products = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject("{\"price\":" + response + "}");
                    for (int i = 0; i < jsonObject.getJSONArray("price").length(); i++) {
                        boolean coincidence = false;
                        String s = jsonObject.getJSONArray("price").getJSONObject(i).get("gNumber").toString();
                        if (products.isEmpty()) {
                            products.add(new Product(jsonObject.getJSONArray("price").getJSONObject(i).get("date").toString(),
                                    jsonObject.getJSONArray("price").getJSONObject(i).get("subject").toString(),
                                    jsonObject.getJSONArray("price").getJSONObject(i).get("supplierArticle").toString(),
                                    Integer.parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("nmId").toString()),
                                    jsonObject.getJSONArray("price").getJSONObject(i).get("warehouseName").toString(),
                                    jsonObject.getJSONArray("price").getJSONObject(i).get("regionName").toString(),
                                    jsonObject.getJSONArray("price").getJSONObject(i).get("gNumber").toString()));
                        } else {
                            for (Product product : products) {
                                if (product.getOdid().equals(jsonObject.getJSONArray("price").getJSONObject(i).get("gNumber").toString())) {
                                    coincidence = true;
                                }
                            }
                            if (!coincidence) {
                                products.add(new Product(jsonObject.getJSONArray("price").getJSONObject(i).get("date").toString(),
                                        jsonObject.getJSONArray("price").getJSONObject(i).get("subject").toString(),
                                        jsonObject.getJSONArray("price").getJSONObject(i).get("supplierArticle").toString(),
                                        Integer.parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("nmId").toString()),
                                        jsonObject.getJSONArray("price").getJSONObject(i).get("warehouseName").toString(),
                                        jsonObject.getJSONArray("price").getJSONObject(i).get("regionName").toString(),
                                        jsonObject.getJSONArray("price").getJSONObject(i).get("gNumber").toString()));
                            }
                        }
                    }
                    SQL.upDate(products, "orders");
                }
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }

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
                        jsonArray.getJSONObject(0).get("offer_id").toString(),
                        Integer.parseInt(jsonArray.getJSONObject(0).get("sku").toString()),
                        (int) (Float.parseFloat(jsonArray.getJSONObject(0).get("price").toString())),
                        (int) (Float.parseFloat(jsonArray.getJSONObject(0).get("price").toString()) * 0.88),
                        "",
                        jsonObject6.getString("region") + " (" + jsonObject6.getString("city") + ")",
                        jsonObject5.getJSONArray("result").getJSONObject(i).get("order_id").toString());
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

