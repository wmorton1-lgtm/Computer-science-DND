import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a directory in the file system tree. A directory can contain other directories and
 * files as children.
 */
public class FolderNode extends FileSystemNode {

    private List<FileSystemNode> children;

    public FolderNode(String name, FolderNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }


    @Override
    public boolean isFolder() {
        return true;
    }

    /**
     * Returns a list view of the children contained directly inside this directory. Modifying the
     * returned list is not required to be supported.
     */
    public List<FileSystemNode> getChildren() {
        return children;
    }

    /**
     * Searches the children of this directory for a node whose name matches the input. Only direct
     * children are considered, not deeper descendants.
     */
    public FileSystemNode getChildByName(String childName) {
        for (FileSystemNode child : children) {
            if (child.getName().equals(childName)) {
                return child;
            }
        }
        return null;
    }

    /**
     * Creates a new file directly inside this directory with the given name and size. If a child
     * with the same name already exists, no file is created and false is returned. Otherwise the
     * new file is added and true is returned.
     */
    public boolean addFile(String fileName, int size) {
        // TODO: implement uniqueness check and insertion of a new FileNode
        if (this.getChildByName(fileName) != null) {
            return false;
        }
        FileNode newFile = new FileNode(fileName, this, size);
        children.add(newFile);
        return true;
    }

    /**
     * Creates a new subdirectory directly inside this directory with the given name. If a child
     * with the same name already exists, no folder is created and false is returned. Otherwise the
     * new folder is added and true is returned.
     */
    public boolean addFolder(String folderName) {
        // TODO: implement uniqueness check and insertion of a new FolderNode
        if (this.getChildByName(folderName) != null) {
            return false;
        }
        FolderNode newFolder = new FolderNode(folderName, this);
        children.add(newFolder);
        return true;
    }

    /**
     * Searches this directory and all of its descendants for nodes whose name matches the input.
     * When a match is found, its full path can be printed by the caller using toString().
     */
    public boolean containsNameRecursive(String searchName) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).isFolder() == true) {
                FolderNode temp = (FolderNode) children.get(i);
                temp.containsNameRecursive(searchName);
            } else {
                if (children.get(i).getName().equals(searchName)) {
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public int getHeight() { // todo: // todo: // todo: // todo: // todo:
        int height = 0;
        for (int i = 0; i < children.size(); i++) {
            
            if (!children.get(i).isFolder()) {
                FolderNode thisChild = (FolderNode) children.get(i);
                int nodeHeight = 1 + thisChild.getHeight();
                if (nodeHeight > height) {
                    height = nodeHeight;
                }
            }
        }
        return height;
    }

    @Override
    public int getSize() {
        int size = 0;
        for (int i = 0; i < children.size(); i++) {
            size += children.get(i).getSize();
        }
        return size;
    }

    @Override
    public int getTotalNodeCount() {
        if (this.isFolder()) {
            if (this.getChildren().isEmpty()) {
                return 0;
            }
        }
        int nodeCount = 0;
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).isFolder()) {
                nodeCount++;
                nodeCount += children.get(i).getTotalNodeCount();
            } else {
                nodeCount++;
            }
        }
        return nodeCount;
    }

    // public int NodeCountHelper() {
       
    // }
}
