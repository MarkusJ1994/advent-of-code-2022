package day7;

import java.util.List;

public class FileSystem {

    public static final int MAX_SIZE = 70000000;

    private final List<String> structureAsCommands;

    private final Directory root = new Directory("/", null, 1);
    private Directory currentDirectory;


    public FileSystem(List<String> structureAsCommands) {
        this.structureAsCommands = structureAsCommands;
    }

    public Directory getRoot() {
        return root;
    }

    public void interpretStructure() {
        structureAsCommands.forEach(cmdLine -> interpretCommand(cmdLine));
    }

    private void interpretCommand(String command) {
        if (isUserCommand(command)) {
            handleUserCommand(command);
        } else {
            handleCommandOutput(command);
        }
    }

    private void handleCommandOutput(String output) {
        String[] out = output.split(" ");
        switch (out[0]) {
            case "dir" ->
                    currentDirectory.addDirectory(new Directory(out[1], currentDirectory, currentDirectory.getDepth() + 1));
            //Consider default a file
            default -> {
                //ex: 123 abc => file with name abc and size 123
                currentDirectory.addFile(new File(out[1], Integer.parseInt(out[0])));
            }
        }
    }

    private void handleUserCommand(String command) {
        String[] cmd = command.split(" ");
        switch (cmd[1]) {
            case "ls" -> handleLs();
            case "cd" -> handleCd(cmd[2]);
            default -> throw new IllegalArgumentException("Can't interpret user command");
        }
    }

    private void handleLs() {
        //Do nothing?
    }

    private void handleCd(String folder) {
        if (folder.equals(root.getName())) {
            currentDirectory = root;
        } else if (folder.equals("..")) {
            currentDirectory = currentDirectory.getParent();
        } else {
            Directory directory = currentDirectory.getDirectories().stream().filter(dir -> folder.equals(dir.getName())).findFirst().orElseThrow();
            currentDirectory = directory;
        }
    }

    private boolean isUserCommand(String command) {
        return command != null && !command.isEmpty() && command.charAt(0) == '$';
    }

}
