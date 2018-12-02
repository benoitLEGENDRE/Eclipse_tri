import java.util.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.*;

/**
 * @author Guillaume Huard
 * @version 08/03/2016
 */
public class MachineTrace extends JComponent implements Runnable
{
	private static final long serialVersionUID = -2564095553370498809L;
	RefreshHandler refresh;
	java.util.List<Line> lines;
	java.util.Stack<Integer> libre;
	Dimension d;
	boolean enBas;
	double angle;
	Point2D position;
	boolean autoRefresh;

	public void attendre(int s) {
		try {Thread.sleep(s);} catch (Exception e) {};
	}

	public void lever() {
		enBas = false;
	}

	public void baisser() {
		enBas = true;
	}

	public int avancer(double L) {
		double x = position.getX();
		double y = position.getY();

		x += L*Math.cos(angle);
		y += L*Math.sin(angle);
		return placer(x, y);    
	}

	public void tournerGauche(double a) {
		angle += a*2*Math.PI/360.0;
	}

	public void tournerDroite(double a) {
		angle -= a*2*Math.PI/360.0;
	}

	public void orienter(double a) {
		angle = a*2*Math.PI/360.0;
	}

	public static int convert(double d) {
		return (int) Math.round(d);
	}

	public int placer(double x, double y) {
		Point2D nouveau = new Point2D.Double(x, y);
		int resultat = -1;
		if (enBas) {
			int x1 = convert(position.getX()) + d.width/2;
			int y1 = -convert(position.getY()) + d.height/2;
			int x2 = convert(nouveau.getX()) + d.width/2;
			int y2 = -convert(nouveau.getY()) + d.height/2;
			Line l = new Line(x1, y1, x2, y2);
			if (libre.size() > 0) {
				resultat = libre.pop();
				lines.set(resultat, l);
			} else {
				lines.add(new Line(x1, y1, x2, y2));
				resultat = lines.size() - 1;
			}
			if (autoRefresh)
				SwingUtilities.invokeLater(refresh);
		}
		position = nouveau;
		return resultat;
	}

	public void effacer(int num) {
		lines.set(num, null);
		libre.push(num);
		if (autoRefresh)
			SwingUtilities.invokeLater(refresh);
	}

	public void effacerTout() {
		lines.clear();
		libre.clear();
		if (autoRefresh)
			SwingUtilities.invokeLater(refresh);
	}
	
	public void rafraichir() {
		SwingUtilities.invokeLater(refresh);
	}

	/**
	 * Constructeur d'objets de classe MachineTrace
	 */
	public MachineTrace(int l, int h)
	{
		refresh = new RefreshHandler(this);
		enBas = false;
		position = new Point2D.Double();
		lines = Collections.synchronizedList(new ArrayList<Line>());
		libre = new Stack<Integer>();
		d = new Dimension(l,h);
		autoRefresh = true;
		SwingUtilities.invokeLater(this);
	}

	public MachineTrace(int l, int h, boolean r) {
		this(l, h);
		autoRefresh = r;
	}

	public void run() {
		JFrame frame = new JFrame("Machine trace");
		this.setPreferredSize(d);
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setSize(d.width, d.height);
		frame.pack();
		frame.setVisible(true);
	}

	public void paintComponent(Graphics g) {
		Graphics2D drawable = (Graphics2D) g;
		int width = getSize().width;
		int height = getSize().height;

		// On efface tout
		drawable.setPaint(Color.white);
		drawable.fillRect(0, 0, width, height);
		drawable.setPaint(Color.black);

		synchronized(lines) {
			Iterator<Line> it = lines.iterator();
			while (it.hasNext()) {
				Line l = it.next();
				if (l != null)
					drawable.drawLine(l.x1, l.y1, l.x2, l.y2);
			}
		}
	}
}

class RefreshHandler implements Runnable {
	JComponent c;

	RefreshHandler(JComponent component) {
		c = component;
	}

	public void run() {
		c.repaint();
	}
}

class Line {
	int x1, y1, x2, y2;

	Line(int a, int b, int c, int d) {
		x1 = a;
		y1 = b;
		x2 = c;
		y2 = d;
	}
}
