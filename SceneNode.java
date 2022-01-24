/*Brian Pan 112856241 Recitation 02*/

/**
 * This SceneNode doubly linked list holds values such as the title of an adventure node, its id, and description
 */
public class SceneNode {
    private String title, sceneDescription;
    private int sceneID;
    private SceneNode left, middle, right, parent;
    private static int numScenes = 0;

    /**
     *
     * @return
     * Getter method that returns the title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * Setter method that sets title for the linked list node
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter method
     * @return
     * returns the description of the adventure scene
     */
    public String getSceneDescription() {
        return sceneDescription;
    }

    /**
     * Setter method
     * @param sceneDescription
     * the description of the adventure scene
     */
    public void setSceneDescription(String sceneDescription) {
        this.sceneDescription = sceneDescription;
    }

    /**
     * Getter method
     * @return
     * Returns the id or serial number of the adventure scene
     */
    public int getSceneID() {
        return sceneID;
    }

    /**
     * Setter method
     * @param sceneID
     * The serial number of the scene
     */
    public void setSceneID(int sceneID) {
        this.sceneID = sceneID;
    }

    /**
     * Getter method
     * @return
     * The left child of the binary tree linked list
     */
    public SceneNode getLeft() {
        return left;
    }

    /**
     * Setter method
     * @param left
     * The left child of the binary tree linked list
     */
    public void setLeft(SceneNode left) {
        this.left = left;
    }

    /**
     * Getter method
     * @return
     * The middle child of the binary tree node
     */
    public SceneNode getMiddle() {
        return middle;
    }

    /**
     * Setter method
     * @param middle
     * The middle child of the binary tree node
     */
    public void setMiddle(SceneNode middle) {
        this.middle = middle;
    }

    /**
     * Getter method
     * @return
     * The right child of binary tree node
     */
    public SceneNode getRight() {
        return right;
    }

    /**
     * Setter method
     * @param right
     * The right child of binary tree node
     */
    public void setRight(SceneNode right) {
        this.right = right;
    }

    /**
     * Getter method
     * @return
     * The parent of binary tree node
     */
    public SceneNode getParent() {
        return parent;
    }

    /**
     *
     * Setter method
     * @param parent
     * The parent of binary tree node
     */
    public void setParent(SceneNode parent) {
        this.parent = parent;
    }

    /**
     * Getter method
     * @return
     * The total number of scenes in the adventure binary tree
     */
    public static int getNumScenes() {
        return numScenes;
    }

    /**
     * Setter method
     * @param numScenes
     * The total number of scenes in the adventure binary tree
     */
    public static void setNumScenes(int numScenes) {
        SceneNode.numScenes = numScenes;
    }

    /**
     * No args constructor
     */
    public SceneNode() {
    }

    /**
     * Args constructor
     * @param title
     * The title of the adventure scene
     * @param sceneDescription
     * The description of the adventure node
     */
    public SceneNode(String title, String sceneDescription){
        this.title = title;
        this.sceneDescription = sceneDescription;
        numScenes++;
        sceneID = numScenes;
    }

    /**
     * Adds a new node to the binary tree
     * @param scene
     * The SceneNode object being added
     * @throws FullSceneException
     * Exception to check if the node is full
     */
    public void addScene(SceneNode scene) throws FullSceneException{
        if (left == null){
            left = scene;
        }
        else if(middle == null){
            middle = scene;
        }
        else if(right == null){
            right = scene;
        }
        else
            throw new FullSceneException();
    }

    /**
     * Checks to see if the node is a leaf
     * @return
     * Returns whether the node is a leaf or not
     */
    public boolean isEnding(){
        return left == null && middle == null && right == null;
    }

    /**
     * Displays the title and description of node and its children
     */
    public void displayScene(){
        System.out.println(title);
        System.out.println(sceneDescription + '\n');
        if (left != null)
            System.out.println("A) " + left.getTitle());
        if (middle != null)
            System.out.println("B) " + middle.getTitle());
        if (right != null)
            System.out.println("C) " + right.getTitle());
    }

    /**
     * Displays all aspects in the Scene, from title, description, id, and children
     */
    public void displayFullScene(){
        System.out.println("Scene ID #" + sceneID);
        System.out.println("Title: " + title);
        System.out.println("Scene: " + sceneDescription);
        System.out.print("Leads to: ");
        if(left == null && middle == null && right == null)
            System.out.println("NONE");
        if(!(left == null))
            System.out.println(left.getTitle() + " (#" + left.getSceneID() + "),");
        if(!(middle == null))
            System.out.println(middle.getTitle() + " (#" + middle.getSceneID()  + "),");
        if(!(right == null))
            System.out.println(right.getTitle() + " (#" + right.getSceneID() + ")f" );
        System.out.println();
    }

    /**
     * String form of the Object
     * @return
     * String representation of the Object
     */
    public String toString(){
        return title + "(#" + sceneID + ")";
    }
}
