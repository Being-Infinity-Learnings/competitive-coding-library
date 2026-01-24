class AVLTreeNode {
    constructor(val) {
        this.val = val;
        this.height = 1;
        this.left = null;
        this.right = null;
    }
}

class AVLTree {
    
    // Helper to get height
    height(node) {
        return node ? node.height : 0;
    }

    // Helper to get balance factor
    getBalancingFactor(node) {
        return node ? this.height(node.left) - this.height(node.right) : 0;
    }

    // Right Rotation (LL Case)
    rightRotate(y) {
        let x = y.left;
        let T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(this.height(y.left), this.height(y.right)) + 1;
        x.height = Math.max(this.height(x.left), this.height(x.right)) + 1;

        return x;
    }

    // Left Rotation (RR Case)
    leftRotate(x) {
        let y = x.right;
        let T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(this.height(x.left), this.height(x.right)) + 1;
        y.height = Math.max(this.height(y.left), this.height(y.right)) + 1;

        return y;
    }

    insert(node, key) {
        // 1. Perform standard BST insertion
        if (!node) {
            return new AVLTreeNode(key);
        }

        if (key < node.val) {
            node.left = this.insert(node.left, key);
        } else if (key > node.val) {
            node.right = this.insert(node.right, key);
        } else {
            return node; // Duplicate keys not allowed
        }

        // 2. Update height
        node.height = 1 + Math.max(this.height(node.left), this.height(node.right));

        // 3. Get balance factor
        let bf = this.getBalancingFactor(node);

        // 4. If unbalanced, handle 4 cases

        // LL Case
        if (bf > 1 && key < node.left.val) {
            return this.rightRotate(node);
        }

        // RR Case
        if (bf < -1 && key > node.right.val) {
            return this.leftRotate(node);
        }

        // LR Case
        if (bf > 1 && key > node.left.val) {
            node.left = this.leftRotate(node.left);
            return this.rightRotate(node);
        }

        // RL Case
        if (bf < -1 && key < node.right.val) {
            node.right = this.rightRotate(node.right);
            return this.leftRotate(node);
        }

        return node;
    }

    minValueNode(node) {
        let current = node;
        while (current.left !== null) {
            current = current.left;
        }
        return current;
    }

    deleteNode(root, key) {
        // 1. Perform standard BST delete
        if (!root) {
            return root;
        }

        if (key < root.val) {
            root.left = this.deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = this.deleteNode(root.right, key);
        } else {
            // Node with only one child or no child
            if (!root.left || !root.right) {
                let temp = root.left ? root.left : root.right;

                if (!temp) {
                    // No child case
                    temp = root;
                    root = null;
                } else {
                    // One child case
                    root = temp;
                }
            } else {
                // Node with two children
                let temp = this.minValueNode(root.right);
                root.val = temp.val;
                root.right = this.deleteNode(root.right, temp.val);
            }
        }

        if (!root) {
            return root;
        }

        // 2. Update height
        root.height = Math.max(this.height(root.left), this.height(root.right)) + 1;

        // 3. Get balance factor
        let bf = this.getBalancingFactor(root);

        // 4. If unbalanced, handle 4 cases

        // LL Case
        if (bf > 1 && this.getBalancingFactor(root.left) >= 0) {
            return this.rightRotate(root);
        }

        // LR Case
        if (bf > 1 && this.getBalancingFactor(root.left) < 0) {
            root.left = this.leftRotate(root.left);
            return this.rightRotate(root);
        }

        // RR Case
        if (bf < -1 && this.getBalancingFactor(root.right) <= 0) {
            return this.leftRotate(root);
        }

        // RL Case
        if (bf < -1 && this.getBalancingFactor(root.right) > 0) {
            root.right = this.rightRotate(root.right);
            return this.leftRotate(root);
        }

        return root;
    }

    inorder(root) {
        if (root) {
            this.inorder(root.left);
            process.stdout.write(root.val + " "); // Print without newline
            this.inorder(root.right);
        }
    }
}
