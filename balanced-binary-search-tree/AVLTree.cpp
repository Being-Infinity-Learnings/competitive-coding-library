#include <iostream>
#include <algorithm>

// Define the AVLTreeNode structure
struct AVLTreeNode {
    int val;
    int height;
    AVLTreeNode* left;
    AVLTreeNode* right;

    AVLTreeNode(int k) : val(k), height(1), left(nullptr), right(nullptr) {}
};

class AVLTree {
public:
    int height(AVLTreeNode* node) {
        return (node == nullptr) ? 0 : node->height;
    }

    int getBalancingFactor(AVLTreeNode* node) {
        return (node == nullptr) ? 0 : height(node->left) - height(node->right);
    }

    // Right Rotation (LL Case)
    AVLTreeNode* rightRotate(AVLTreeNode* y) {
        AVLTreeNode* x = y->left;
        AVLTreeNode* T2 = x->right;

        // Perform rotation
        x->right = y;
        y->left = T2;

        // Update heights
        y->height = std::max(height(y->left), height(y->right)) + 1;
        x->height = std::max(height(x->left), height(x->right)) + 1;

        return x;
    }

    // Left Rotation (RR Case)
    AVLTreeNode* leftRotate(AVLTreeNode* x) {
        AVLTreeNode* y = x->right;
        AVLTreeNode* T2 = y->left;

        // Perform rotation
        y->left = x;
        x->right = T2;

        // Update heights
        x->height = std::max(height(x->left), height(x->right)) + 1;
        y->height = std::max(height(y->left), height(y->right)) + 1;

        return y;
    }

    AVLTreeNode* insert(AVLTreeNode* node, int key) {
        if (node == nullptr)
            return new AVLTreeNode(key);

        if (key < node->val)
            node->left = insert(node->left, key);
        else if (key > node->val)
            node->right = insert(node->right, key);
        else
            return node; // Duplicate keys not allowed

        // Update height
        node->height = 1 + std::max(height(node->left), height(node->right));

        // Get balance factor
        int bf = getBalancingFactor(node);

        // LL Case
        if (bf > 1 && key < node->left->val)
            return rightRotate(node);

        // RR Case
        if (bf < -1 && key > node->right->val)
            return leftRotate(node);

        // LR Case
        if (bf > 1 && key > node->left->val) {
            node->left = leftRotate(node->left);
            return rightRotate(node);
        }

        // RL Case
        if (bf < -1 && key < node->right->val) {
            node->right = rightRotate(node->right);
            return leftRotate(node);
        }

        return node;
    }

    AVLTreeNode* minValueNode(AVLTreeNode* node) {
        AVLTreeNode* current = node;
        while (current->left != nullptr)
            current = current->left;
        return current;
    }

    AVLTreeNode* deleteNode(AVLTreeNode* root, int key) {
        if (root == nullptr)
            return root;

        if (key < root->val)
            root->left = deleteNode(root->left, key);
        else if (key > root->val)
            root->right = deleteNode(root->right, key);
        else {
            if ((root->left == nullptr) || (root->right == nullptr)) {
                AVLTreeNode* temp = (root->left) ? root->left : root->right;

                if (temp == nullptr) {
                    temp = root;
                    root = nullptr;
                }
                else { 
                    AVLTreeNode* oldRoot = root;
                    root = temp; 
                    temp = oldRoot;
                }
                
                // Explicitly free memory (C++ specific)
                delete temp;
            }
            else {
                AVLTreeNode* temp = minValueNode(root->right);
                root->val = temp->val;
                root->right = deleteNode(root->right, temp->val);
            }
        }

        if (root == nullptr)
            return root;

        root->height = std::max(height(root->left), height(root->right)) + 1;
        int bf = getBalancingFactor(root);

        // LL Case
        if (bf > 1 && getBalancingFactor(root->left) >= 0)
            return rightRotate(root);

        // LR Case
        if (bf > 1 && getBalancingFactor(root->left) < 0) {
            root->left = leftRotate(root->left);
            return rightRotate(root);
        }

        // RR Case
        if (bf < -1 && getBalancingFactor(root->right) <= 0)
            return leftRotate(root);

        // RL Case
        if (bf < -1 && getBalancingFactor(root->right) > 0) {
            root->right = rightRotate(root->right);
            return leftRotate(root);
        }

        return root;
    }

    void inorder(AVLTreeNode* root) {
        if (root != nullptr) {
            inorder(root->left);
            std::cout << root->val << " ";
            inorder(root->right);
        }
    }
};
