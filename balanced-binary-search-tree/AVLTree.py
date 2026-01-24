class AVLTreeNode:
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None
        self.height = 1

class AVLTree:
    def get_height(self, node):
        if not node:
            return 0
        return node.height

    def get_balance_factor(self, node):
        if not node:
            return 0
        return self.get_height(node.left) - self.get_height(node.right)

    # Right rotation (LL case)
    def right_rotate(self, y):
        x = y.left
        T2 = x.right

        # Perform rotation
        x.right = y
        y.left = T2

        # Update heights
        y.height = max(self.get_height(y.left), self.get_height(y.right)) + 1
        x.height = max(self.get_height(x.left), self.get_height(x.right)) + 1

        return x

    # Left rotation (RR case)
    def left_rotate(self, x):
        y = x.right
        T2 = y.left

        # Perform rotation
        y.left = x
        x.right = T2

        # Update heights
        x.height = max(self.get_height(x.left), self.get_height(x.right)) + 1
        y.height = max(self.get_height(y.left), self.get_height(y.right)) + 1

        return y

    def insert(self, node, key):
        # 1. Perform the normal BST insertion
        if not node:
            return AVLTreeNode(key)

        if key < node.val:
            node.left = self.insert(node.left, key)
        elif key > node.val:
            node.right = self.insert(node.right, key)
        else:
            return node  # Duplicate keys not allowed

        # 2. Update height of this ancestor node
        node.height = 1 + max(self.get_height(node.left), self.get_height(node.right))

        # 3. Get the balance factor
        bf = self.get_balance_factor(node)

        # 4. If unbalanced, then there are 4 cases

        # LL Case
        if bf > 1 and key < node.left.val:
            return self.right_rotate(node)

        # RR Case
        if bf < -1 and key > node.right.val:
            return self.left_rotate(node)

        # LR Case
        if bf > 1 and key > node.left.val:
            node.left = self.left_rotate(node.left)
            return self.right_rotate(node)

        # RL Case
        if bf < -1 and key < node.right.val:
            node.right = self.right_rotate(node.right)
            return self.left_rotate(node)

        return node

    def get_min_value_node(self, node):
        current = node
        while current.left is not None:
            current = current.left
        return current

    def delete(self, root, key):
        # 1. Perform standard BST delete
        if not root:
            return root

        if key < root.val:
            root.left = self.delete(root.left, key)
        elif key > root.val:
            root.right = self.delete(root.right, key)
        else:
            # Node with only one child or no child
            if root.left is None:
                temp = root.right
                root = None
                return temp
            elif root.right is None:
                temp = root.left
                root = None
                return temp

            # Node with two children: Get the inorder successor
            temp = self.get_min_value_node(root.right)
            root.val = temp.val
            root.right = self.delete(root.right, temp.val)

        # If the tree had only one node then return
        if not root:
            return root

        # 2. Update height of the current node
        root.height = 1 + max(self.get_height(root.left), self.get_height(root.right))

        # 3. Get the balance factor
        bf = self.get_balance_factor(root)

        # 4. If unbalanced, then there are 4 cases

        # LL Case
        if bf > 1 and self.get_balance_factor(root.left) >= 0:
            return self.right_rotate(root)

        # LR Case
        if bf > 1 and self.get_balance_factor(root.left) < 0:
            root.left = self.left_rotate(root.left)
            return self.right_rotate(root)

        # RR Case
        if bf < -1 and self.get_balance_factor(root.right) <= 0:
            return self.left_rotate(root)

        # RL Case
        if bf < -1 and self.get_balance_factor(root.right) > 0:
            root.right = self.right_rotate(root.right)
            return self.left_rotate(root)

        return root

    def inorder(self, root):
        if root:
            self.inorder(root.left)
            print(f"{root.val}", end=" ")
            self.inorder(root.right)
