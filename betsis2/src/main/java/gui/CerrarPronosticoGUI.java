package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import domain.Cuenta;
import domain.Pronostico;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CerrarPronosticoGUI extends JFrame {
	private JLabel eventLabel;
	private JLabel preguntaLabel;
	private JLabel eligePronosticoLabel;
	private JComboBox<String> comboBox;
	private JButton apostarButton;
	private JLabel eventEnseñarLabel;
	private JLabel preguntaEnseñarLabel;
	private DefaultComboBoxModel<String> pronosticoList = new DefaultComboBoxModel<String>();
	private Vector<Pronostico> pronosticos = new Vector<Pronostico>();
	private Pronostico p;
	private JLabel lblNewLabel_2;
	private JButton volverButton;
	public CerrarPronosticoGUI(int questionNum, int eventNum) {

		this.setSize(new Dimension(441, 312));

		BLFacade facade = AdminGUI.getBusinessLogic();


		getContentPane().setLayout(null);

		eventLabel = new JLabel("Evento: ");
		eventLabel.setBounds(91, 31, 59, 14);
		getContentPane().add(eventLabel);

		preguntaLabel = new JLabel("Pregunta: ");
		preguntaLabel.setBounds(79, 56, 70, 14);
		getContentPane().add(preguntaLabel);

		eligePronosticoLabel = new JLabel("Cierra pronostico:");
		eligePronosticoLabel.setBounds(41, 89, 109, 14);
		getContentPane().add(eligePronosticoLabel);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(160, 86, 256, 20);
		pronosticos = facade.getPronosticos(questionNum);
		for (domain.Pronostico p:pronosticos) {
			pronosticoList.addElement(p.toString());
		}
		comboBox.setModel(pronosticoList);
		getContentPane().add(comboBox);

		apostarButton = new JButton("Cerrar pregunta");
		apostarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				lblNewLabel_2.setVisible(false);
				domain.Event e = facade.getEvent(eventNum);
				domain.Question q = facade.getQuestion(questionNum);




				for (domain.Pronostico i:pronosticos) {
					if (i.toString().equals(comboBox.getSelectedItem())) {
						p=i;
						facade.cerrarPregunta(p, e, q);
						lblNewLabel_2.setVisible(true);
					}


					
					
					

				}

			}
		});
		apostarButton.setBounds(146, 175, 135, 23);
		getContentPane().add(apostarButton);

		eventEnseñarLabel = new JLabel("New label");
		eventEnseñarLabel.setBounds(159, 31, 136, 14);
		eventEnseñarLabel.setText(facade.getEvent(eventNum).getDescription());
		getContentPane().add(eventEnseñarLabel);

		preguntaEnseñarLabel = new JLabel("New label");
		preguntaEnseñarLabel.setBounds(159, 56, 213, 14);
		preguntaEnseñarLabel.setText(facade.getQuestion(questionNum).getQuestion());
		getContentPane().add(preguntaEnseñarLabel);

		lblNewLabel_2 = new JLabel("Pregunta cerrada");
		lblNewLabel_2.setVisible(false);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(160, 209, 148, 25);
		getContentPane().add(lblNewLabel_2);

		volverButton = new JButton("Volver");
		volverButton.setBounds(305, 22, 89, 23);
		getContentPane().add(volverButton);
		volverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

	}
}
