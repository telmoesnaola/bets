package businessLogic;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Question;
import domain.Apuesta;
import domain.Cuenta;
import domain.CuentaAhorro;
import domain.Event;
import domain.Pronostico;
import exceptions.EventFinished;
import exceptions.PronosticoAlreadyExist;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
		}

	}

	public BLFacadeImplementation(DataAccess da)  {

		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		dbManager=da;		
	}

	@WebMethod
	public Pronostico createPronostico(int numPregunta, String pronostico, float cuota) throws PronosticoAlreadyExist {

		
		dbManager.open(false);
		Pronostico s=null;

		s = dbManager.crearPronostico(numPregunta, pronostico, cuota);

		dbManager.close();

		return s;
	};
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	@WebMethod
	public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{

		//The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry=null;


		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));


		qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();

		return qry;
	};

	@WebMethod
	public Event createEvent(String title, Date eventDate) throws EventFinished{

		//The minimum bed must be greater than 0
		dbManager.open(false);
		Event ev=null;

		if(new Date().compareTo(eventDate)>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));


		ev=dbManager.createEvent(title, eventDate);		

		dbManager.close();

		return ev;
	};

	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod	
	public Vector<Event> getEvents(Date date)  {
		dbManager.open(false);
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}


	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}


	public void close() {
		DataAccess dB4oManager=new DataAccess(false);

		dB4oManager.close();

	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod	
	public void initializeBD(){
		dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}

	@WebMethod
	public boolean hacerLogin(String login, String password) {
		dbManager.open(false);

		boolean r  = dbManager.hacerLogin(login, password);

		dbManager.close();
		return r;

	}

	@WebMethod
	public boolean isAdmin(String login) {
		dbManager.open(false);
		boolean r = dbManager.esAdmin(login);
		dbManager.close();
		return r;
	}

	@WebMethod
	public boolean registrar(String login, String password, String nombre, String apellido, String mail) {
		dbManager.open(false);
		boolean r = dbManager.registrar(login, password, nombre, apellido, mail);
		dbManager.close();
		return r;
	}

	@WebMethod
	public Cuenta getCuenta(String l) {
		dbManager.open(false);
		Cuenta c = dbManager.getCuenta(l);
		dbManager.close();
		return c;
	}

	@WebMethod
	public Event getEvent(int id) {
		dbManager.open(false);
		Event e =dbManager.getEvent(id);
		dbManager.close();
		return e;
	}

	@WebMethod
	public Question getQuestion(int questionNum) {
		dbManager.open(false);
		Question q =dbManager.getQuestion(questionNum);
		dbManager.close();
		return q;
	}

	@Override
	public Vector<Pronostico> getPronosticos(int questionNum) {
		dbManager.open(false);
		Vector<Pronostico> p = new Vector<Pronostico>();
		p = dbManager.getPronosticos(questionNum);
		dbManager.close();
		return p;
	}

	@Override
	public Apuesta createApuesta(Pronostico p, float f, Event e, Question q, Cuenta usuario, CuentaAhorro c) {
		dbManager.open(false);
		Apuesta a = null;
		a=dbManager.createApuesta(p, f, e, q, usuario, c);
		dbManager.close();
		return a;
	}

	@Override
	public void restarFondos(CuentaAhorro c, float f) {
		dbManager.open(false);
		dbManager.restarFondos(c, f);
		dbManager.close();
	}

	@Override
	public void cerrarPregunta(Pronostico p, Event e, Question q) {
		dbManager.open(false);
		dbManager.cerrarPregunta(p, e, q);
		dbManager.close();
	}

	@Override
	public void cerrarEvento(Event e) {
		dbManager.open(false);
		dbManager.cerrarEvento(e);
		dbManager.close();
	}

	@Override
	public void crearCuentaAhorro(Cuenta u, String s) {
		dbManager.open(false);
		dbManager.crearCuentaAhorro(u, s);
		dbManager.close();
	}

	@Override
	public Vector<CuentaAhorro> getCuentaAhorro(Cuenta u) {
		dbManager.open(false);
		Vector<CuentaAhorro> c = new Vector<CuentaAhorro>();
		c=dbManager.getCuentaAhorro(u);
		dbManager.close();
		return c;
	}

	@Override
	public float verGanancias(CuentaAhorro c1) {
		dbManager.open(false);
		Float f = dbManager.verGanancias(c1);
		dbManager.close();
		return f;
	}

	


}

