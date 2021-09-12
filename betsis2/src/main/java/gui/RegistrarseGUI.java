package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;

public class RegistrarseGUI extends JFrame {

	private JPanel contentPane;
	private JTextField usuarioField;
	private JPasswordField passwordField;
	private JLabel lblError;
	private JTextField nombreField;
	private JTextField apellidoField;
	private JTextField mailField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarseGUI frame = new RegistrarseGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegistrarseGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usuarioField = new JTextField();
		usuarioField.setBounds(133, 24, 148, 23);
		contentPane.add(usuarioField);
		usuarioField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(133, 156, 148, 23);
		contentPane.add(passwordField);
		
		JButton btnRegistrate = new JButton("Registrate");
		btnRegistrate.setBounds(157, 185, 119, 23);
		btnRegistrate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				String l = usuarioField.getText();
				String p = new String(passwordField.getPassword());
				String n = nombreField.getText();
				String a = apellidoField.getText();
				String m = mailField.getText();
				
				BLFacade facade = AdminGUI.getBusinessLogic();
				if (facade.registrar(l, p, n, a, m)) {
					JFrame b = new UserGUI(facade.getCuenta(l));

					b.setVisible(true);
					setVisible(false);
				}else {
					lblError.setVisible(true);
				}
			}
		});
		contentPane.add(btnRegistrate);
		
		lblError = new JLabel("ERROR");
		lblError.setForeground(Color.RED);
		lblError.setBounds(198, 219, 46, 14);
		lblError.setVisible(false);
		contentPane.add(lblError);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(52, 28, 73, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(52, 160, 93, 14);
		contentPane.add(lblContrasea);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(52, 60, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Apellido");
		lblNewLabel_1.setBounds(52, 94, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mail");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setBounds(52, 126, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		nombreField = new JTextField();
		nombreField.setBounds(133, 56, 148, 23);
		contentPane.add(nombreField);
		nombreField.setColumns(10);
		
		apellidoField = new JTextField();
		apellidoField.setBounds(133, 90, 148, 23);
		contentPane.add(apellidoField);
		apellidoField.setColumns(10);
		
		mailField = new JTextField();
		mailField.setBounds(133, 123, 148, 23);
		contentPane.add(mailField);
		mailField.setColumns(10);
		
	}
}
