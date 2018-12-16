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
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

public class Hangman {

	private JFrame frame;
	private JTextField txtAnswer;
	private JLabel lblDisplayAnswer;
	private char[] answerArray;
	private String[] dashArray;
	private JLabel lblPhoto; 
	private int mistakes = 1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hangman window = new Hangman();
					window.frame.setVisible(true);
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
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblPhoto = new JLabel("");
		lblPhoto.setIcon(new ImageIcon("C:\\Users\\admin\\eclipse-workspace\\test\\src\\Hangman1.png"));
		lblPhoto.setBounds(114, 191, 283, 391);
		frame.getContentPane().add(lblPhoto);

		JCheckBox chckbxAnimals = new JCheckBox("Animals");
		chckbxAnimals.setSelected(true);
		chckbxAnimals.setBounds(530, 122, 221, 41);
		frame.getContentPane().add(chckbxAnimals);

		JCheckBox chckbxColors = new JCheckBox("Colors");
		chckbxColors.setSelected(true);
		chckbxColors.setBounds(530, 165, 221, 41);
		frame.getContentPane().add(chckbxColors);

		JCheckBox chckbxFruits = new JCheckBox("Fruits");
		chckbxFruits.setSelected(true);
		chckbxFruits.setBounds(530, 211, 221, 41);
		frame.getContentPane().add(chckbxFruits);

		txtAnswer = new JTextField();
		txtAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		txtAnswer.setToolTipText("answer");
		txtAnswer.setBounds(68, 123, 236, 39);
//		txtAnswer.addActionListener();
		frame.getContentPane().add(txtAnswer);
		txtAnswer.setColumns(10);

		ArrayList<String> wordList = new ArrayList<String>();

//		String[] wordList2 = new String[5];

		if (chckbxFruits.isSelected()) {
			File file = new File("C:\\Users\\admin\\eclipse-workspace\\test\\src\\fruits.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				wordList.add(st);
			}
			br.close();
		}

		if (chckbxAnimals.isSelected()) {
			File file = new File("C:\\Users\\admin\\eclipse-workspace\\test\\src\\animals.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				wordList.add(st);
			}
			br.close();
		}

		if (chckbxColors.isSelected()) {
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

//		Character.jo

//		String.join(" ", answerArray);

		lblDisplayAnswer = new JLabel(String.join(" ", dashArray));
		lblDisplayAnswer.setBounds(508, 403, 250, 33);
		frame.getContentPane().add(lblDisplayAnswer);

		JButton btnRestart = new JButton("Restart");
		btnRestart.setBounds(771, 28, 171, 41);
		frame.getContentPane().add(btnRestart);

		JButton btnNewButton = new JButton("enter");
		btnNewButton.setBounds(312, 122, 129, 41);
		btnNewButton.addActionListener(new AnswerButtonListener());
		frame.getContentPane().add(btnNewButton);
	}

	class AnswerButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			char userAns = txtAnswer.getText().charAt(0);

			boolean exists = false;
			for (int x = 0; x < answerArray.length; x++) {
				if (userAns == answerArray[x]) {
					dashArray[x] = userAns + "";
					exists = true;
				}

			}

			if (exists) {
				lblDisplayAnswer.setText(String.join(" ", dashArray));
			} else {
//				String hangmanName = 
				mistakes++;
				lblPhoto.setIcon(new ImageIcon("C:\\Users\\admin\\eclipse-workspace\\test\\src\\Hangman"+mistakes+".png"));
			}
			txtAnswer.setText(null);

		}

	}

}
