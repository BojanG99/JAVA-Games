package igrica;


public class Vektor implements Cloneable {
	private double x , y;

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Vektor(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public Vektor clone() {
		try {
			Vektor v = (Vektor)super.clone();
			v.x = x;
			v.y = y;
			return v;
		}
		catch (CloneNotSupportedException e) {return null;}
	}
	
	public void pomnozi(double uv) {
		x *= uv;
		y *= uv;
	}
	
	public void saberi( Vektor v2) {
		x += v2.x ;
		y += v2.y;
	}
	
}
