package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Cuenta;
import exceptions.PronosticoAlreadyExist;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CrearCuentaAhorrosGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton crearCuentaButton;
	private JButton volverButton;
	private JButton btnAadirFondos;
	private JLabel lblNewLabel_2;

	

	/**
	 * Create the frame.
	 */
	public CrearCuentaAhorrosGUI(Cuenta u) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("A\u00F1adir cuenta ahorro");
		lblNewLabel.setBounds(157, 22, 130, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Nombre de la cuenta");
		lblNewLabel_1.setBounds(50, 67, 117, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(194, 64, 130, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		crearCuentaButton = new JButton("Crear cuenta");
		crearCuentaButton.setBounds(157, 113, 109, 23);
		contentPane.add(crearCuentaButton);
		crearCuentaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNewLabel_2.setVisible(false);
				String s = textField.getText();
				
				BLFacade facade = AdminGUI.getBusinessLogic();
				
				facade.crearCuentaAhorro(u, s);
				lblNewLabel_2.setVisible(true);
			}
		});
		
		volverButton = new JButton("Volver");
		volverButton.setBounds(75, 188, 124, 23);
		contentPane.add(volverButton);
		
		volverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame b = new UserGUI(u);
				b.setVisible(true);
				setVisible(false);
			}
		});
		
		btnAadirFondos = new JButton("A\u00F1adir fondos");
		btnAadirFondos.setBounds(215, 188, 130, 23);
		contentPane.add(btnAadirFondos);
		
		lblNewLabel_2 = new JLabel("Cuenta creada");
		lblNewLabel_2.setBounds(167, 147, 120, 14);
		lblNewLabel_2.setVisible(false);
		contentPane.add(lblNewLabel_2);
		
		btnAadirFondos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame b = new AñadirFondosGUI(u);
				b.setVisible(true);
				setVisible(false);
			}
		});
	}
}
