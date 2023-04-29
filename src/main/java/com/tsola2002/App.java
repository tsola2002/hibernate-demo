package com.tsola2002;

//import com.tsola2002.domain.Message;
import com.tsola2002.entity.Message;
import com.tsola2002.util.HibernateUtil;
import org.hibernate.Session;

public class App
{
    public static void main( String[] args )
    {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Message message = new Message( "Hello World with Hibernate and JPA Annotations with domain knowledge" );

        session.save(message);

        session.getTransaction().commit();
        session.close();

    }
}
