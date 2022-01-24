/*Brian Pan 112856241 Recitation 02*/

import java.util.*;

/**
 * This class runs and designs an adventure that the user will have to input
 */
public class AdventureDesigner {
    /**
     * Allows for user inputs to create Adventure game
     */
    public static void playGame(){
        Scanner stdin = new Scanner(System.in);
        SceneTree newAdventure = new SceneTree();
        String title, sceneDescription;
        System.out.println("Creating a story...\n");
        System.out.println("Please enter a title");
        title = stdin.nextLine();
        System.out.println("Please enter a scene");
        sceneDescription = stdin.nextLine();
        newAdventure.addNewNode(title, sceneDescription);
        newAdventure.setCursor(newAdventure.getRoot());
        boolean quit = false;
        do {
            System.out.println("A) Add Scene\n" +
                    "R) Remove Scene\n" +
                    "S) Show Current Scene\n" +
                    "P) Print Adventure Tree\n" +
                    "B) Go Back A Scene\n" +
                    "F) Go Forward A Scene\n" +
                    "G) Play Game\n" +
                    "N) Print Path To Cursor\n" +
                    "M) Move scene\n" +
                    "Q) Quit");

            String selection = stdin.nextLine().toUpperCase();

            switch (selection){
                case "A":
                    try {
                        System.out.println("Please enter a title");
                        title = stdin.nextLine();
                        System.out.println("Please enter a scene");
                        sceneDescription = stdin.nextLine();
                        newAdventure.addNewNode(title, sceneDescription);
                    }catch (FullSceneException ex){
                        System.out.println("Scene is full, new scene was not added"+ '\n');
                    }catch (IllegalArgumentException ex){
                        System.out.println("Invalid input"+ '\n');
                    }
                    break;
                case "R":
                    try {
                        System.out.println("Please enter an option");
                        String option = stdin.nextLine().toUpperCase();
                        newAdventure.removeScene(option);
                    }catch (NoSuchNodeException ex){
                        System.out.println("That option does not exist."+ '\n');
                    }catch (IllegalArgumentException ex){
                        System.out.println("Invalid input."+ '\n');
                    }
                    break;
                case "S":
                    newAdventure.getCursor().displayFullScene();
                    break;
                case "P":
                    System.out.println(newAdventure.toString(newAdventure.getRoot(), "     ") + "\n");
                    break;
                case "B":
                    try {
                        newAdventure.moveCursorBackwards();
                    }catch (NoSuchNodeException ex){
                        System.out.println("There is no scene to go back to"+ '\n');
                    }
                    break;
                case "F":
                    try {
                        System.out.println("Which option do you wish to go to: ");
                        String option = stdin.nextLine().toUpperCase();
                        newAdventure.moveCursorForwards(option);
                    }catch (NoSuchNodeException ex){
                        System.out.println("That option does not exist."+ '\n');
                    }
                    break;
                case "G":
                    newAdventure.setCursor(newAdventure.returnToRoot(newAdventure.getCursor()));
                    System.out.println("Now beginning game...");
                    while(!newAdventure.getCursor().isEnding()){
                        newAdventure.getCursor().displayScene();
                        System.out.println("Please enter an option: ");
                        String option = stdin.nextLine().toUpperCase();
                        newAdventure.moveCursorForwards(option);
                    }
                    newAdventure.getCursor().displayScene();
                    System.out.println("\nThe End");
                    break;
                case "N":
                    System.out.println(newAdventure.getPathFromRoot(newAdventure.getCursor()));
                    break;
                case "M":
                    try {
                        System.out.println("Move current scene to: ");
                        int sceneIDToMoveTo = stdin.nextInt();
                        stdin.nextLine();
                        newAdventure.moveScene(sceneIDToMoveTo);
                    }catch (NoSuchNodeException ex){
                        System.out.println("That scene does not exist."+ '\n');
                    }catch (FullSceneException ex){
                        System.out.println("That scene has no more room for any other options"+ '\n');
                    }catch (InputMismatchException ex){
                        System.out.println("Invalid input"+ '\n');
                    }
                    break;
                case "Q":
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid menu input"+ '\n');
                    break;
            }
        }
        while (!quit);
        System.out.println("Program terminating normally...");
    }

    /**
     * Launches the Adventure Game
     * @param args
     */
    public static void main(String []args){
        playGame();
    }
}
