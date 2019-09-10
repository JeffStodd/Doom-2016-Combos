
public class Weapon {
	private String name;
	
	private int damage;
	private double ready;
	private double cooldown;

	public Weapon(String name, int damage, double ready, double cooldown) {
		super();
		this.name = name;
		this.damage = damage;
		this.ready = ready;
		this.cooldown = cooldown;
	}

	
	
	public double getReady() {
		return ready;
	}



	public void setReady(double ready) {
		this.ready = ready;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public double getCooldown() {
		return cooldown;
	}

	public void setCooldown(double cooldown) {
		this.cooldown = cooldown;
	}
	
}
