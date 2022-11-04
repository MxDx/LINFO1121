package searching;

/**
 * You are given a binary search tree (BST) whose nodes implement the BinaryNode and KeyNode interfaces
 * available in the utils package.
 *
 * We ask you to complete the ceil method, which take as argument the root of a BST and an integer `value`.
 * This method finds a node `N` in the BST such that
 *  - Its value is greater or equal than `value`
 *  - No nodes whose value is greater than `value` has a value lower than `N`
 *  and returns its value.
 *  If no such node exists, the method returns null.
 *
 *  For example, consider the following tree
 *
 *                              12
 *                              |
 *                 8 ------------------------ 18
 *                  |                          |
 *           3 ------------ 11       14 -------------- 20
 *                          |        |
 *                     9 ---         --- 15
 *
 * - The ceiled valued of 11 is 11
 * - The ceiled valued of 4 is 8
 * - The ceiled valued of 21 is null
 */
public class BinarySearchTree {

    public static void main(String[] args) {
        BinarySearchTree.BSTNode<Integer> root = new BinarySearchTree.BSTNode<>(12);
        int [] values = new int []{8, 18, 3, 11, 14, 20, 9, 15};
        for (int i = 0; i < values.length; i++) {
            root.add(values[i]);
        }
        System.out.println("Test Ceil");
        System.out.println("Test for 11: " + BinarySearchTree.ceil(root, 11));
        System.out.println("Test for 4: " + BinarySearchTree.ceil(root, 4));
        System.out.println("Test for 21: " + BinarySearchTree.ceil(root, 21));
        System.out.println("Test for 13: " + BinarySearchTree.ceil(root, 13));

        System.out.println("\nTest floor");
        System.out.println("Test for 11: " + BinarySearchTree.floor(root, 11));
        System.out.println("Test for 4: " + BinarySearchTree.floor(root, 4));
        System.out.println("Test for 21: " + BinarySearchTree.floor(root, 21));
        System.out.println("Test for 13: " + BinarySearchTree.floor(root, 1));
    }

    /**
     * Returns the ceiled value of `value` in the tree rooted at `root`
     *
     * @param root the root of the tree
     * @param value the value we want to ceil
     */
    public static Integer ceil(BSTNode<Integer> root, int value) {
        return recCeil(root, value, null);
    }

    public static Integer recCeil(BSTNode<Integer> root, int value, Integer currentBest) {
        if (root == null) {
            return currentBest;
        }
        Integer currentNodeValue = root.getKey();
        if (currentNodeValue == value) {
            return value;
        }
        if (currentNodeValue > value) {
            if (currentBest == null || currentBest > currentNodeValue) {
                return recCeil(root.getLeft(), value, currentNodeValue);
            } else {
                return recCeil(root.getLeft(), value, currentBest);
            }
        } else {
            return recCeil(root.getRight(), value, currentBest);
        }
    }

    /**
     * Returns the floored value of `value` in the tree rooted at `root`
     * @param root the root of the tree
     * @param value the value we want to floor
     */
    public static Integer floor(BSTNode<Integer> root, int value) {
        return recFloor(root, value, null);
    }

    public static Integer recFloor(BSTNode<Integer> root, int value, Integer floor) {
        if (root == null) {
            return floor;
        }
        if (root.getKey() == value) {
            return value;
        }
        if (root.getKey() > value) {
            return recFloor(root.getLeft(), value, floor);
        }
        return recFloor(root.getRight(), value, root.getKey());
    }

    static class BSTNode<K extends Comparable<K>> {

        private BSTNode<K> left;
        private BSTNode<K> right;
        private K key;
        private int size;

        public BSTNode(K key) {
            this.left = null;
            this.right = null;
            this.key = key;
            this.size = 0;
        }

        public BSTNode(K key, int size) {
            this.left = null;
            this.right = null;
            this.key = key;
            this.size = size;
        }

        public BSTNode<K> getLeft() {
            return this.left;
        }

        @SuppressWarnings("unchecked")
        public void setLeft(BSTNode<K> node) {
            this.left = node;
        }

        public BSTNode<K> getRight() {
            return this.right;
        }

        @SuppressWarnings("unchecked")
        public void setRight(BSTNode<K> node) {
            this.right = node;
        }

        public K getKey() {
            return this.key;
        }

        public void setKey(K newKey) {
            this.key = newKey;
        }

        public int getSize() {
            return this.size;
        }

        public void setSize(int newSize) {
            this.size = newSize;
        }

        /**
         * Adds a new value in the subtree rooted a this node
         */
        public void add(K key) {
            if (key.compareTo(this.key) > 0) {
                if (this.right == null) {
                    this.right = new BSTNode<>(key);
                } else {
                    this.right.add(key);
                }
            } else {
                if (this.left == null) {
                    this.left = new BSTNode<>(key);
                } else {
                    this.left.add(key);
                }
            }
        }
    }

}

