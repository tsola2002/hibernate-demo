package com.tsola2002;

//import com.tsola2002.domain.Message;
import com.tsola2002.entity.Guide;
import com.tsola2002.entity.Message;
import com.tsola2002.entity.Student;
import com.tsola2002.util.HibernateUtil;
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
