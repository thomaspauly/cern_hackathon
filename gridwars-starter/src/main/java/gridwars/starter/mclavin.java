package gridwars.starter;

import cern.ais.gridwars.api.Coordinates;
import cern.ais.gridwars.api.UniverseView;
import cern.ais.gridwars.api.bot.PlayerBot;
import cern.ais.gridwars.api.command.MovementCommand;

import java.util.List;



/**
 * Simple bot that expands into all directions if there is a cell that does not belong to the bot
 */
public class mclavin implements PlayerBot {

    int turnNum = 0;
    int count = 0;
    boolean moveORexpand = true;
    MovementCommand.Direction direction5;

    // private final MovementCommand.Direction direction2;


    // public mclavin() {
	// 	this(MovementCommand.Direction.LEFT);
	// }

	// public mclavin(MovementCommand.Direction direction2) {
	// 	this.direction2 = direction2;
	// }

    public void getNextCommands(UniverseView universeView, List<MovementCommand> commandList) {
        List<Coordinates> myCells = universeView.getMyCells();
        turnNum+=1;
        count+=1;
        // System.out.print("=========");
        // System.out.print(count);
        // System.out.print("*********");
         
        if (turnNum%4 ==0){
            direction5 = MovementCommand.Direction.UP;
            }
        else if(turnNum%4 ==1){
            direction5 = MovementCommand.Direction.RIGHT;
        }
        else if(turnNum%4 ==2){
            direction5 = MovementCommand.Direction.DOWN;
        }
        else if(turnNum%4 ==3){
            direction5 = MovementCommand.Direction.LEFT;
        }

        // System.out.print("=========");
        // System.out.print(direction5);
        // System.out.print("*********");

        if (count>=2){
            moveORexpand = !moveORexpand;
            count = 0;
        }

        for (Coordinates cell : myCells) {
            int currentPopulation = universeView.getPopulation(cell);
            
            
            
            if ( moveORexpand) {
                if (currentPopulation>5) {
                    // System.out.print(moveORexpand);
                    // int split = 1;

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
	            MovementCommand movementCommand = new MovementCommand(cell, direction5, currentPopulation / 2);
	            commandList.add(movementCommand);
                }
            }

        }
    }
}
