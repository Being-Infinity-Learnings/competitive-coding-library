class AVLTree {
    class AVLTreeNode {
        int val, height;
        AVLTreeNode left, right;

        AVLTreeNode(int val) {
            this.val = val;
            this.height = 1; 
        }
    }
    
    int height(AVLTreeNode node) {
        return node == null ? 0 : node.height;
    }

    int getBalancingFactor(AVLTreeNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // Right rotation (LL case)
    AVLTreeNode rightRotate(AVLTreeNode y) {
        AVLTreeNode x = y.left;
        AVLTreeNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Left rotation (RR case)
    AVLTreeNode leftRotate(AVLTreeNode x) {
        AVLTreeNode y = x.right;
        AVLTreeNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    AVLTreeNode insert(AVLTreeNode node, int key) {
        if (node == null)
            return new AVLTreeNode(key);

        if (key < node.val)
            node.left = insert(node.left, key);
        else if (key > node.val)
            node.right = insert(node.right, key);
        else
            return node; 

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int bf = getBalancingFactor(node);

        // LL Case
        if (bf > 1 && key < node.left.val)
            return rightRotate(node);

        // RR Case
        if (bf < -1 && key > node.right.val)
            return leftRotate(node);

        // LR Case
        if (bf > 1 && key > node.left.val) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL Case
        if (bf < -1 && key < node.right.val) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    void inorder(AVLTreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.val + " ");
            inorder(root.right);
        }
    }

    AVLTreeNode minValueNode(AVLTreeNode node) {
        AVLTreeNode current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    AVLTreeNode delete(AVLTreeNode root, int key) {
        if (root == null)
            return root;

        if (key < root.val)
            root.left = delete(root.left, key);
        else if (key > root.val)
            root.right = delete(root.right, key);
        else {
            if ((root.left == null) || (root.right == null)) {
                AVLTreeNode temp = (root.left != null) ? root.left : root.right;

                if (temp == null) {
                    root = null;
                }
                else {
                    root = temp;
                }
            }
            else {
                AVLTreeNode temp = minValueNode(root.right);
                root.val = temp.val;
                root.right = delete(root.right, temp.val);
            }
        }

        if (root == null)
            return root;

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int bf = getBalancingFactor(root);

        // LL Case
        if (bf > 1 && getBalancingFactor(root.left) >= 0)
            return rightRotate(root);

        // LR Case
        if (bf > 1 && getBalancingFactor(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // RR Case
        if (bf < -1 && getBalancingFactor(root.right) <= 0)
            return leftRotate(root);

        // RL Case
        if (bf < -1 && getBalancingFactor(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }
}
