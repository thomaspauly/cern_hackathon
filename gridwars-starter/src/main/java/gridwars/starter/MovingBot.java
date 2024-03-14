package gridwars.starter;

import cern.ais.gridwars.api.Coordinates;
import cern.ais.gridwars.api.UniverseView;
import cern.ais.gridwars.api.bot.PlayerBot;
import cern.ais.gridwars.api.command.MovementCommand;

import java.util.List;



/**
 * Simple bot that expands into all directions if there is a cell that does not belong to the bot
 */
public class MovingBot implements PlayerBot {

    int turnNum = 0;
    boolean moveORexpand = true;

    private final MovementCommand.Direction direction2;


    public MovingBot() {
		this(MovementCommand.Direction.UP);
	}

	public MovingBot(MovementCommand.Direction direction2) {
		this.direction2 = direction2;
	}

    public void getNextCommands(UniverseView universeView, List<MovementCommand> commandList) {
        List<Coordinates> myCells = universeView.getMyCells();
        turnNum+=1;
        // System.out.print("=========");
        // System.out.print(turnNum);
        // System.out.print("*********");

        for (Coordinates cell : myCells) {
            int currentPopulation = universeView.getPopulation(cell);
            
            
            
            if ( turnNum <100 || turnNum > 120) {
                if (currentPopulation>5) {
                    // System.out.print("=========");
                    int split = 1;

                    // for (MovementCommand.Direction direction : MovementCommand.Direction.values()) {
                    //     System.out.print(universeView.belongsToMe(cell.getNeighbour(direction)));
                    //     System.out.print("=========");
                    //     if (!universeView.belongsToMe(cell.getNeighbour(direction))) {
                    //         split++;
                    //     }
                    // }

                    // Expand
                    for (MovementCommand.Direction direction3 : MovementCommand.Direction.values()) {
                        // System.out.print(direction3); 
                        // System.out.print("=========");
                        if (universeView.getPopulation(cell.getNeighbour(direction3)) < currentPopulation){
                        commandList.add(new MovementCommand(cell, direction3, currentPopulation / 4));
                        }
                    }
                 }
            }
            
            else{
                if (currentPopulation > 1) {
	            MovementCommand movementCommand = new MovementCommand(cell, direction2, currentPopulation / 2);
	            commandList.add(movementCommand);
                }
            }

        }
    }
}
