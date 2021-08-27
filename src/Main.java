import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" +
                "+N - прыгни на N шагов направо\n" +
                "-N - прыгни на N шагов налево\n" +
                "<< - Undo (отмени последнюю команду)\n" +
                ">> - Redo (повтори отменённую команду)\n" +
                "!! - повтори последнюю команду\n" +
                "0 - выход\n");

        Frog frog = new Frog();
        List<FrogCommand> commands = new ArrayList<>();
        int curCommand = -1;

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("0")) {
                break;
            }

            if (input.equals("<<")) {
                if (curCommand < 0) {
                    System.out.println("Нечего отменять!");
                } else {
                    commands.get(curCommand).undo();
                    curCommand--;
                }

            } else if (input.equals(">>")) {
                if (curCommand == commands.size() - 1) {
                    System.out.println("Нечего отменять!");
                } else {
                    curCommand++;
                    commands.get(curCommand).make();
                }

            } else if (input.equals("!!")) {
                if (curCommand < 0) {
                    System.out.println("Нет последней команды!");
                } else {
                    if (curCommand != commands.size() - 1) {
                        for (int i = commands.size() - 1; i > curCommand; i--) {
                            commands.remove(i);
                        }
                    }
                    if (frog.insideInterval) {
                        commands.get(curCommand).make();
                        commands.add(commands.get(curCommand));
                        curCommand++;
                    }
                }

            } else if (input.matches("([+\\-])(\\d|\\d{2})")) {
                int steps = Integer.parseInt(input);

                if (curCommand != commands.size() - 1) {
                    for (int i = commands.size() - 1; i > curCommand; i--) {
                        commands.remove(i);
                    }
                }
                FrogCommand cmd = FrogCommands.jumpRightCommand(frog, steps);
                cmd.make();
                if (frog.insideInterval) {
                    curCommand++;
                    commands.add(cmd);
                }

            } else {
                System.out.println("Введена неправильная команда!");
            }

            int j = frog.getPosition();
            for (int i = Frog.MIN_POSITION; i <= Frog.MAX_POSITION; i++) {
                if (i == j) {
                    System.out.print("[X] ");
                } else {
                    System.out.print("[ ] ");
                }
            }
            //System.out.println("Размер списка: " + commands.size() + ", позиция курсора: " + curCommand);
            System.out.println();
        }
    }
}
