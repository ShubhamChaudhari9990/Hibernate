package com.hibernate.Mapping;

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
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		System.out.println("Session Created");
		
		Employee e1 = new Employee();
		e1.setName("Shree");
		e1.setEmail("shree@gmail.com");
		
		Address address1 = new Address();
		address1.setAddressLine1("Katraj");
		address1.setCity("Pune");
		address1.setCountry("IND");
		address1.setState("MH");
		address1.setPincode(411041);
		
		e1.setAddress(address1);
	
		address1.setEmployee(e1);
		
		//session.save(e1);
		
		TypedQuery querry = session.createQuery("from Employee");
		java.util.List<Employee> list = querry.getResultList();
		Iterator<Employee> itr = list.iterator();
		while(itr.hasNext())
		{
			Employee emp = itr.next();
			System.out.println(emp.getEmpid()+" "+emp.getName()+" "+emp.getEmail());
			
			Address a = emp.getAddress();
			System.out.println(a.getAddressLine1()+" "+a.getCity()+" "+a.getState()+" "+a.getCountry()+" "+a.getPincode());
		}
		t.commit();
		session.close();
    }
}
