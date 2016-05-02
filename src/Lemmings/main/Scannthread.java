package Lemmings.main;

import java.util.Scanner;

public class Scannthread  extends Thread{
	
	
	 boolean keyPressed;
	
	
	public Scannthread() {
		this.keyPressed = false;
	}

	@Override
	public void run(){
		//System.out.println("'p' pour pause.. (5seconde)");
		Scanner keyboard = new Scanner(System.in);
		char mykey = '~';
		mykey = keyboard.next(".").charAt(0);
		if (mykey == 'p')
			this.keyPressed = true;
		else this.run();
	}
	
	boolean keyPressed() {
		return this.keyPressed;
	}
}
