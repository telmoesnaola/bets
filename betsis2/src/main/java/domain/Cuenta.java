package domain;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cuenta {
	@Id
	String usuario;
	String password;
	boolean admin;
	String nombre;
	String apellido;
	String mail;
//	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<CuentaAhorro> cuentasAhorro=new Vector<CuentaAhorro>(); 
	

	public Cuenta(String usuario, String password, Boolean admin) {
		this.admin=admin;
		this.password=password;
		this.usuario=usuario;
	}

	public Cuenta(String login, String password, String nombre, String apellido, String mail) {
		this.usuario=login;
		this.password=password;
		this.nombre=nombre;
		this.apellido=apellido;
		this.mail=mail;
		admin=false;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	
	
	public Vector<CuentaAhorro> getCuentasAhorro() {
		return cuentasAhorro;
	}

	public void setCuentasAhorro(Vector<CuentaAhorro> cuentasAhorro) {
		this.cuentasAhorro = cuentasAhorro;
	}

	public CuentaAhorro addCuentaAhorro(Cuenta u, String s) {
		CuentaAhorro c = new CuentaAhorro(this, s);
		cuentasAhorro.add(c);
		return c;
	}
}
