package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Apuesta;
import domain.Cuenta;
import domain.CuentaAhorro;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MisApuestasGUI extends JFrame {

	private JPanel contentPane;
	private String[] columnNamesApuestas = new String[] {
			"Evento",
			"Pregunta",
			"Pronostico",
			"Cerrada",
			"Ganada",
			"Dinero Apostado",
			"Dinero Ganado",
	};
	private DefaultTableModel tableModelApuestas;
	private JTable tableApuestas= new JTable();
	private JScrollPane scrollPane=new JScrollPane();
	private JButton btnNewButton;
	private JComboBox<String> comboBox;
	private DefaultComboBoxModel<String> cuentasList = new DefaultComboBoxModel<String>();
	private Vector<CuentaAhorro> cuentas= new Vector<CuentaAhorro>();
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton_1;
	public MisApuestasGUI(Cuenta u) {

		BLFacade facade = AdminGUI.getBusinessLogic();

		setBounds(100, 100, 760, 370);
		contentPane = new JPanel();

		setContentPane(contentPane);
		this.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Selecciona una cuenta: ");
		lblNewLabel.setBounds(34, 32, 147, 14);
		this.getContentPane().add(lblNewLabel);

		comboBox = new JComboBox<String>();
		cuentas = facade.getCuentaAhorro(u);
		for (domain.CuentaAhorro c: cuentas) {
			cuentasList.addElement(c.getNombreCuenta());
		}
		comboBox.setModel(cuentasList);
		comboBox.setBounds(164, 29, 203, 20);
		this.getContentPane().add(comboBox);

		btnNewButton = new JButton("Buscar apuestas");
		btnNewButton.setBounds(133, 72, 132, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CuentaAhorro c1 = null;
				tableModelApuestas.setDataVector(null, columnNamesApuestas);
				tableModelApuestas.setColumnCount(7);

				for (domain.CuentaAhorro c:cuentas) {
					if(c.getNombreCuenta().equals(comboBox.getSelectedItem())) {
						c1=c;
					}
				}

				Vector<Apuesta> apuestas = c1.getApuestas();

				for (domain.Apuesta a: apuestas) {
					Vector<Object> row = new Vector<Object>();

					row.add(a.getEvento().getDescription());
					row.add(a.getPregunta().getQuestion());
					row.add(a.getPronostico().getPronostico());

					if (a.isCerrada()) {
						row.add("Sí");
					}else {
						row.add("No");
					}

					if (a.isGanada()) {
						row.add("Sí");
					}else {
						row.add("No");
					}

					row.add(a.getCantidad());

					if (a.isCerrada()) {
						if (a.isGanada()) {
							row.add((a.getCantidad()*a.getPronostico().getCuota())-a.getCantidad());
						}else {
							row.add(-(a.getCantidad()));
						}
					}

					tableModelApuestas.addRow(row);
				}

				lblNewLabel_2.setText(Float.toString(facade.verGanancias(c1)));

			}
		});
		this.getContentPane().add(btnNewButton);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 122, 700, 176);

		scrollPane.setViewportView(tableApuestas);
		tableModelApuestas=new DefaultTableModel(null, columnNamesApuestas);
		tableApuestas.setModel(tableModelApuestas);

		this.getContentPane().add(scrollPane);

		lblNewLabel_1 = new JLabel("Ganancias: ");
		lblNewLabel_1.setBounds(419, 32, 77, 14);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(506, 32, 84, 14);
		contentPane.add(lblNewLabel_2);

		btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.setBounds(634, 72, 89, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame b = new UserGUI(u);
				b.setVisible(true);
				setVisible(false);
			}
		});
	}
}
