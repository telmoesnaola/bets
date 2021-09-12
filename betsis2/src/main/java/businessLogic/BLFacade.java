package businessLogic;

import java.util.Vector;
import java.util.Date;





//import domain.Booking;
import domain.Question;
import domain.Apuesta;
import domain.Cuenta;
import domain.CuentaAhorro;
import domain.Event;
import domain.Pronostico;
import exceptions.EventFinished;
import exceptions.PronosticoAlreadyExist;
import exceptions.QuestionAlreadyExist;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic. Hola esto e
 */
@WebService
public interface BLFacade  {
	  

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
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	@WebMethod public Event createEvent(String title, Date eventDate) throws EventFinished;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
	@WebMethod public boolean hacerLogin(String login, String password);
	
	@WebMethod public boolean isAdmin(String login);
	
	@WebMethod public boolean registrar(String login, String password, String nombre, String apellido, String mail);
	
	@WebMethod public Pronostico createPronostico(int n, String pronostico, float cuota) throws PronosticoAlreadyExist;

	@WebMethod public Cuenta getCuenta(String l);
	
	@WebMethod public Event getEvent(int id);

	@WebMethod public Question getQuestion(int questionNum);

	@WebMethod public Vector<Pronostico> getPronosticos(int questionNum);

	@WebMethod public Apuesta createApuesta(Pronostico p, float f, Event e, Question q, Cuenta usuario, CuentaAhorro c);

	@WebMethod public void restarFondos(CuentaAhorro c1, float f);

	@WebMethod public void cerrarPregunta(Pronostico p, Event e, Question q);

	@WebMethod public void cerrarEvento(Event e);

	@WebMethod public void crearCuentaAhorro(Cuenta u, String s);

	@WebMethod public Vector<CuentaAhorro> getCuentaAhorro(Cuenta u);

	@WebMethod public float verGanancias(CuentaAhorro c1);

	

	
}
