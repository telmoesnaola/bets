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

public class Login extends JFrame {


	private JTextField textField;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JPasswordField passwordField;
	private JLabel lblNewLabel_1;
	private JLabel lblError;
	private JButton btnRegistrarse;
	private Cuenta usuario=null;


	/**
	 * Create the panel.
	 */
	public Login() {
		super();

		this.setSize(new Dimension(604, 370));
		getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(157, 73, 130, 30);
		getContentPane().add(textField);
		textField.setColumns(10);

		lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(157, 48, 71, 14);
		getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1.setBounds(157, 114, 85, 14);
		getContentPane().add(lblNewLabel_1);

		btnNewButton = new JButton();
		btnNewButton.setSize(150, 30);
		btnNewButton.setLocation(146, 170);
		btnNewButton.setText("Iniciar Sesion");
		btnNewButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				String l = textField.getText();
				String p = new String(passwordField.getPassword());

				BLFacade facade = AdminGUI.getBusinessLogic();
				if (facade.hacerLogin(l, p)) {
					if (facade.isAdmin(l)) {
						JFrame a = new AdminGUI();

						a.setVisible(true);
						setVisible(false);

					}else {
						
						usuario=facade.getCuenta(l);
						
						JFrame b = new UserGUI(usuario);

						b.setVisible(true);
						setVisible(false);
						
						
					}
				}else {
					lblError.setVisible(true);
				}
			}
		});

		getContentPane().add(btnNewButton);


		passwordField = new JPasswordField();
		passwordField.setBounds(157, 129, 130, 30);
		getContentPane().add(passwordField);

		lblError = new JLabel("ERROR");
		lblError.setBounds(196, 211, 46, 14);
		lblError.setVisible(false);
		getContentPane().add(lblError);

		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBounds(312, 11, 130, 30);
		btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new RegistrarseGUI();
				a.setVisible(true);
				setVisible(false);
			}
		});

		getContentPane().add(btnRegistrarse);
	}





}









