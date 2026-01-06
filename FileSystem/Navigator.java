import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles interactive navigation of the file system This class reads commands from standard input,
 * interprets them, and invokes operations on the current directory node.
 */
public class Navigator {

    private final FileSystemTree fileSystem;
    private FolderNode currentDirectory;
    private boolean shouldExit;

    /**
     * Constructs a navigator for a given file system tree. The starting location is the root
     * directory.
     */
    public Navigator(FileSystemTree fst) {
        this.fileSystem = fst;
        this.currentDirectory = fst.getRoot();
    }

    /**
     * Starts a command loop that repeatedly reads a line of input, interprets it as a command with
     * arguments, and executes it until a request to terminate is received.
     */
    public void run() {
        shouldExit = false;
        Scanner kb = new Scanner(System.in);

        while (!shouldExit) {
            // Prompt can be customized to show current path if desired.
            String line = kb.nextLine();
            processUserInputString(line);
        }

        kb.close();
    }

    /**
     * Changes the current directory based on a single path argument. Behavior should mirror typical
     * Unix shells: - "." refers to the current directory (no change). - ".." moves to the parent
     * directory (if one exists). - Paths starting with "/" are interpreted from the root directory.
     * - Other paths are interpreted relative to the current directory.
     */
    private void cd(String[] args) {
        if (args == null) {
            throw new NullPointerException("args was null");
        }
        if (args.length != 1) {
            throw new IllegalArgumentException("cd() invalid parametr");
        }
        if (args[0] == null) {
            throw new NullPointerException("1st arg was null");
        }


        FolderNode node;
        if (args[0].startsWith("/")) {
            node = fileSystem.getRoot();
        } else {
            node = currentDirectory;
        }

        String[] pathParts = args[0].split("/");
        for (int i = 0; i < pathParts.length; i++) {
            String part = pathParts[i];

            if (part.length() == 0 || part.equals(".")) {

            } else if (part.equals("..")) {
                if (node.getParent() != null) {
                    node = node.getParent();
                }
            } else {
                FileSystemNode child = node.getChildByName(part);
                if (child == null || child.isFolder() == false) {
                    return;
                } else {
                    node = (FolderNode) child;
                }
            }
        }
        currentDirectory = node;
    }

    // public String splitPaths(String path) {
    // for (int index = 0; index < path.length(); index++) {
    // if (path.substring(index, index + 1) == "/") {
    // return path.substring(index + 1, path.length());
    // }
    // }
    // return "";
    // }

    /**
     * Lists all items contained directly in the current directory. Output formatting can mirror
     * typical file system listings.
     */

    private void ls(String[] args) {
        List<FileSystemNode> children = currentDirectory.getChildren();
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).isFolder()) {
                System.out.println(children.get(i).getName() + "/");
            } else {
                System.out.println(children.get(i).getName());
            }

        }



    }

    /**
     * Creates a new directory inside the current directory using the provided name.
     */
    private void mkdir(String[] args) {
        if (args == null || args.length != 1 || args[0] == null) {
            throw new IllegalArgumentException();
        }
        // read folder name from args and delegate to currentDirectory.addFolder(...)
        currentDirectory.addFolder(args[0]);
    }

    /**
     * Creates a new file inside the current directory with a given name and size.
     */
    private void touch(String[] args) {
        if (args == null || args.length != 2 || args[0] == null || args[1] == null) {
            throw new IllegalArgumentException();
        }
        currentDirectory.addFile(args[0], Integer.parseInt(args[1]));
    }

    /**
     * Searches the current directory and its descendants for nodes with a given name and prints
     * their paths.
     */
    private void find(String[] args) {
        // TODO: use recursive search starting at currentDirectory\
        String targetName = args[0];
        if (args.length != 1) {
            throw new IllegalArgumentException("find(): wrong number of args");
        }
        if (targetName == null) {
            throw new NullPointerException("find() target string was null");
        }

        ArrayList<String> pathList = new ArrayList<String>();
        makePathList(currentDirectory, targetName, pathList);
        for (String child : pathList) {
            System.out.println(child.toString());
        }
    }

    private void makePathList(FolderNode tempDirectory, String targetName,
            ArrayList<String> pathList) {
        List<FileSystemNode> tempChildren = tempDirectory.getChildren();
        for (int i = 0; i < tempChildren.size(); i++) {
            if (tempChildren.get(i).isFolder()) {
                FolderNode tempChild = (FolderNode) tempChildren.get(i);
                makePathList(tempChild, targetName, pathList);
            } else if (tempChildren.get(i).getName().equals(targetName)) {
                pathList.add(tempChildren.get(i).toString());
            }
        }
    }

    /**
     * Prints the absolute path of the current directory, from the root to this node.
     */
    private void pwd(String[] args) {
        System.out.println(currentDirectory.toString());
    }

    /**
     * Displays the contents of the current directory as a tree, optionally respecting flags or
     * depth limits if provided by the arguments.
     */
    private void tree(String[] args) {
        List<FileSystemNode> children = currentDirectory.getChildren();
        for (int i = 0; i < children.size(); i++) {
            printTree(children.get(i), "");
        }
    }

    private void printTree(FileSystemNode node, String tabsOver) {
        System.out.println(tabsOver + "|---" + node.getName());

        if (node.isFolder()) {
            FolderNode folder = (FolderNode) node;
            List<FileSystemNode> kids = folder.getChildren();
            for (int i = 0; i < kids.size(); i++) {
                String nextTabsOver;
                if (tabsOver.equals("")) {
                    nextTabsOver = "|   ";
                } else {
                    nextTabsOver = tabsOver + "    ";
                }
                printTree(kids.get(i), nextTabsOver);
            }
        }
    }

    /**
     * Prints how many nodes (files and folders) exist in the current directory and all of its
     * subdirectories.
     */
    private void count(String[] args) {
        System.out.println(currentDirectory.getTotalNodeCount() - 1);
    }

    /**
     * Prints the total size of all files reachable from the current directory.
     */
    private void size(String[] args) {
        System.out.println(currentDirectory.getSize());
    }

    /**
     * Prints the depth of the current directory, defined as the number of edges from the root
     * directory down to this directory.
     */
    private void depth(String[] args) {
        System.out.println(currentDirectory.getDepth());
    }

    /**
     * Prints the height of the current directory, defined as the longest downward distance from
     * this directory to any file or subdirectory beneath it. An empty directory has value 0.
     */
    private void height(String[] args) {
        System.out.println(currentDirectory.getHeight());
    }

    /**
     * Signals that the interactive loop should terminate after the current command.
     */
    private void quit(String[] args) {
        shouldExit = true;
    }

    /**
     * Interprets a line of user input by splitting it into a command and arguments, then forwarding
     * control to the appropriate helper method.
     *
     * Example inputs and how they are interpreted: "ls" -> command: "ls" args: []
     *
     * "mkdir docs" -> command: "mkdir" args: ["docs"]
     *
     * "touch notes.txt 100" -> command: "touch" args: ["notes.txt", "100"]
     *
     * "cd .." -> command: "cd" args: [".."]
     */
    public void processUserInputString(String line) {
        if (line == null || line.trim().isEmpty()) {
            return;
        }

        String[] parts = line.trim().split("\\s+");
        String command = parts[0];
        String[] args = new String[parts.length - 1];
        System.arraycopy(parts, 1, args, 0, args.length);

        switch (command) {
            case "cd":
                cd(args);
                break;
            case "ls":
                ls(args);
                break;
            case "mkdir":
                mkdir(args);
                break;
            case "touch":
                touch(args);
                break;
            case "find":
                find(args);
                break;
            case "pwd":
                pwd(args);
                break;
            case "tree":
                tree(args);
                break;
            case "count":
                count(args);
                break;
            case "size":
                size(args);
                break;
            case "depth":
                depth(args);
                break;
            case "height":
                height(args);
                break;
            case "quit":
                quit(args);
                break;
            default:
                // Unknown commands can be reported back to the user.
                System.out.println("Unrecognized command: " + command);
        }
    }
}
