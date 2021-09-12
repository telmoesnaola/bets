package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Cuenta;
import domain.CuentaAhorro;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class AñadirFondosGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JComboBox<String> comboBox;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	private JButton AnadirFondosBtn;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private DefaultComboBoxModel<String> cuentasList = new DefaultComboBoxModel<String>();
	private Vector<CuentaAhorro> cuentas= new Vector<CuentaAhorro>();

	public AñadirFondosGUI(Cuenta u) {
		
		BLFacade facade = AdminGUI.getBusinessLogic();
		
		this.setSize(new Dimension(441, 312));
		setBounds(100, 100, 450, 300);
		
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Cuentas de "+u.getNombre()+":");
		lblNewLabel.setBounds(36, 37, 150, 14);
		getContentPane().add(lblNewLabel);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(185, 34, 167, 20);
		cuentas = facade.getCuentaAhorro(u);
		for (domain.CuentaAhorro c: cuentas) {
			cuentasList.addElement(c.getNombreCuenta());
		}
		comboBox.setModel(cuentasList);
		getContentPane().add(comboBox);
		
		lblNewLabel_1 = new JLabel("Cantidad a a\u00F1adir:");
		lblNewLabel_1.setBounds(36, 92, 126, 14);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(185, 89, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Volver");
		btnNewButton.setBounds(97, 182, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame b = new UserGUI(u);
				b.setVisible(true);
				setVisible(false);
			}
		});
		getContentPane().add(btnNewButton);
		
		AnadirFondosBtn = new JButton("A\u00F1adir Fondos");
		AnadirFondosBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_2.setVisible(false);
				lblNewLabel_3.setVisible(false);
				try {
				
				
				float f = Float.parseFloat(textField.getText());
				
				for (domain.CuentaAhorro c:cuentas) {
					if(c.getNombreCuenta().equals(comboBox.getSelectedItem())) {
						CuentaAhorro c1=c;
						facade.restarFondos(c1, (-f));
					}
				}
				lblNewLabel_2.setVisible(true);
				}
				catch(Exception e1){
					lblNewLabel_3.setVisible(true);
				}
			}
		});
		AnadirFondosBtn.setBounds(196, 182, 126, 23);
		getContentPane().add(AnadirFondosBtn);
		
		lblNewLabel_2 = new JLabel("Fondos a\u00F1adidos correctamente");
		lblNewLabel_2.setBounds(126, 139, 204, 14);
		getContentPane().add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
		
		lblNewLabel_3 = new JLabel("ERROR");
		lblNewLabel_3.setBounds(189, 139, 46, 14);
		getContentPane().add(lblNewLabel_3);
		lblNewLabel_3.setVisible(false);
	}
}
