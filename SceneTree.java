/*Brian Pan 112856241 Recitation 02*/

/**
 * This SceneTree object holds the SceneNode doubly linked list
 */
public class SceneTree {
    SceneNode root;
    SceneNode cursor;

    /**
     * Getter method
     * @return
     * The root of the binary tree
     */
    public SceneNode getRoot() {
        return root;
    }

    /**
     * Setter method
     * @param root
     * The root of binary tree
     */
    public void setRoot(SceneNode root) {
        this.root = root;
    }

    /**
     * Getter method
     * @return
     * The cursor node
     */
    public SceneNode getCursor() {
        return cursor;
    }

    /**
     * Setter method
     * @param cursor
     * The cursor node
     */
    public void setCursor(SceneNode cursor) {
        this.cursor = cursor;
    }

    /**
     * No args constructor
     */
    public SceneTree(){}

    /**
     * Moves cursor node to parent
     * @throws NoSuchNodeException
     * Checks to see if the parent node exist
     */
    public void moveCursorBackwards() throws NoSuchNodeException{
        if(cursor.getParent() == null){
            throw new NoSuchNodeException();
        }
        else{
            cursor = cursor.getParent();
            System.out.println("Successfully moved back to " + cursor.getTitle());
        }
    }

    /**
     * Moves cursor to child
     * @param option
     * Choose between right, left, and middle child
     * @throws NoSuchNodeException
     * Checks to see if child node exist
     */
    public void moveCursorForwards(String option) throws NoSuchNodeException{
        switch (option){
            case "A":
                if (cursor.getLeft() == null){
                    throw new NoSuchNodeException();
                }
                else cursor = cursor.getLeft();
                break;
            case "B":
                if (cursor.getMiddle() == null){
                    throw new NoSuchNodeException();
                }
                else cursor = cursor.getMiddle();
                break;
            case "C":
                if (cursor.getRight() == null){
                    throw new NoSuchNodeException();
                }
                else cursor = cursor.getRight();
            default:
                throw new NoSuchNodeException();
        }
        System.out.println("Successfully moved to " + cursor.getTitle() + '\n');
    }

    /**
     * Adds a new node to binary tree
     * @param title
     * Title of node
     * @param sceneDescription
     * Description of node
     */
    public void addNewNode(String title, String sceneDescription){
        SceneNode newNode = new SceneNode(title, sceneDescription);
        if (root == null){
            root = newNode;
            cursor = root;
        }else {
            cursor.addScene(newNode);
            newNode.setParent(cursor);
        }
        System.out.println("Scene #" + newNode.getSceneID() + " added\n");
    }

    /**
     * Removes a node from binary tree
     * @param option
     * Choose between left, middle, and right child
     * @throws NoSuchNodeException
     * Checks to see if child node exist
     */
    public void removeScene(String option) throws NoSuchNodeException{
        switch (option){
            case "A":
                if (cursor.getLeft() == null){
                    throw new NoSuchNodeException();
                }
                else {
                    cursor.setLeft(cursor.getMiddle());
                    cursor.setMiddle(cursor.getRight());
                    cursor.setRight(null);
                    System.out.println("First path removed" + '\n');
                }
                break;
            case "B":
                if (cursor.getMiddle() == null){
                    throw new NoSuchNodeException();
                }
                else {
                    cursor.setMiddle(cursor.getRight());
                    cursor.setRight(null);
                    System.out.println("Second path removed" + '\n');
                }
                break;
            case "C":
                if (cursor.getRight() == null){
                    throw new NoSuchNodeException();
                }
                else {
                    cursor.setRight(null);
                    System.out.println("Third path removed" + '\n');
                }
                break;
            default:
                throw new NoSuchNodeException();
        }
    }

    /**
     * Moves the node to new location
     * @param sceneIDToMoveTo
     * New parent of the moved node
     * @throws NoSuchNodeException
     * Checks to see if node exist
     * @throws FullSceneException
     * Checks to see if node has room for another child
     */
    public void moveScene(int sceneIDToMoveTo) throws NoSuchNodeException, FullSceneException{
        SceneNode sceneToMoveTo = findScene(root, sceneIDToMoveTo);
        if(sceneToMoveTo == null)
            throw new NoSuchNodeException();
        else if(sceneToMoveTo.getLeft() == null){
            sceneToMoveTo.setLeft(cursor);
            cursor.setParent(sceneToMoveTo);
        }
        else if(sceneToMoveTo.getMiddle() == null){
            sceneToMoveTo.setMiddle(cursor);
            cursor.setParent(sceneToMoveTo);
        }
        else if(sceneToMoveTo.getRight() == null){
            sceneToMoveTo.setRight(cursor);
            cursor.setParent(sceneToMoveTo);
        }
        else throw new FullSceneException();
        System.out.println("Successfully moved scene.");
    }

    /**
     * Finds a scene in the binary tree
     * @param scene
     * Scene object to be found
     * @param sceneID
     * Id of scene to be find
     * @return
     * The scene being searched for
     */
    public SceneNode findScene(SceneNode scene, int sceneID) {
        if (scene != null) {
            if (scene.getSceneID() == sceneID) {
                return scene;
            } else {
                SceneNode foundNode = findScene(scene.getLeft(), sceneID);
                if (foundNode == null){
                    foundNode = findScene(scene.getMiddle(), sceneID);
                    if(foundNode == null){
                        foundNode = findScene(scene.getRight(),sceneID);
                    }
                }
                return foundNode;
            }
        }
        else return null;
    }

    /**
     * Binary Tree until the cursor
     * @param path
     * Path from root to cursor
     * @return
     * String of the path from root to cursor
     */
    public String getPathFromRoot(SceneNode path){
        if(path.getParent() == null){
            return path.getTitle();
        }
        else {
            return getPathFromRoot(path.getParent()) + ", " + path.getTitle();
        }
    }

    /**
     * Moves cursor back to root
     * @param cursor
     * Location in binary tree
     * @return
     * Returns the root of binary tree
     */
    public SceneNode returnToRoot(SceneNode cursor){
        if(cursor.getParent() == null) {
            root = cursor;
            return root;
        }
        returnToRoot(cursor.getParent());
        return root;
    }

    /**
     * Converts SceneTree object into a string
     * @param sceneTree
     * Binary tree carrying SceneNode objects
     * @param extender
     * Indenters
     * @return
     * Returns string notation of binary tree
     */
    public String toString(SceneNode sceneTree, String extender){
        String stringRepresentation = "";
        if(sceneTree == null){
            return stringRepresentation;
        }
        else {
            if (sceneTree.getRight() != null) {
                    stringRepresentation = "\n" + extender + "C) " + toString(sceneTree.getRight(), extender + "     ")
                            + stringRepresentation;
            }
            if (sceneTree.getMiddle() != null) {
                    stringRepresentation = "\n" + extender + "B) " + toString(sceneTree.getMiddle(), extender + "     ")
                            + stringRepresentation;
            }
            if (sceneTree.getLeft() != null) {
                    stringRepresentation = "\n" + extender + "A) " + toString(sceneTree.getLeft(), extender + "     ")
                            + stringRepresentation;
            }
        }
        if(sceneTree.equals(cursor))
            return sceneTree.toString() + "*" + stringRepresentation;
        else
            return sceneTree.toString() + stringRepresentation;
    }
}
