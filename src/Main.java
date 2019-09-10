import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.Scanner;

public class Main {

	static ArrayList<Weapon> weapons = new ArrayList<Weapon>(); 
	static ArrayList<Enemy> demons = new ArrayList<Enemy>();
	
	static Weapon pistol = new Weapon("Pistol", 20, 0, 0.25);
	static Weapon AR = new Weapon("Assault Rifle", 40, 0, 0.15); //500 rpm
	static Weapon RL = new Weapon("Rocket Launcher", 480, 0, 1.1); //130 direct
	static Weapon shotty = new Weapon("Shotgun", 246, 0, 0.9); //100 direct
	static Weapon dShotty = new Weapon("Super Shotgun", 960, 0, 1);
	static Weapon gauss = new Weapon("Gauss Cannon", 650, 0, 1.6);
	
	static Weapon pistol2 = new Weapon("Pistol Charged", 200, 1.5, 1);
	static Weapon AR2 = new Weapon("Micro Missle", 110, 0.15, 0.15); //400 rpm
	static Weapon RL2 = new Weapon("Burst Rocket Launcher", 975, 0.4, 1.5); //130 direct
	static Weapon shotty2 = new Weapon("Shotgun Grenade", 375, 0.1, 2.1); //100 direct
	static Weapon dShotty2 = new Weapon("Double Super Shotgun", 1920, 0, 1);
	static Weapon gauss2 = new Weapon("Siege Gauss Cannon", 1680, 1, 1.8);
	
	static Enemy possessed = new Enemy("Possessed", 150);
	static Enemy imp = new Enemy("Imp", 150);
	static Enemy soldier = new Enemy("Soldier", 400);
	static Enemy shield = new Enemy("Shield Soldier", 500);
	static Enemy knight = new Enemy("Hell Knight", 1800); //1799
	static Enemy razer = new Enemy("Hell Razer", 600);
	static Enemy revenent = new Enemy("Revanent", 1604);
	static Enemy pinky = new Enemy("Pinky", 1000);
	static Enemy spectre = new Enemy("Spectre", 2000);
	static Enemy cacodemon = new Enemy("Cacodemon", 2000);
	static Enemy mancubus = new Enemy("Mancubus", 2500);
	static Enemy cybermancubus = new Enemy("Cybermancubus", 3505);
	static Enemy baron = new Enemy("Baron", 3505);
	

	static LinkedList<Node> leafNodes = new LinkedList<Node>();
	static TreeMap<Double, LinkedList<Node>> combos = new TreeMap<Double, LinkedList<Node>>();
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		initLists();
		
		Node opener = new Node(null);
		opener.setParent(null);
		opener.setGeneration(0);
		
		System.out.println("DOOM 2016 Weapon Combos\n");
		
		System.out.println("0: possessed");
		System.out.println("1: imp");
		System.out.println("2: soldier");
		System.out.println("3: shield");
		System.out.println("4: knight");
		System.out.println("5: razer");
		System.out.println("6: revenent");
		System.out.println("7: pinky");
		System.out.println("8: spectre");
		System.out.println("9: cacodemon");
		System.out.println("10: mancubus");
		System.out.println("11: cybermancubus");
		System.out.println("12: baron");
		
		System.out.print("\nEnter the corresponding number for the demon: ");
		int demon = reader.nextInt();
		System.out.print("Enter the max number of weapons switched and/or times fired: ");
		int generations = reader.nextInt();
		System.out.print("Enter the max value for overdamage (200 recommended): ");
		int overdamage = reader.nextInt();
		System.out.println("\nGenerating combos for " + demons.get(demon).getName());
		
		generateGraph(opener, demons.get(demon).getHealth(), generations, overdamage);
		
		sortCombos();
		
		printCombos();
	}
	
	public static void initLists()
	{
		//weapons.add(pistol);
		weapons.add(AR);
		weapons.add(RL);											
		weapons.add(shotty);
		//weapons.add(dShotty);
		weapons.add(gauss);
		
		//weapons.add(pistol2);
		weapons.add(AR2);
		weapons.add(RL2);											
		weapons.add(shotty2);
		//weapons.add(dShotty2);
		weapons.add(gauss2);
		
		demons.add(possessed);
		demons.add(imp);
		demons.add(soldier);
		demons.add(shield);
		demons.add(knight);
		demons.add(razer);
		demons.add(revenent);
		demons.add(pinky);
		demons.add(spectre);
		demons.add(cacodemon);
		demons.add(mancubus);
		demons.add(cybermancubus);
		demons.add(baron);
	}

	public static void generateGraph(Node opener, int health, int max, int overdamage)
	{
		if(health > 0)
		{
			if(opener.getGeneration() > max)
				return;
			for(Weapon temp: weapons)
			{
				Node next = new Node(temp);
				next.setParent(opener);
				next.setGeneration(next.getParent().getGeneration() + 1);
				opener.getNext().add(next);
				generateGraph(next, health - temp.getDamage(), max, overdamage);
			}
		}
		else if(health > -1 * overdamage)
		{
			opener.setOverdamage(Math.abs(health));
			leafNodes.add(opener); //over damage, node
		}
	}
	
	public static void sortCombos()
	{
		
		for(int i = 0; i < leafNodes.size(); i++)
		{
			Node temp = leafNodes.get(i);
			double time = 0;

			LinkedList<Node> weaponCombo = new LinkedList<Node>();
			
			while(temp.getParent() != null)
			{
				weaponCombo.add(temp);
				time += temp.getWeapon().getReady();
				Weapon prev = temp.getWeapon();
				temp = temp.getParent();
				if(temp.getWeapon() != prev) //swap time
				{
					time += 0.2; //or .25?
				}
				else
				{
					time += temp.getWeapon().getCooldown();
				}
				
			}
			time -= 0.2;
			Collections.reverse(weaponCombo);
			combos.put(time, weaponCombo);
		}
	}
	
	public static void printCombos()
	{
		Object[] keys = combos.keySet().toArray();
		
		for(int i = 0; i < combos.size(); i++)
		{
			System.out.print(Math.round((double)keys[i] * 1000.0) / 1000.0 + " sec: "); //ttk
			LinkedList<Node> temp = combos.get(keys[i]);
			for(Node n: temp)
			{
				System.out.print(n.getWeapon().getName() + " -> ");
			}
			System.out.print(temp.getLast().getOverdamage() + " overdamage");
			System.out.println();
		}
	}
	
}
