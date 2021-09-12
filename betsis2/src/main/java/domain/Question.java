package domain;

import java.io.*;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Question implements Serializable {
	
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer questionNumber;
	private String question; 
	private float betMinimum;
	private String result;  
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Pronostico> pronosticos = new Vector<Pronostico>();
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Apuesta> apuestas = new Vector<Apuesta>();
	private boolean cerrada;
	private Pronostico pronosticoFinal;
	@XmlIDREF
	private Event event;

	public Question(){
		super();
	}
	
	public Pronostico addPronostico(String pronostico, float cuota) {
		Pronostico p = new Pronostico(pronostico, cuota, this);
		this.pronosticos.add(p);
		return p;
	}
	
	public Question(Integer queryNumber, String query, float betMinimum, Event event) {
		super();
		this.questionNumber = queryNumber;
		this.question = query;
		this.betMinimum=betMinimum;
		this.event = event;
	}
	
	public Question(String query, float betMinimum,  Event event) {
		super();
		this.question = query;
		this.betMinimum=betMinimum;

		//this.event = event;
	}

	/**
	 * Get the  number of the question
	 * 
	 * @return the question number
	 */
	public Integer getQuestionNumber() {
		return questionNumber;
	}
	
	
	public Vector<Pronostico> getPronosticos() {
		return pronosticos;
	}

	public void setPronosticos(Vector<Pronostico> pronosticos) {
		this.pronosticos = pronosticos;
	}

	/**
	 * Set the bet number to a question
	 * 
	 * @param questionNumber to be setted
	 */
	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}


	/**
	 * Get the question description of the bet
	 * 
	 * @return the bet question
	 */

	public String getQuestion() {
		return question;
	}


	/**
	 * Set the question description of the bet
	 * 
	 * @param question to be setted
	 */	
	public void setQuestion(String question) {
		this.question = question;
	}



	/**
	 * Get the minimun ammount of the bet
	 * 
	 * @return the minimum bet ammount
	 */
	
	public float getBetMinimum() {
		return betMinimum;
	}


	/**
	 * Get the minimun ammount of the bet
	 * 
	 * @param  betMinimum minimum bet ammount to be setted
	 */

	public void setBetMinimum(float betMinimum) {
		this.betMinimum = betMinimum;
	}



	/**
	 * Get the result of the  query
	 * 
	 * @return the the query result
	 */
	public String getResult() {
		return result;
	}



	/**
	 * Get the result of the  query
	 * 
	 * @param result of the query to be setted
	 */
	
	public void setResult(String result) {
		this.result = result;
	}



	/**
	 * Get the event associated to the bet
	 * 
	 * @return the associated event
	 */
	public Event getEvent() {
		return event;
	}



	/**
	 * Set the event associated to the bet
	 * 
	 * @param event to associate to the bet
	 */
	public void setEvent(Event event) {
		this.event = event;
	}




	public String toString(){
		return questionNumber+";"+question+";"+Float.toString(betMinimum);
	}
	
	public boolean DoesPronosticoExists(String pronostico)  {	
//		for (String p:this.getPronosticos()){
//			if (p.compareTo(pronostico)==0)
//				return true;
//		}
		return false;
	}

	public boolean isCerrada() {
		return cerrada;
	}

	public void setCerrada(boolean cerrada) {
		this.cerrada = cerrada;
	}

	public Pronostico getPronosticoFinal() {
		return pronosticoFinal;
	}

	public void setPronosticoFinal(Pronostico pronosticoFinal) {
		this.pronosticoFinal = pronosticoFinal;
	}

	public Vector<Apuesta> getApuestas() {
		return apuestas;
	}

	public void setApuestas(Vector<Apuesta> apuestas) {
		this.apuestas = apuestas;
	}
	
	public Apuesta addApuesta(Pronostico p, float f, Event e, Question q, Cuenta usuario, CuentaAhorro c) {
		Apuesta a = new Apuesta(p, f, e, q, usuario, c);
		this.apuestas.add(a);
		return a;
	}
	



	
}