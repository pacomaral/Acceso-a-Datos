import org.hibernate.Session;

public class Principal {

	public static void main(String[] args) {
		
		Session session=HibernateUtilities.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		//Creamos profesores
		Profesor p1 = new Profesor("Miguel", "Mañana");
		Profesor p2 = new Profesor("Marcos", "Tarde");
		
		//Creamos titulaciones
		Titulacion t1 = new Titulacion(1440, "Desarrollo móbil", "DAM");
		Titulacion t2 = new Titulacion(1400, "Desarrollo web", "DAW");
		
		//Creamos asignaturas con dos temas cada una 
		Asignatura a1 = new Asignatura();
		a1.setNombre("AAD");
		a1.setHoras(400);
		
		Tema tema1 = new Tema();
		tema1.setTitulo("Interfaces");
		tema1.setDuracion("140 horas");
		a1.getTemas().add(tema1);
		
		Tema tema2 = new Tema();
		tema2.setTitulo("BBDD");
		tema2.setDuracion("140 horas");
		a1.getTemas().add(tema2);
		
		Asignatura a2 = new Asignatura();
		a2.setHoras(500);
		a2.setNombre("PMDM");
		Tema tema3 = new Tema();
		tema3.setTitulo("Usabilidad");
		tema3.setDuracion("15 horas");
		a2.getTemas().add(tema3);
		
		Tema tema4 = new Tema();
		tema4.setTitulo("Redes");
		tema4.setDuracion("333 horas");
		a2.getTemas().add(tema4);
		
		
		//Guardamos instancias
		session.save(p1);
		session.save(p2);
		session.save(t1);
		session.save(t2);
		session.save(a1);
		session.save(a2);
		
		session.getTransaction().commit();
		
		//Cerramos la sesión
		session.close();
		
		HibernateUtilities.getSessionFactory().close();
		
		
	}

}