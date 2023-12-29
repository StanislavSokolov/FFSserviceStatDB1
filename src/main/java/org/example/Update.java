package org.example;

import org.example.com.Key;
import org.example.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Update extends Thread {

    @Override
    public void run() {
        int count = 0;

        super.run();
        while (true) {
            try {
                update();
                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {

        SessionFactory sessionFactory = new Configuration().addAnnotatedClass(User.class).
                addAnnotatedClass(QueueRequests.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            URL generetedURL = null;
            String response = null;
            List<QueueRequests> queueRequestsList = session.createQuery("FROM QueueRequests").getResultList();

            if (!queueRequestsList.isEmpty()) {
                QueueRequests queueRequest = queueRequestsList.get(0);
                User user = (User) session.createQuery("FROM User WHERE id LIKE '" + queueRequest.getClientId() + "'").getResultList().get(0);
                String token = "";
                if (queueRequest.getShop().equals("wb")) token = user.getTokenStandartWB();
                generetedURL = URLRequestResponse.generateURL(queueRequest.getShop(), queueRequest.getMethod(), token, null);
                ArrayList<Key> keys = new ArrayList<>();
                keys.add(new Key("nmId", queueRequest.getArticle()));
                keys.add(new Key("price", queueRequest.getDataToChange()));
                try {
                    response = URLRequestResponse.getResponseFromURLandBodyRequest(generetedURL, token, keys);
                    System.out.println(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                if (!response.equals("{\"errors\":[\"(api-new) too many requests\"]}")) {
//
//                }
                session.delete(queueRequest);
            }
            System.out.println("It is empty here");
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}