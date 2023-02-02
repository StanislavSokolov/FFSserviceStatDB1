import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import jdk.nashorn.internal.parser.Token;
import org.json.JSONArray;
import org.json.JSONObject;

public class Update extends Thread {

    private final String TOKENWB1 = "token";

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

        generetedURL = URLRequestResponse.generateURL(2, 6, TOKENWB1);
        try {
            response = URLRequestResponse.getResponseFromURL(generetedURL, TOKENWB1);
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

        generetedURL = URLRequestResponse.generateURL(2, 7, TOKENWB1);
        try {
            response = URLRequestResponse.getResponseFromURL(generetedURL, TOKENWB1);
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

    }
}

