package Service;


import java.util.*;
import java.lang.reflect.*;

/**
 * Created by James on 1/20/2018.
 */

public class CommandTest {
}


class Location {
    private int _x;
    private int _y;

    public Location(int x, int y) {
        _x = x;
        _y = y;
    }

    public int getX() {
        return _x;
    }

    public void setX(int x) {
        _x = x;
    }

    public int getY() {
        return _y;
    }

    public void setY(int y) {
        _y = y;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", _x, _y);
    }
}

class VideoGame {

    private static VideoGame _instance = new VideoGame();

    public static void move(int player, Location newLoc) {
        _instance._move(player, newLoc);
    }

    public static void jump(int player) {
        _instance._jump(player);
    }

    public static void raiseShield(int player) {
        _instance._raiseShield(player);
    }

    public static void swingSword(int player, int angle, int speed) {
        _instance._swingSword(player, angle, speed);
    }


    private VideoGame() {
        System.out.println("Initializing video game");
    }

    private void _move(int player, Location newLoc) {
        System.out.println(String.format("Player %d moved to %s", player, newLoc));
    }

    private void _jump(int player) {
        System.out.println(String.format("Player %d jumped", player));
    }

    private void _raiseShield(int player) {
        System.out.println(String.format("Player %d raised their sheild", player));
    }

    private void _swingSword(int player, int angle, int speed) {
        System.out.println(String.format(
                "Player %d swung their sword at angle %d with speed %d", player, angle, speed));
    }
}

// Command interface

interface Command {
    void execute();
}

// Generic command class


class GenericCommandTester {

    public static void main(String[] args) {
        Command move = new GenericCommand("VideoGame", "move",
                new Class<?>[]{ int.class, Location.class },
                new Object[] { 3 , new Location(75, 12) });
        Command jump = new GenericCommand("VideoGame", "jump",
                new Class<?>[]{ int.class },
                new Object[] { 3 });
        Command raiseShield = new GenericCommand("VideoGame", "raiseShield",
                new Class<?>[]{ int.class },
                new Object[] { 3 });
        Command swingSword = new GenericCommand("VideoGame", "swingSword",
                new Class<?>[]{ int.class, int.class, int.class },
                new Object[] { 3 , 45, 100 });

        List<Command> commands = new ArrayList<Command>();
        commands.add(move);
        commands.add(jump);
        commands.add(raiseShield);
        commands.add(swingSword);

        runCommands(commands);
    }

    private static void runCommands(List<Command> commands) {
        for (Command c : commands) {
            c.execute();
        }
    }
}
