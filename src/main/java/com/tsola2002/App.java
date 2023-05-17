package com.tsola2002;

//import com.tsola2002.domain.Message;
import com.tsola2002.entity.Guide;
import com.tsola2002.entity.Message;
import com.tsola2002.entity.Student;
import com.tsola2002.util.HibernateUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class App
{
    public static void main( String[] args )
    {



        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction txn =  session.getTransaction();

        try{
            txn.begin();

            //we create 2 guides
            Guide guide1 = new Guide("2000MO10789", "BOOTSTRAP", 1000);
            Guide guide2 = new Guide("2000IM10901", "REPONSIVE WEB DESIGN", 2000);

            //we create 2 students
            Student student1 = new Student("2014JT50123", "John Smith", guide1);
            Student student2 = new Student("2014AL50456", "Amy Gill", guide1);

            // we associate both students to the first guide
            guide1.getStudents().add(student1);
            guide1.getStudents().add(student2);

            // we persist both guide objects using the persist method of the session object
            session.persist(guide1);
            session.persist(guide2);

            txn.commit();

        } catch(Exception e) {
            if(txn!=null) {
                txn.rollback();
            }
            e.printStackTrace();
        } finally {
            if(session !=null) {
                session.close();
            }
        }


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db_school");
        EntityManager em = emf.createEntityManager();
        EntityTransaction ten = em.getTransaction();

        try{
            ten.begin();

            //QUERYING ENTITIES
            Query query = em.createQuery("select guide from Guide as guide");
            List<Guide> guides = query.getResultList();

            for (Guide guide : guides) {
                System.out.println(guide);
            }

            ten.commit();

        } catch (Exception e) {
            if (ten != null) {
                ten.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }


//        session.beginTransaction();
//
//        Message message = new Message( "Hello World with Hibernate and JPA Annotations with domain knowledge" );
//
//        session.save(message);
//
//        session.getTransaction().commit();
//        session.close();

    }
}
