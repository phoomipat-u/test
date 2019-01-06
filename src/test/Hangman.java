package test;

import java.awt.EventQueue;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

public class Hangman {

	private JFrame greetingFrame;
	private JFrame mainFrame;
	private JTextField txtAnswer;
	private JLabel lblDisplayAnswer;
	private char[] answerArray;
	private String[] dashArray;
	private JLabel lblPhoto;
	private int mistakes = 1;
	private JLabel lblKlx;

	Container greetingContainer = new Container();
	Container mainContainer = new Container();

	JCheckBox chckbxAnimals1;
	JCheckBox chckbxColors1;
	JCheckBox chckbxFruits1;

	JCheckBox chckbxAnimals2;
	JCheckBox chckbxColors2;
	JCheckBox chckbxFruits2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Hangman window = new Hangman();
					window.mainFrame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 */
	public Hangman() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws Exception
	 * 
	 * @throws Exception
	 */
	private void generateWord() throws Exception {
		ArrayList<String> wordList = new ArrayList<String>();

//		String[] wordList2 = new String[5];
		if (chckbxFruits2.isSelected()) {
			File file = new File("C:\\Users\\admin\\eclipse-workspace\\test\\src\\fruits.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				wordList.add(st);
			}
			br.close();
		}

		if (chckbxAnimals2.isSelected()) {
			File file = new File("C:\\Users\\admin\\eclipse-workspace\\test\\src\\animals.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				wordList.add(st);
			}
			br.close();
		}

		if (chckbxColors2.isSelected()) {
			File file = new File("C:\\Users\\admin\\eclipse-workspace\\test\\src\\colors.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				wordList.add(st);
			}
			br.close();
		}

		Random ran = new Random();
		int randIndex = ran.nextInt(wordList.size());
		String answer = wordList.get(randIndex);
		answerArray = answer.toCharArray();
		dashArray = new String[answerArray.length];
		for (int k = 0; k < answerArray.length; k++) {
			dashArray[k] = "_";
		}

		System.out.print(answer);
	}

	private void initialize() throws Exception {

		mainFrame = new JFrame();
		mainFrame.setBounds(100, 100, 1000, 1000);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);

		chckbxAnimals1 = new JCheckBox("Animals");
		chckbxAnimals1.setSelected(true);
		chckbxAnimals1.setBounds(530, 122, 221, 41);
		chckbxAnimals1.addActionListener(new CheckBoxButtonListener("animals"));
		greetingContainer.add(chckbxAnimals1);

		chckbxColors1 = new JCheckBox("Colors");
		chckbxColors1.setSelected(true);
		chckbxColors1.setBounds(530, 165, 221, 41);
		chckbxColors1.addActionListener(new CheckBoxButtonListener("colors"));
		greetingContainer.add(chckbxColors1);

		chckbxFruits1 = new JCheckBox("Fruits");
		chckbxFruits1.setSelected(true);
		chckbxFruits1.setBounds(530, 211, 221, 41);
		chckbxFruits1.addActionListener(new CheckBoxButtonListener("fruits"));
		greetingContainer.add(chckbxFruits1);

		JButton btnStart = new JButton("Start");
		btnStart.setBounds(771, 28, 171, 41);
		btnStart.addActionListener(new StartButtonListener());
		greetingContainer.add(btnStart);

		lblPhoto = new JLabel("");
		lblPhoto.setIcon(new ImageIcon("C:\\Users\\admin\\eclipse-workspace\\test\\src\\Hangman1.png"));
		lblPhoto.setBounds(114, 191, 283, 391);
		mainContainer.add(lblPhoto);

		chckbxAnimals2 = new JCheckBox("Animals");
		chckbxAnimals2.setSelected(chckbxAnimals1.isSelected());
		chckbxAnimals2.setBounds(530, 122, 221, 41);
		mainContainer.add(chckbxAnimals2);

		chckbxColors2 = new JCheckBox("Colors");
		chckbxColors2.setSelected(chckbxColors1.isSelected());
		chckbxColors2.setBounds(530, 165, 221, 41);
		mainContainer.add(chckbxColors2);

		chckbxFruits2 = new JCheckBox("Fruits");
		chckbxFruits2.setSelected(chckbxFruits1.isSelected());
		chckbxFruits2.setBounds(530, 211, 221, 41);
		mainContainer.add(chckbxFruits2);

		txtAnswer = new JTextField();
		txtAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		txtAnswer.setToolTipText("answer");
		txtAnswer.setBounds(68, 123, 236, 39);
//		txtAnswer.addActionListener();
		mainContainer.add(txtAnswer);
		txtAnswer.setColumns(10);
		txtAnswer.addActionListener(new AnswerButtonListener());

//		Character.jo

//		String.join(" ", answerArray);
		generateWord();
		lblDisplayAnswer = new JLabel(String.join(" ", dashArray));
		lblDisplayAnswer.setBounds(501, 389, 250, 33);
		mainContainer.add(lblDisplayAnswer);

		JButton btnRestart = new JButton("Restart");
		btnRestart.setBounds(771, 28, 171, 41);
		btnRestart.addActionListener(new RestartButtonListener());
		mainContainer.add(btnRestart);

		JButton btnNewButton = new JButton("enter");
		btnNewButton.setBounds(312, 122, 129, 41);
		btnNewButton.addActionListener(new AnswerButtonListener());
		mainContainer.add(btnNewButton);

		mainFrame.setContentPane(greetingContainer);
//		mainFrame.setCo

	}

	public void restart() {
		mistakes = 1;
		lblPhoto.setIcon(new ImageIcon("C:\\Users\\admin\\eclipse-workspace\\test\\src\\Hangman1.png"));
		lblDisplayAnswer.setText(null);
		try {
			generateWord();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class StartButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
//			mainFrame.setContentPane(null);
			mainFrame.setContentPane(mainContainer);
			mainFrame.revalidate();
			mainFrame.repaint();

			chckbxAnimals2.setSelected(chckbxAnimals1.isSelected());
			chckbxFruits2.setSelected(chckbxFruits1.isSelected());
			chckbxColors2.setSelected(chckbxColors1.isSelected());
			try {
				generateWord();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//			mainFrame.setContentPane(mainContainer);
		}
	}

	class RestartButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			restart();
		}
	}

	class CheckBoxButtonListener implements ActionListener {

		String checkbox;

		public CheckBoxButtonListener(String checkbox) {
			this.checkbox = checkbox;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {

			System.out.print(checkbox);

			if (checkbox.equals("animals")) {
				chckbxAnimals1.setSelected(chckbxAnimals1.isSelected());
			} else if (checkbox.equals("fruits")) {
				chckbxFruits1.setSelected(chckbxFruits1.isSelected());
			}

			else if (checkbox.equals("colors")) {
				chckbxColors1.setSelected(chckbxColors1.isSelected());
			}

		}

	}

	class AnswerButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			char userAns = txtAnswer.getText().toLowerCase().charAt(0);

			boolean exists = false;
			for (int x = 0; x < answerArray.length; x++) {
				if (userAns == answerArray[x]) {
					dashArray[x] = userAns + "";
					exists = true;
				}

			}

			if (exists) {
				lblDisplayAnswer.setText(String.join(" ", dashArray));

				boolean completed = true;
				for (int i = 0; i < dashArray.length; i++) {
					if (dashArray[i] == "_") {
						completed = false;
					}
				}

				if (completed) {
					int ans = JOptionPane.showConfirmDialog(mainFrame, "You won! Would you like to play again?", "hangman", 0, 1, null);
					if (ans == 0) {
						restart();
					}
				}

			} else {
//				String hangmanName = 
				mistakes++;
				System.out.println(mistakes);
				lblPhoto.setIcon(
						new ImageIcon("C:\\Users\\admin\\eclipse-workspace\\test\\src\\Hangman" + mistakes + ".png"));
			}
			if (mistakes == 10) {
				int ans = JOptionPane.showConfirmDialog(mainFrame, "You lost! Would you like to play again?", "hangman",
						0, 1, null);
				if (ans == 0) {
					restart();
				}
			}

			txtAnswer.setText(null);

		}

	}
}
