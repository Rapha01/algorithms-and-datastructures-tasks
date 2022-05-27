import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * A graphical user interface to visualize binary trees.
 * 
 * @author Dominik Kaaser
 * @email dominik@kaaser.at
 * @version 1.0.0
 */
public class TreeGUI {
	private final static int WIDTH = 800;
	private final static int HEIGHT = 600;

	private JFrame gui = null;

	/**
	 * Launch the GUI. Opens a JFrame and draws the data.
	 * 
	 */
	public TreeGUI() {

		gui = new JFrame("Data GUI");
		gui.setSize(WIDTH, HEIGHT);
		gui.setEnabled(true);
		gui.setVisible(true);

		gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		gui.setResizable(false);

		JPanel content = new DataCanvas();
		gui.setContentPane(content);

		Insets insets = gui.getInsets();
		gui.setSize(WIDTH + insets.left + insets.right, HEIGHT + insets.top + insets.bottom);

		gui.setTitle("Tree GUI");

		gui.repaint();

	}

	/**
	 * Refresh the display and do a complete repaint of the entire window.
	 */
	public void update() {
		gui.repaint();
	}

	/**
	 * Inner class to get a Canvas for drawing the tree.
	 * 
	 * @author Dominik Kaaser
	 * @email dominik@kaaser.at
	 * @version 1.0.0
	 */
	private class DataCanvas extends JPanel {

		private static final long serialVersionUID = -7693392408937004652L;

		/**
		 * Method for drawing the data points.
		 */
		@Override
		public void paintComponent(Graphics graphics) {
			graphics.setColor(Color.white);
			graphics.fillRect(0, 0, TreeGUI.WIDTH, TreeGUI.HEIGHT);
			graphics.setColor(Color.black);
			TreeDemo.draw(graphics);
		}
	}
}
