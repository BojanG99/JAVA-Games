package igrica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Zid extends Polje {
	
	public Zid(Mreza mojaMreza) {
		super(mojaMreza);
		Polje p = this;
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (mojaMreza.vrstaPolja == 0)
					Mreza.zameniPolje(p);
			}
		});
	}

	@Override
	public boolean DaLiSeFiguraMozeNalaziti() {
		return false;
	}

	public void paint(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
}
