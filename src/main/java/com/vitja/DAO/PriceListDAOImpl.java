package com.vitja.DAO;

import com.vitja.model.PriceList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Viktor on 10.10.2016.
 */
public class PriceListDAOImpl  implements PriceListDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(PriceList priceList) {
        Session session = null;
        try {
            session = sessionFactory.openSession();

            Transaction transaction = session.beginTransaction();

            session.persist(priceList);

            transaction.commit();
        }catch (Exception e){

            e.printStackTrace();

        } finally {

            if(session != null){
                session.close();
            }

        }



    }
}
