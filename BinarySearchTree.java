import java.util.ArrayList;

public class BinarySearchTree {
    Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public BinarySearchTree(Node root) {
        this.root = root;
    }

    public boolean isEmpty() {
        /**
         * check if the tree is empty
         * @return true for yes, false for no
         */
        return root == null;
    }

    public boolean setRoot(Integer e) {
        /**
         * if tree does not have a root, create root node using e and return true, otherwise, return false
         */
        if (root == null) {
            root = new Node(e);
            return true;
        }
        else {
            return false;
        }
    }

    public int depth(Node target){
        /**
         * count and return the depth of node target
         */
        Node current = root;
        int depth = 0;

        while (current != null && current != target) {
            if (target.getElement().compareTo(current.getElement()) < 0) {
                current = current.getLeft();
                depth++;
            }
            else if (target.getElement().compareTo(current.getElement()) > 0) {
                current = current.getRight();
                depth++;
            }
        }
        return depth;
    }

    public int height(Node start){
        /**
         * find and return the height of tree or subtree rooted at start
         */
        if (start == null) {
            return -1;
        }
        int leftHeight = height(start.getLeft());
        int rightHeight = height(start.getRight());
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public Node parent(Node start, Node target, Node parent) {
        /**
         * Given a tree or subtree rooted at start, find the target node and return its parent
         */

        /*recursion
         * if target is root, return base case
         * if start is null, return null
         * if start is target, return parent
         * if target element is smaller than start element, find parent on left subtree
         * if target element is greater than start element, find parent on right subtree
         *
         * */
        if (target == root) {
            return start;
        }
        if (start == null) {
            return null;
        }

        if (start == target) {
            return parent;
        }

        if (target.getElement().compareTo(start.getElement()) < 0) {
            return parent(start.getLeft(), target, start);
        }

        else {
            return parent(start.getRight(), target, start);
        }
    }

    public int numChildren(Node target) {
        /**
         * return the number of children for the node referred by target
         */
        int count=0;
        if(target!= null && target.getLeft() != null) count++;
        if(target != null && target.getRight()!= null) count++;
        return count;
    }

    public boolean isInternal(Node target) {
        /**
         * check if a node target is an Internal node, return true for yes, false for No
         */
        if (target == null) {
            return false;
        }
        if (target.getLeft() != null || target.getRight() != null) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isExternal(Node target) {
        /**
         * check if a node target is an external node, return true for yes, false for No
         */
        if (target.getLeft() == null && target.getRight() == null) {
            return true;
        }
        else {
            return false;
        }
    }

    private Node findMin(Node start) {
        /*
         * based on the implementation shows on the slides, find and return the node contains the minimum elements
         * */
        if (start == null) {
            return null;
        }
        else if(start.getLeft() == null) {
            return start;
        }
        return findMin(start.getLeft());
    }

    private Node findMax(Node start) {
        /*
         * * based on the implementation shows on the slides, find and return the maximum element
         */
        if (start != null) {
            return null;
        }
        else if (start.getRight() == null) {
            return start;
        }
        return findMin(start.getLeft());
    }

    public boolean insert(Integer newElement, Node start) {
        /*
         * based on the insertion implementation shows on the slides, insert a newElement into a tree or subtree rooted at start
         */
        if (start == null) {
            root = new Node(newElement);
            return true;
        }
        int compareResult = newElement.compareTo(start.getElement());

        if (compareResult < 0) {
            if (start.getLeft() == null) {
                start.setLeft(new Node(newElement));
                return true;
            }
            return insert(newElement, start.getLeft());
        }
        else if (compareResult > 0) {
            if (start.getRight() == null) {
                start.setRight(new Node(newElement));
                return true;
            }
            return insert(newElement, start.getRight());
        }
        else {
            return false;
        }
        }

    public Node search(Node start, int target) {
        /*
         * based on the implementation shows on the slides, find and return the node contains the target elements on a tree or subtree rooted at start
         */
        if (start == null || start.getElement() == target) {
            return start;
        }
        if (target < start.getElement()) {
            return search(start.getLeft(), target);
        }
        return search(start.getRight(), target);
    }

    public Node remove(int target, Node start) {
        /*
         * based on the implementation shows on the slides, find and return the maximum elements a target element from a tree or subtree rooted at start
         */
        if (start == null) {
            return null;
        }
        if (target < start.getElement()) {
            start.setLeft(remove(target, start.getLeft()));
        } else if (target > start.getElement()) {
            start.setRight(remove(target, start.getRight()));
        } else {
            if (start.getLeft() == null) {
                return start.getRight();
            } else if (start.getRight() == null) {
                return start.getLeft();
            }
            Node minNode = findMin(start.getRight());
            start = minNode;
            start.setRight(remove(minNode.getElement(), start.getRight()));
        }
        return start;
    }

    public ArrayList<Integer> inOrder(Node start, ArrayList<Integer> list) {
        /*
         * based on the implementation demonstrated in class, return the inorder traversal elements sequence as an Arraylist
         */
        if (start != null) {
            inOrder(start.getLeft(), list);
            list.add(start.getElement());
            inOrder(start.getRight(), list);
        }
        return list;
    }

    public boolean isIdentical(Node xRoot, Node yRoot) {
        /*
         * check if tree rooted at xRoot and yRoot respectively are identical or not, return true for yes and false for no
         */
        //if both roots are null (two empty trees), return true --base case
        //if both root are not null, and values stored are equal, return isIdentical(both left subtree) && isIdentical(both right subtree)--recursive.
        //all other case, return false
        /*********************************************update this**********************************************/
        if (xRoot == null && yRoot == null) {
            return true;
        }
        else if (xRoot.getElement() == yRoot.getElement()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void cutBranch(Node start){
        /*
         * remove the subtree rooted at node start, all nodes in this subtree will be removed
         */
        //recursion: cutBranch my leftsubtree, cutBranch my rightsubtree, remove myself
        if (start == null) {
            return;
        }
        cutBranch(start.getLeft());
        cutBranch(start.getRight());
        if (start == root) {
            root = null;
        }
        else {
            Node parent = parent(start, null, null);
            if (parent.getLeft() == start) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        }
    }
}