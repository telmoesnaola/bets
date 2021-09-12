package gui;

import javax.swing.*;


import businessLogic.BLFacade;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;

import domain.Cuenta;
import domain.Event;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.Vector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserGUI extends JFrame {


	private JButton buscarButton;
	private JButton añadirCuentaButton;
	private JButton btnAñadirFondos;
	private JButton btnMisApuestas;

	/**
	 * Create the panel.
	 */
	public UserGUI(Cuenta u) {
		super();

		this.setSize(new Dimension(604, 370));
		getContentPane().setLayout(null);
		
		JLabel bienvenidaLabel = new JLabel("Bienvenido "+u.getNombre());
		bienvenidaLabel.setBounds(191, 59, 227, 44);
		getContentPane().add(bienvenidaLabel);
		
		buscarButton = new JButton("Buscar eventos");
		buscarButton.setBounds(74, 131, 185, 60);
		getContentPane().add(buscarButton);
		
		añadirCuentaButton = new JButton("A\u00F1adir cuenta");
		añadirCuentaButton.setBounds(269, 131, 185, 60);
		getContentPane().add(añadirCuentaButton);
		
		btnAñadirFondos = new JButton("A\u00F1adir Fondos");
		btnAñadirFondos.setBounds(74, 202, 185, 60);
		getContentPane().add(btnAñadirFondos);
		
		btnMisApuestas = new JButton("Mis Apuestas");
		btnMisApuestas.setBounds(269, 202, 185, 60);
		getContentPane().add(btnMisApuestas);
		
		añadirCuentaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame b = new CrearCuentaAhorrosGUI(u);
				b.setVisible(true);
				setVisible(false);
			}
		});
		
		buscarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame b = new BusquedaApostarGUI(u);
				b.setVisible(true);
				setVisible(false);
			}
		});
		
		btnAñadirFondos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame b = new AñadirFondosGUI(u);
				b.setVisible(true);
				setVisible(false);
			}
		});
		
		btnMisApuestas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame b = new MisApuestasGUI(u);
				b.setVisible(true);
				setVisible(false);
			}
		});
	}
}









