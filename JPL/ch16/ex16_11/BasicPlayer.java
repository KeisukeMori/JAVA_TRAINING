package ch16.ex16_11;

import java.util.Random;

public class BasicPlayer extends Player{

	@Override
	public void play(Game game) {
		do {
			game.play();
			if(game.getCount() > 8){
				return;
			}
			int x=0;
			int y=0;
			do{
				if(x == 3) {
					y++;
					x = 0;
				}
			}while(!game.setState(x++, y, "○"));
			game.printMessage("player : " + "[" + x + "," + y + "]" + ":" + game.getCount());
		}while(game.setCount() < 9);
	}

}
