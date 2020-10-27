package StatechartSkeleton;

import java.util.Scanner;
// TODO: Implement a singleton abstract factory
public class MainApp {
    public static void main(String []args){
        Scanner in = new Scanner(System.in);
        // TODO: instantiate the factory.
    //    Dishwasher dishwasher = new Dishwasher();
      //  dishwasher.startBehaviour();
      //  String command = null;
        do {
            System.out.print("Enter event name or q to exit: ");
            command = in.nextLine();
            dishwasher.react(command);
        }while (!command.equals("q"));
    }
}
