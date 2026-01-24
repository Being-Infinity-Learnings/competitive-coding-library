using System;

public class AVLTree
{
    public class AVLTreeNode
    {
        public int val, height;
        public AVLTreeNode left, right;

        public AVLTreeNode(int val)
        {
            this.val = val;
            this.height = 1;
        }
    }

    // Helper function to get height of the tree
    int Height(AVLTreeNode node)
    {
        return node == null ? 0 : node.height;
    }

    // Get Balance factor of node N
    int GetBalancingFactor(AVLTreeNode node)
    {
        return node == null ? 0 : Height(node.left) - Height(node.right);
    }

    // Right Rotation (LL Case)
    AVLTreeNode RightRotate(AVLTreeNode y)
    {
        AVLTreeNode x = y.left;
        AVLTreeNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.Max(Height(y.left), Height(y.right)) + 1;
        x.height = Math.Max(Height(x.left), Height(x.right)) + 1;

        return x;
    }

    // Left Rotation (RR Case)
    AVLTreeNode LeftRotate(AVLTreeNode x)
    {
        AVLTreeNode y = x.right;
        AVLTreeNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.Max(Height(x.left), Height(x.right)) + 1;
        y.height = Math.Max(Height(y.left), Height(y.right)) + 1;

        return y;
    }

    public AVLTreeNode Insert(AVLTreeNode node, int key)
    {
        // 1. Perform the normal BST insertion
        if (node == null)
            return new AVLTreeNode(key);

        if (key < node.val)
            node.left = Insert(node.left, key);
        else if (key > node.val)
            node.right = Insert(node.right, key);
        else
            return node; // Duplicate keys not allowed

        // 2. Update height of this ancestor node
        node.height = 1 + Math.Max(Height(node.left), Height(node.right));

        // 3. Get the balance factor of this ancestor node
        int bf = GetBalancingFactor(node);

        // 4. If unbalanced, then there are 4 cases

        // LL Case
        if (bf > 1 && key < node.left.val)
            return RightRotate(node);

        // RR Case
        if (bf < -1 && key > node.right.val)
            return LeftRotate(node);

        // LR Case
        if (bf > 1 && key > node.left.val)
        {
            node.left = LeftRotate(node.left);
            return RightRotate(node);
        }

        // RL Case
        if (bf < -1 && key < node.right.val)
        {
            node.right = RightRotate(node.right);
            return LeftRotate(node);
        }

        return node;
    }

    AVLTreeNode MinValueNode(AVLTreeNode node)
    {
        AVLTreeNode current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    public AVLTreeNode Delete(AVLTreeNode root, int key)
    {
        // 1. Perform standard BST delete
        if (root == null)
            return root;

        if (key < root.val)
            root.left = Delete(root.left, key);
        else if (key > root.val)
            root.right = Delete(root.right, key);
        else
        {
            // Node with only one child or no child
            if ((root.left == null) || (root.right == null))
            {
                AVLTreeNode temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                // No child case
                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else // One child case
                    root = temp; 
            }
            else
            {
                // Node with two children: Get the inorder successor
                AVLTreeNode temp = MinValueNode(root.right);
                root.val = temp.val;
                root.right = Delete(root.right, temp.val);
            }
        }

        if (root == null)
            return root;

        // 2. Update height of the current node
        root.height = Math.Max(Height(root.left), Height(root.right)) + 1;

        // 3. Get the balance factor
        int bf = GetBalancingFactor(root);

        // 4. If unbalanced, then there are 4 cases

        // LL Case
        if (bf > 1 && GetBalancingFactor(root.left) >= 0)
            return RightRotate(root);

        // LR Case
        if (bf > 1 && GetBalancingFactor(root.left) < 0)
        {
            root.left = LeftRotate(root.left);
            return RightRotate(root);
        }

        // RR Case
        if (bf < -1 && GetBalancingFactor(root.right) <= 0)
            return LeftRotate(root);

        // RL Case
        if (bf < -1 && GetBalancingFactor(root.right) > 0)
        {
            root.right = RightRotate(root.right);
            return LeftRotate(root);
        }

        return root;
    }

    public void Inorder(AVLTreeNode root)
    {
        if (root != null)
        {
            Inorder(root.left);
            Console.Write(root.val + " ");
            Inorder(root.right);
        }
    }
}
