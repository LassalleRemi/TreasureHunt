package fr.lille.iutinfo.affichage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javax.swing.JPanel;

public class Composition extends JFrame {
	private JComboBox Eq1J1;
	private JComboBox Eq1J2;
	private JComboBox Eq1J3;
	private JComboBox Eq2J1;
	private JComboBox Eq2J2;
	private JComboBox Eq2J3;
	private JRadioButtonMenuItem b1;
	private JRadioButtonMenuItem b2;
	public JSlider slide = new JSlider();
	public JSlider slideRochers = new JSlider();
	public JLabel taille = new JLabel("Valeur actuelle : 25");
	public JLabel nbreRochers = new JLabel("Pourcentage de rochers : 25");
	public JTextArea nomEquipe1;
	public JTextArea nomEquipe2;
	public boolean stop = false;

	public Composition() {
		String[] type = { "Explorateur", "Voleur", "Piegeur", "Guerrier" };
		this.setTitle("Constituion des équipes");
		this.setPreferredSize(new Dimension(500, 500));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		// Le conteneur principal
		JPanel content = new JPanel();
		content.setPreferredSize(new Dimension(300, 120));
		content.setBackground(new Color(253, 247, 205));
		// On définit le layout manager
		content.setLayout(new GridBagLayout());

		GridBagConstraints g = new GridBagConstraints();

		// equipe1
		g.gridx = 0;
		g.gridy = 0;
		content.add(new JLabel("Membres de l'équipe 1 :"), g);
		g.gridx = 1;
		g.gridy = 1;
		Eq1J1 = new JComboBox(type);
		content.add(Eq1J1, g);
		g.gridx = 1;
		g.gridy = 2;
		Eq1J2 = new JComboBox(type);
		content.add(Eq1J2, g);
		g.gridx = 1;
		g.gridy = 3;
		Eq1J3 = new JComboBox(type);
		content.add(Eq1J3, g);

		// equipe2
		g.gridx = 0;
		g.gridy = 4;
		content.add(new JLabel("Membres de l'équipe 2 :"), g);
		g.gridx = 1;
		g.gridy = 5;
		Eq2J1 = new JComboBox(type);
		content.add(Eq2J1, g);
		g.gridx = 1;
		g.gridy = 6;
		Eq2J2 = new JComboBox(type);
		content.add(Eq2J2, g);
		g.gridx = 1;
		g.gridy = 7;
		Eq2J3 = new JComboBox(type);
		content.add(Eq2J3, g);

		// definir slide taille ile
		g.gridx = 0;
		g.gridy = 10;
		content.add(new JLabel("Choix de la taille de l'ile :"), g);
		g.gridx = 0;
		g.gridy = 11;
		slide.setMaximum(25);
		slide.setMinimum(10);
		slide.setValue(25);
		slide.setPaintTicks(true);
		slide.setPaintLabels(true);
		slide.setMinorTickSpacing(10); // intervalle entre les graduations
		slide.setMajorTickSpacing(5); // intervalle entre les chiffres
		// afficher valeur actuel de taille
		slide.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				taille.setText("Valeur actuelle : " + ((JSlider) event.getSource()).getValue());
			}
		});
		content.add(slide, g);
		g.gridx = 0;
		g.gridy = 12;
		content.add(taille, g);

		// ---Slide pour le pourcentage de rochers---
		g.gridx = 0;
		g.gridy = 14;
		content.add(new JLabel("Choix du pourcentage de rochers :"), g);
		g.gridx = 0;
		g.gridy = 15;
		slideRochers.setMaximum(25);
		slideRochers.setMinimum(10);
		slideRochers.setValue(25);
		slideRochers.setPaintTicks(true);
		slideRochers.setPaintLabels(true);
		slideRochers.setMinorTickSpacing(10); // intervalle entre les
												// graduations
		slideRochers.setMajorTickSpacing(5); // intervalle entre les chiffres
		// afficher valeur actuel de taille
		slideRochers.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				nbreRochers.setText("Pourcentage de rochers : " + ((JSlider) event.getSource()).getValue());
			}
		});
		content.add(slideRochers, g);
		g.gridx = 0;
		g.gridy = 16;
		content.add(nbreRochers, g);

		// Nom equipe
		JLabel nomE1 = new JLabel("Nom de l'équipe 1 : ");
		g.gridx = 1;
		g.gridy = 13;
		content.add(nomE1, g);
		nomEquipe1 = new JTextArea("Skittle", 2, 5);
		g.gridx = 1;
		g.gridy = 14;
		content.add(nomEquipe1, g);

		JLabel nomE2 = new JLabel("Nom de l'équipe 2 : ");
		g.gridx = 1;
		g.gridy = 15;
		content.add(nomE2, g);
		nomEquipe2 = new JTextArea("Smarties", 2, 5);
		g.gridx = 1;
		g.gridy = 16;
		content.add(nomEquipe2, g);

		// bouton valider
		JButton valide = new JButton("Valider");
		g.gridx = 4;
		g.gridy = 17;
		content.add(valide, g);

		valide.addMouseListener(new Mouse());

		// On ajoute le conteneur
		this.setContentPane(content);
		this.setVisible(true);
	}

	private class Mouse implements MouseListener {
		public void mouseClicked(MouseEvent event) {
			setVisible(false);
			stop = true;
		}

		public void mouseEntered(MouseEvent arg0) {
		}

		public void mouseExited(MouseEvent arg0) {
		}

		public void mousePressed(MouseEvent arg0) {
		}

		public void mouseReleased(MouseEvent arg0) {
		}
	}

	public String[] compoEquipe1() {
		return new String[] { Eq1J1.getSelectedItem().toString(), Eq1J2.getSelectedItem().toString(),
				Eq1J3.getSelectedItem().toString() };
	}

	public String[] compoEquipe2() {
		return new String[] { Eq2J1.getSelectedItem().toString(), Eq2J2.getSelectedItem().toString(),
				Eq2J3.getSelectedItem().toString() };
	}
}