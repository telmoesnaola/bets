package domain;

import java.io.*;
import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
public class Apuesta implements Serializable{
	
	@Id
	@GeneratedValue
	private Integer apuestaNumber;
	private float cantidad;
	private Event evento;
	private Question pregunta;
	private Cuenta usuario;
	private Pronostico pronostico;
	private boolean cerrada=false;
	private CuentaAhorro cuentaAhorro;
	
	private boolean ganada;
	
	public Apuesta(Pronostico pronostico, float cantidad, Event evento, Question pregunta, Cuenta usuario, CuentaAhorro c) {
		super();
		this.pronostico=pronostico;
		this.cantidad=cantidad;
		this.evento=evento;
		this.pregunta=pregunta;
		this.usuario=usuario;
		this.cuentaAhorro=c;
	}

	public Integer getApuestaNumber() {
		return apuestaNumber;
	}

	public void setApuestaNumber(Integer apuestaNumber) {
		this.apuestaNumber = apuestaNumber;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public Event getEvento() {
		return evento;
	}

	public void setEvento(Event evento) {
		this.evento = evento;
	}

	public Question getPregunta() {
		return pregunta;
	}

	public void setPregunta(Question pregunta) {
		this.pregunta = pregunta;
	}

	public Cuenta getUsuario() {
		return usuario;
	}

	public void setUsuario(Cuenta usuario) {
		this.usuario = usuario;
	}

	public Pronostico getPronostico() {
		return pronostico;
	}

	public void setPronostico(Pronostico pronostico) {
		this.pronostico = pronostico;
	}
	
	public boolean isCerrada() {
		return cerrada;
	}

	public void setCerrada(boolean cerrada) {
		this.cerrada = cerrada;
	}

	public boolean isGanada() {
		return ganada;
	}

	public void setGanada(boolean ganada) {
		this.ganada = ganada;
	}

	public CuentaAhorro getCuentaAhorro() {
		return cuentaAhorro;
	}

	public void setCuentaAhorro(CuentaAhorro cuentaAhorro) {
		this.cuentaAhorro = cuentaAhorro;
	}
	
	
	
	
}
