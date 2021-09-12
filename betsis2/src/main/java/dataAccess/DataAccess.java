package dataAccess;

//hello
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Apuesta;
import domain.Cuenta;
import domain.CuentaAhorro;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import exceptions.EventAlreadyExist;
import exceptions.PronosticoAlreadyExist;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

	public DataAccess(boolean initializeMode)  {

		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);

	}

	public DataAccess()  {	
		new DataAccess(false);
	}


	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){

		db.getTransaction().begin();
		try {


			Calendar today = Calendar.getInstance();

			int month=today.get(Calendar.MONTH);
			month+=1;
			int year=today.get(Calendar.YEAR);
			if (month==12) { month=0; year+=1;}  

			Cuenta c1 = new Cuenta("admin", "admin", true);
			Cuenta c2 = new Cuenta("usuario", "1234", false);


			db.persist(c1);
			db.persist(c2);

			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));


			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month+1,28));
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month+1,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month+1,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month+1,28));




			Event ev21=new Event(21, "Real Madrid-Real Sociedad", UtilDate.newDate(year,month,3));
			Event ev22=new Event(22, "Valencia-Villareal", UtilDate.newDate(year,month,5));
			Event ev23=new Event(23, "Valladolid-Getafe", UtilDate.newDate(year,month,6));
			Event ev24=new Event(24, "Elche-Sevilla", UtilDate.newDate(year,month,6));
			Event ev25=new Event(25, "Cadiz-Eibar", UtilDate.newDate(year,month,6));
			Event ev26=new Event(26, "Osasuna-Barcelona", UtilDate.newDate(year,month,6));
			Event ev27=new Event(27, "Huesca-Celta de Vigo", UtilDate.newDate(year,month,7));
			Event ev28=new Event(28, "Atlético Madrid-Real Madrid", UtilDate.newDate(year,month,7));
			Event ev29=new Event(29, "Real Sociedad-Levante", UtilDate.newDate(year,month,7));
			Event ev30=new Event(30, "Ath. Bilbao-Granada", UtilDate.newDate(year,month,7));
			Event ev31=new Event(31, "Betis-Alaves", UtilDate.newDate(year,month,8));


			Event ev32=new Event(32, "Atlético Madrid-Ath.Bilbao", UtilDate.newDate(year,month,10));

			Event ev33=new Event(33, "Levante-Valencia", UtilDate.newDate(year,month,12));
			Event ev34=new Event(34, "Alavés-Cádiz", UtilDate.newDate(year,month,13));
			Event ev35=new Event(35, "Real Madrid-Elche C.F", UtilDate.newDate(year,month,13));
			Event ev36=new Event(36, "Osasuna-Valladolid", UtilDate.newDate(year,month,13));
			Event ev37=new Event(37, "Getafe-Atlético Madrid", UtilDate.newDate(year,month,13));
			Event ev38=new Event(38, "Celta de Vigo-Ath. Bilbao", UtilDate.newDate(year,month,14));
			Event ev39=new Event(39, "Granada-Real Sociedad", UtilDate.newDate(year,month,14));
			Event ev40=new Event(40, "Eibar-Villareal", UtilDate.newDate(year,month,14));
			Event ev41=new Event(41, "Sevilla-Betis", UtilDate.newDate(year,month,14));
			Event ev42=new Event(42, "Barcelona-Huesca", UtilDate.newDate(year,month,15));

			Event ev43=new Event(43, "Sevilla-Elche", UtilDate.newDate(year,month,17));

			Event ev44=new Event(44, "Betis-Levante", UtilDate.newDate(year,month,19));
			Event ev45=new Event(45, "Ath.Bilbao-Eibar", UtilDate.newDate(year,month,20));
			Event ev46=new Event(46, "Celta de Vigo-Real Madrid", UtilDate.newDate(year,month,20));
			Event ev47=new Event(47, "Huesca-Osasuna", UtilDate.newDate(year,month,20));
			Event ev48=new Event(48, "Valladolid-Sevilla", UtilDate.newDate(year,month,20));
			Event ev49=new Event(49, "Getafe-Elche", UtilDate.newDate(year,month,21));
			Event ev50=new Event(50, "Valencia-Granada", UtilDate.newDate(year,month,21));
			Event ev51=new Event(51, "Villareal-Cádiz", UtilDate.newDate(year,month,21));
			Event ev52=new Event(52, "Atlético Madrid-Alavés", UtilDate.newDate(year,month,21));
			Event ev53=new Event(53, "Real Sociedad-Barcelona", UtilDate.newDate(year,month,21));




			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);

			}

			Pronostico p1=q3.addPronostico("Atletico", 1);
			Pronostico p2=q3.addPronostico("Athletic", 3);

			Pronostico p3=q4.addPronostico("0", 2);
			Pronostico p4=q4.addPronostico("+2", 4);

			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6); 


			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);
			db.persist(ev21);
			db.persist(ev22);
			db.persist(ev23);
			db.persist(ev24);
			db.persist(ev25);
			db.persist(ev26);
			db.persist(ev27);
			db.persist(ev28);
			db.persist(ev29);
			db.persist(ev30);
			db.persist(ev31);
			db.persist(ev32);
			db.persist(ev33);
			db.persist(ev34);
			db.persist(ev35);
			db.persist(ev36);
			db.persist(ev37);
			db.persist(ev38);
			db.persist(ev39);
			db.persist(ev40);
			db.persist(ev41);
			db.persist(ev42);
			db.persist(ev43);
			db.persist(ev44);
			db.persist(ev45);
			db.persist(ev46);
			db.persist(ev47);
			db.persist(ev48);
			db.persist(ev49);
			db.persist(ev50);
			db.persist(ev51);
			db.persist(ev52);
			db.persist(ev53);

			db.persist(p1);
			db.persist(p2);
			db.persist(p3);
			db.persist(p4);


			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);

		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);
		//db.persist(q);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;

	}

	public Pronostico crearPronostico(int numPregunta, String pronostico, float cuota) throws PronosticoAlreadyExist {
		System.out.println(">> DataAccess: CreatePronostico=> "+pronostico);
		Question q = db.find(Question.class, numPregunta);

		if (q.DoesPronosticoExists(pronostico)) throw new PronosticoAlreadyExist("EL pronostico ya existe");

		db.getTransaction().begin();
		Pronostico p = q.addPronostico(pronostico, cuota);

		db.persist(p);
		db.persist(q);
		db.getTransaction().commit();
		return p;
	}

	public Event createEvent(String description, Date eventDate) {
		System.out.println(">> DataAccess: createEvent=> event= "+description);



		db.getTransaction().begin();
		Event ev = new Event(description, eventDate);
		//db.persist(q);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return ev;

	}

	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
		for (Event ev:events){
			System.out.println(ev.toString());		 
			res.add(ev);
		}
		return res;
	}

	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	

		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);


		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d:dates){
			System.out.println(d.toString());		 
			res.add(d);
		}
		return res;
	}


	public void open(boolean initializeMode){

		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			db = emf.createEntityManager();
		}

	}
	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.DoesQuestionExists(question);

	}

	public boolean hacerLogin(String login, String password) {
		db.getTransaction().begin();
		Cuenta c = db.find(Cuenta.class, login);
		if (c==null) return false;
		return (c.getPassword().equals(password));
	}

	public boolean esAdmin(String usuario) {
		db.getTransaction().begin();
		Cuenta c = db.find(Cuenta.class, usuario);
		return c.isAdmin();
	}


	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean registrar(String login, String password, String nombre, String apellido, String mail) {
		if (db.find(Cuenta.class, login)!=null) return false;
		db.getTransaction().begin();
		Cuenta c = new Cuenta(login, password, nombre, apellido, mail);
		db.persist(c);
		db.getTransaction().commit();
		return true;
	}

	public Cuenta getCuenta(String l) {
		db.getTransaction().begin();
		Cuenta c = db.find(Cuenta.class, l);

		return c;
	}

	public Event getEvent(int id) {
		db.getTransaction().begin();
		Event e = db.find(Event.class, id);
		return e;
	}

	public Question getQuestion(int questionNum) {
		db.getTransaction().begin();
		Question q = db.find(Question.class, questionNum);
		return q;
	}

	public Vector<Pronostico> getPronosticos(int questionNum) {
		db.getTransaction().begin();
		Vector<Pronostico> p = new Vector<Pronostico>();
		Question q = db.find(Question.class, questionNum);
		p = q.getPronosticos();
		return p;
	}

	public Apuesta createApuesta(Pronostico p, float f, Event e, Question q, Cuenta usuario, CuentaAhorro c) {
		Question q1 = db.find(Question.class, q.getQuestionNumber());
		CuentaAhorro c1 = db.find(CuentaAhorro.class, c.getCuentaAhorroNumber());
		db.getTransaction().begin();
		Apuesta a = q1.addApuesta(p, f, e, q1, usuario, c);
		c1.addApuesta(a);
		db.persist(a);
		db.persist(q1);
		db.persist(c1);
		db.getTransaction().commit();
		return a;
	}

	public void restarFondos(CuentaAhorro c, float f) {
		CuentaAhorro c1=db.find(CuentaAhorro.class, c.getCuentaAhorroNumber());
		db.getTransaction().begin();
		c1.setFondos(c1.getFondos()-f);
		db.persist(c1);
		db.getTransaction().commit();
	}

	public void cerrarPregunta(Pronostico p, Event e, Question q) {
		Question q1=db.find(Question.class, q.getQuestionNumber());
		db.getTransaction().begin();
		q1.setPronosticoFinal(p);
		String r1= p.toString();
		Vector<Apuesta> apuestas=q1.getApuestas();
		db.persist(q1);

		for (Apuesta a: apuestas) {
			Apuesta a1 = db.find(Apuesta.class, a.getApuestaNumber());
			String r = a1.getPronostico().toString();

			if (r1.equals(r)) {
				a1.setGanada(true);

			}

			a1.setCerrada(true);
			db.persist(a1);

		}

		q1.setCerrada(true);

		db.persist(q1);
		db.getTransaction().commit();
	}

	public void crearCuentaAhorro(Cuenta u, String s) {
		Cuenta u1 = db.find(Cuenta.class, u.getUsuario());
		db.getTransaction().begin();
		CuentaAhorro c = u1.addCuentaAhorro(u1, s);
		db.persist(c);
		db.persist(u1);
		db.getTransaction().commit();
	}

	public Vector<CuentaAhorro> getCuentaAhorro(Cuenta u) {
		db.getTransaction().begin();
		Cuenta u1 = db.find(Cuenta.class, u.getUsuario());
		Vector<CuentaAhorro> c = new Vector<CuentaAhorro>();

		c=u1.getCuentasAhorro();

		return c;
	}

	public void cerrarEvento(Event e) {
		Event e1 = db.find(Event.class, e.getEventNumber());
		db.getTransaction().begin();
		for(Question q: e1.getQuestions()) {
			for (Apuesta a: q.getApuestas()) {
				if(a.isGanada()) {
					CuentaAhorro c = a.getCuentaAhorro();
					c.setFondos(c.getFondos()+(a.getCantidad()*a.getPronostico().getCuota()));
					db.persist(c);
				}
			}
		}
		e1.setCerrado(true);
		db.persist(e1);
		db.getTransaction().commit();

	}

	public Float verGanancias(CuentaAhorro c1) {
		CuentaAhorro c = db.find(CuentaAhorro.class, c1.getCuentaAhorroNumber());
		Vector<Apuesta> apuestas=c.getApuestas();
		float f=0;
		db.getTransaction().begin();
		for (Apuesta a:apuestas) {
			if(a.isCerrada()) {
				if (a.isGanada()) {
					f=+(a.getCantidad()*a.getPronostico().getCuota())-a.getCantidad();
				}else {
					f=f-a.getCantidad();
				}
			}
		}
		return f;
	}



}



