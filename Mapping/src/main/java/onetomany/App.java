package onetomany;

import java.util.Iterator;

import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import antlr.collections.List;

public class App 
{
    public static void main( String[] args )
    {
    	StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		System.out.println("Meta Data");

    	Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		System.out.println("Session Created");
		
		Book b = new Book();
		b.setBname("Java");
		b.setAuthor("James Gosling");
		b.setPrice(350.0);
		
		Book b1 = new Book();
		b1.setBname("Testing");
		b1.setAuthor("xyz");
		b1.setPrice(250.0);
		
		Library l1 = new Library();
		l1.setName("WorldBooks");
		
		b.setLibrary(l1);
		b1.setLibrary(l1);
		session.save(b);
//		session.save(l1);
		session.save(b1);
//		TypedQuery querry = session.createQuery("from Employee");//Employee It's a class name not a database name
//		java.util.List<Library> list = querry.getResultList();
//		Iterator<Library> itr = list.iterator();
//		while(itr.hasNext())
//		{
//			Library emp = itr.next();
//			System.out.println(emp.getId()+" "+emp.getName()+" ");
//			
//			Book b1 = emp.getBook();
//			System.out.println(b1.getBid()+" "+b1.getBname()+" "+b1.getAuthor()+" "+b1.getPrice()+" ");
//		}
		t.commit();
		session.close();
    }
}
