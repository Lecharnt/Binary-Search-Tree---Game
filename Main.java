import java.util.Scanner;

// treenode represents each node in the binary tree
class TreeNode {
    String question;
    TreeNode yes;
    TreeNode no;
    public TreeNode(String question) {
        this.question = question;
    }
}
class BinaryTree {
    TreeNode root; // root node of the binary tree
    int nodeCount; // total number of nodes in the tree
    public BinaryTree() {
        root = null; // initialize root as null
    }
    // method to insert a new question node into the tree
    public void insert(String question) {
        root = insertRecursive(root, question);
    }
    // to insert a new question node into the tree
    private TreeNode insertRecursive(TreeNode root, String question) {
        // If current node is null, create a new node
        if (root == null) {
            root = new TreeNode(question);
            nodeCount++; // Increment node count
            return root;
        }
        // traverse left if the yes child is null
        if (root.yes == null) {
            root.yes = insertRecursive(root.yes, question);
        }
        // traverse right if the no child is null
        else if (root.no == null) {
            root.no = insertRecursive(root.no, question);
        }
        return root;
    }
    public void playGame() {
        // initialize the initial questions and answers
        root = new TreeNode("Is it an animal?");
        root.yes = new TreeNode("Does it have four legs?");
        root.no = new TreeNode("Does it fly?");
        root.yes.yes = new TreeNode("Is it a cat?");
        root.yes.no = new TreeNode("Is it a dog?");
        root.no.yes = new TreeNode("Is it a bird?");
        root.no.no = new TreeNode("Is it a fish?");
        // a scanner to read user input
        Scanner scanner = new Scanner(System.in);
        TreeNode currentNode = root; //initialize current node as root
        while (true) {
            System.out.println(currentNode.question); // print current question
            System.out.print("Yes or no: ");
            String yesOrNo = scanner.nextLine().toLowerCase(); // read user input
            if (yesOrNo.equals("yes")) {
                // if yes move to the yes child node
                if (currentNode.yes == null) {
                    System.out.println("I win");
                    break; // end game if leaf node is reached
                } else {
                    currentNode = currentNode.yes; // move to yes child
                }
            } else if (yesOrNo.equals("no")) {
                // If no move to the no child node
                if (currentNode.no == null) {
                    // if leaf node is reached let user input a new animal and question
                    System.out.println("You win");
                    System.out.print("What animal were you thinking of: ");
                    String newAnimal = scanner.nextLine();
                    System.out.println("Give a yes or no question to input to guess " + newAnimal + " from " + currentNode.question);
                    System.out.print("Your question: ");
                    String newQuestion = scanner.nextLine();
                    System.out.print("If " + newAnimal + " is the correct answer to your question yes or no? ");
                    String newAnswer = scanner.nextLine().toLowerCase();
                    // update the current node with new question and answers
                    if (newAnswer.equals("yes")) {
                        currentNode.no = new TreeNode(currentNode.question);
                        currentNode.yes = new TreeNode(newAnimal);
                        currentNode.question = newQuestion;
                    } else if (newAnswer.equals("no")) {
                        currentNode.yes = new TreeNode(currentNode.question);
                        currentNode.no = new TreeNode(newAnimal);
                        currentNode.question = newQuestion;
                    } else {
                        System.out.println("Please enter yes or no");
                    }
                    currentNode = root; // reset current node to root
                } else {
                    currentNode = currentNode.no; // move to no child
                }
            }
        }
    }
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.playGame();
    }
}
