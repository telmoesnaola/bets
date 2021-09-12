package domain;
import java.io.Serializable;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)   
@Entity
public class CuentaAhorro implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer cuentaAhorroNumber;
	private Cuenta usuario;
	private float fondos;
	private String nombreCuenta;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Apuesta> apuestas=new Vector<Apuesta>(); 
	
	public CuentaAhorro (Cuenta usuario, String nombre) {
		super();
		this.usuario=usuario;
		this.nombreCuenta=nombre;
	}
	
	public Integer getCuentaAhorroNumber() {
		return cuentaAhorroNumber;
	}

	public void setCuentaAhorroNumber(Integer cuentaAhorroNumber) {
		this.cuentaAhorroNumber = cuentaAhorroNumber;
	}

	public Cuenta getUsuario() {
		return usuario;
	}

	public void setUsuario(Cuenta usuario) {
		this.usuario = usuario;
	}

	public float getFondos() {
		return fondos;
	}

	public void setFondos(float fondos) {
		this.fondos = fondos;
	}

	public Vector<Apuesta> getApuestas() {
		return apuestas;
	}

	public void setApuestas(Vector<Apuesta> apuestas) {
		this.apuestas = apuestas;
	}

	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	public void addApuesta(Apuesta a) {
		apuestas.add(a);
		
	}
	
	
}
