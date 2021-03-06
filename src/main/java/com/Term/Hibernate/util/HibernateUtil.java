package com.Term.Hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static StandardServiceRegistry standardServiceRegistry;
	private static SessionFactory sessionFactory;
	static {
		if (sessionFactory == null) {
			try {
				standardServiceRegistry = new StandardServiceRegistryBuilder().configure("com/Term/xml/Hibernate.cfg.xml").build();
				MetadataSources metadatasources = new MetadataSources(standardServiceRegistry);
				Metadata metadata = metadatasources.getMetadataBuilder().build();
				sessionFactory = metadata.getSessionFactoryBuilder().build();
			} catch (Exception e) {
				e.printStackTrace();
				if (standardServiceRegistry != null) {
					StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
				}
			}
		}

	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
