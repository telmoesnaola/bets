package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import domain.Cuenta;
import domain.CuentaAhorro;
import domain.Pronostico;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ApostarGUI extends JFrame {
	private JTextField cantField;
	private JLabel eventLabel;
	private JLabel preguntaLabel;
	private JLabel eligePronosticoLabel;
	private JComboBox<String> comboBox;
	private JLabel lblNewLabel;
	private JButton apostarButton;
	private JLabel eventEnseñarLabel;
	private JLabel preguntaEnseñarLabel;
	private DefaultComboBoxModel<String> pronosticoList = new DefaultComboBoxModel<String>();
	private Vector<Pronostico> pronosticos = new Vector<Pronostico>();
	private JLabel lblNewLabel_1;
	private JLabel apuestaMinimaLabel;
	private Pronostico p;
	private JLabel ERROR;
	private JLabel lblNewLabel_2;
	private JButton volverButton;
	private JLabel lblEligeTuCuenta;
	private JComboBox<String> comboBox_1;
	private DefaultComboBoxModel<String> cuentasList = new DefaultComboBoxModel<String>();
	private Vector<CuentaAhorro> cuentas= new Vector<CuentaAhorro>();
	public ApostarGUI(Cuenta usuario, int questionNum, int eventNum) {

		this.setSize(new Dimension(441, 312));

		BLFacade facade = AdminGUI.getBusinessLogic();


		getContentPane().setLayout(null);

		eventLabel = new JLabel("Evento: ");
		eventLabel.setBounds(91, 31, 59, 14);
		getContentPane().add(eventLabel);

		preguntaLabel = new JLabel("Pregunta: ");
		preguntaLabel.setBounds(79, 56, 70, 14);
		getContentPane().add(preguntaLabel);

		eligePronosticoLabel = new JLabel("Elige tu pronostico:");
		eligePronosticoLabel.setBounds(41, 113, 109, 14);
		getContentPane().add(eligePronosticoLabel);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(160, 110, 256, 20);
		pronosticos = facade.getPronosticos(questionNum);
		for (domain.Pronostico p:pronosticos) {
			pronosticoList.addElement(p.toString());
		}
		comboBox.setModel(pronosticoList);
		getContentPane().add(comboBox);

		lblNewLabel = new JLabel("Cantidad a apostar:");
		lblNewLabel.setBounds(41, 189, 110, 14);
		getContentPane().add(lblNewLabel);

		cantField = new JTextField();
		cantField.setBounds(159, 186, 45, 20);
		getContentPane().add(cantField);
		cantField.setColumns(10);

		apostarButton = new JButton("Apostar");
		apostarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ERROR.setVisible(false);
				lblNewLabel_2.setVisible(false);
				domain.Event e = facade.getEvent(eventNum);
				domain.Question q = facade.getQuestion(questionNum);
				CuentaAhorro c1 = null;

				float f = Float.parseFloat(cantField.getText());
				
				for (domain.CuentaAhorro c:cuentas) {
					if(c.getNombreCuenta().equals(comboBox_1.getSelectedItem())) {
						c1=c;
					}
				}

				if (f>=q.getBetMinimum()&&f<=c1.getFondos()) {
					for (domain.Pronostico i:pronosticos) {
						if (i.toString().equals(comboBox.getSelectedItem())) {
							p=i;
						}
					}
					

					facade.createApuesta(p, f, e, q, usuario, c1);
					facade.restarFondos(c1, f);
					lblNewLabel_2.setVisible(true);

				}
				else {
					ERROR.setVisible(true);
				}
			}
		});
		apostarButton.setBounds(159, 217, 89, 23);
		getContentPane().add(apostarButton);

		eventEnseñarLabel = new JLabel("New label");
		eventEnseñarLabel.setBounds(159, 31, 136, 14);
		eventEnseñarLabel.setText(facade.getEvent(eventNum).getDescription());
		getContentPane().add(eventEnseñarLabel);

		preguntaEnseñarLabel = new JLabel("New label");
		preguntaEnseñarLabel.setBounds(159, 56, 213, 14);
		preguntaEnseñarLabel.setText(facade.getQuestion(questionNum).getQuestion());
		getContentPane().add(preguntaEnseñarLabel);

		lblNewLabel_1 = new JLabel("Apuesta m\u00EDnima:");
		lblNewLabel_1.setBounds(50, 88, 100, 14);
		getContentPane().add(lblNewLabel_1);

		apuestaMinimaLabel = new JLabel("New label");
		apuestaMinimaLabel.setBounds(160, 88, 46, 14);
		apuestaMinimaLabel.setText(Float.toString(facade.getQuestion(questionNum).getBetMinimum()));
		getContentPane().add(apuestaMinimaLabel);

		ERROR = new JLabel("ERROR");
		ERROR.setVisible(false);
		ERROR.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ERROR.setBounds(169, 240, 89, 33);
		getContentPane().add(ERROR);

		lblNewLabel_2 = new JLabel("Apuesta realizada!");
		lblNewLabel_2.setVisible(false);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(160, 245, 148, 25);
		getContentPane().add(lblNewLabel_2);

		volverButton = new JButton("Volver");
		volverButton.setBounds(305, 22, 89, 23);
		getContentPane().add(volverButton);
		
		lblEligeTuCuenta = new JLabel("Elige tu cuenta:");
		lblEligeTuCuenta.setBounds(41, 150, 109, 14);
		getContentPane().add(lblEligeTuCuenta);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(160, 147, 255, 20);
		cuentas = facade.getCuentaAhorro(usuario);
		for (domain.CuentaAhorro c: cuentas) {
			cuentasList.addElement(c.getNombreCuenta());
		}
		comboBox_1.setModel(cuentasList);
		getContentPane().add(comboBox_1);
		
		
		volverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame b = new UserGUI(usuario);
				b.setVisible(true);
				setVisible(false);
			}
		});

	}
}
