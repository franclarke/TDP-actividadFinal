import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;


import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;

public class Panel extends JFrame {

	private static final long serialVersionUID = -7026196256867529758L;
	private JPanel contentPane;
	private JFileChooser fileChooser;
	private String path;
	private String[] listado = new String[1];
	private Logica logica;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panel frame = new Panel();
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
	public Panel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);


		logica = new Logica();

		@SuppressWarnings("unused")
		Idioma idioma = new Idioma();

		ResourceBundle bundle1 = ResourceBundle.getBundle("es_ES");
		ResourceBundle bundle2 = ResourceBundle.getBundle("en_US");

		JTextArea board = new JTextArea();
		board.setBackground(Color.WHITE);
		board.setEditable(false);
		board.setBounds(27, 72, 449, 125);
		contentPane.add(board);

		JTextArea board2 = new JTextArea();
		board2.setLineWrap(true);
		board2.setRows(5);
		board2.setEditable(false);
		board2.setBackground(Color.WHITE);
		board2.setBounds(27, 330, 449, 125);
		contentPane.add(board2);

		setTitle(bundle1.getString("titulo"));





		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					board2.setText(null);
					int total=0;
					HashMap<String, Integer> mapa = new HashMap<String, Integer>();
					mapa = logica.cuentaPalabras(path+"\\"+comboBox.getSelectedItem().toString(),mapa);
					total+=logica.totalPalabras(path+"\\"+comboBox.getSelectedItem().toString());

					Set<String> keys = mapa.keySet();
					Iterator<String> itk = keys.iterator();
					Iterator<Integer> itv = mapa.values().iterator();
					String key= "";
					int value=0;
					for(int i=0; i<5;i++) {
						key = itk.next();
						value = itv.next();
						board2.append(key+" ---> "+value+",        "+(value*100)/total+"%"+"\n");
					}


				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		comboBox.setBounds(221, 280, 255, 40);
		contentPane.add(comboBox);



		JButton btnCargarDir = new JButton(bundle1.getString("cargarDir"));
		btnCargarDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				board.setText(null);
				fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int seleccion = fileChooser.showOpenDialog(contentPane);
				if (seleccion == JFileChooser.APPROVE_OPTION)
				{
					File fichero = fileChooser.getSelectedFile();
					path = fichero.getAbsolutePath();
					listado = fichero.list();
					for (int j = 0; j < listado.length; j++) {
						if(listado[j].endsWith(".txt" )) {
							//Agrego listado al JText
							board.append(listado[j]+"\n");
							//Agrego listado al comboBox
							comboBox.addItem(listado[j]);
						}
					}
					board2.setText(null);
				}
			}
		});
		btnCargarDir.setBounds(27, 22, 140, 40);
		contentPane.add(btnCargarDir);

		JButton btnComenzar = new JButton(bundle1.getString("comenzar"));
		btnComenzar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int total=0;
					board2.setText(null);
					HashMap<String, Integer> mapa = new HashMap<String, Integer>();
					if(listado[0]!=null) {
						for(String archivo : listado) {
							mapa = logica.cuentaPalabras(path+"\\"+archivo,mapa);
							total+=logica.totalPalabras(path+"\\"+archivo);
						}
						Set<String> keys = mapa.keySet();
						Iterator<String> itk = keys.iterator();
						Iterator<Integer> itv = mapa.values().iterator();
						String key= "";
						int value=0;
						for(int i=0; i<5;i++) {
							key = itk.next();
							value = itv.next();
							board2.append(key+" ---> "+value+",        "+(value*100)/total+"%"+"\n");
						}
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnComenzar.setBounds(27, 280, 140, 40);
		contentPane.add(btnComenzar);


		JButton eng = new JButton("Ingles");
		eng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCargarDir.setText(bundle2.getString("cargarDir"));
				btnComenzar.setText(bundle2.getString("comenzar"));
				setTitle(bundle2.getString("titulo"));
			}
		});
		eng.setBounds(129, 532, 85, 21);
		contentPane.add(eng);

		JButton esp = new JButton("Español");
		esp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCargarDir.setText(bundle1.getString("cargarDir"));
				btnComenzar.setText(bundle1.getString("comenzar"));
				setTitle(bundle1.getString("titulo"));
			}
		});
		esp.setBounds(279, 532, 85, 21);
		contentPane.add(esp);











	}
}
