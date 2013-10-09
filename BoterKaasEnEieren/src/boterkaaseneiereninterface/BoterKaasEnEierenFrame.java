package boterkaaseneiereninterface;
import boterkaaseneierendomein.*;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import boterkaaseneierendomein.SpeelBord;
import boterkaaseneierendomein.Veld;

public class BoterKaasEnEierenFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private ArrayList<JButton> speelvelden = new ArrayList<JButton>();  //  @jve:decl-index=0:
	private SpeelBord speelBord = new SpeelBord();
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BoterKaasEnEierenFrame thisClass = new BoterKaasEnEierenFrame();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setVisible(true);
            }
        });
	}

	/**
	 * This is the default constructor
	 */
	public BoterKaasEnEierenFrame() {
		super();
		initialize();
	}
	
	private class BoterKaasEnEierenFrameJButtonActionListener implements ActionListener {
		private Veld veld;
		private JButton button;
		private BoterKaasEnEierenFrame parent = BoterKaasEnEierenFrame.this;
		public BoterKaasEnEierenFrameJButtonActionListener(JButton source, Veld veld) {
			this.veld = veld;
			this.button = source;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			speelBord.kiesVeld(this.veld);
			if(parent.speelBord.geefVeldStatus(this.veld) == VeldStatus.SPELEREEN)
				this.button.setBackground(Color.RED);
			else
				this.button.setBackground(Color.GREEN);
			this.button.setEnabled(false);
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(500, 500);
		this.setContentPane(getJContentPane());
		int counter = 0;
		for(Veld v: Veld.values()) {
			JButton b = new JButton();
			b.setBackground(Color.LIGHT_GRAY);
			Rectangle r = new Rectangle();
			r.height = 128;
			r.width = 128;
			r.x =  32 + (counter % 3) * 128; 
			r.y = 32 + (counter / 3) * 128;
			b.setBounds(r);
			b.addActionListener(new BoterKaasEnEierenFrameJButtonActionListener(b, v));
			this.getJContentPane().add(b);
			this.speelvelden.add(b);
			counter++;
		}
		JButton resetButton = new JButton();
		resetButton.setText("Reset");
		Rectangle resetButtonBounds = new Rectangle();
		resetButtonBounds.height = 24;
		resetButtonBounds.width = 128;
		resetButtonBounds.x = 0;
		resetButtonBounds.y = 425;
		resetButton.setBounds(resetButtonBounds);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				speelBord.startNieuwSpel();
				for(JButton b: BoterKaasEnEierenFrame.this.speelvelden) {
					b.setBackground(Color.LIGHT_GRAY);
					b.setEnabled(true);
				}
			}			
		});
		this.getJContentPane().add(resetButton);
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
		}
		return jContentPane;
	}
}
