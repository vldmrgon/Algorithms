package com.my.pro;

class Node {
    int value;
    Node parent;
    Node left;
    Node right;
    NodeColor color;
}

enum NodeColor {
    BLACK,
    RED
}

public class RedBlackTree {
    private final Node NIL;
    private Node root;

    public RedBlackTree() {
        NIL = new Node();
        NIL.color = NodeColor.BLACK;
        NIL.left = null;
        NIL.right = null;
        root = NIL;
    }

    public void insert(int value) {
        Node newNode = new Node();
        newNode.color = NodeColor.RED;
        newNode.parent = null;
        newNode.value = value;
        newNode.left = NIL;
        newNode.right = NIL;

        insertRecursive(newNode, root);
    }

    private void insertRecursive(Node newNode, Node currentNode) {
        if (currentNode == NIL) {
            newNode.parent = currentNode.parent;
            if (newNode.parent == null) {
                root = newNode;
            } else if (newNode.value < newNode.parent.value) {
                newNode.parent.left = newNode;
            } else {
                newNode.parent.right = newNode;
            }

            if (newNode.parent == null) {
                newNode.color = NodeColor.BLACK;
                return;
            }

            if (newNode.parent.parent == null) {
                return;
            }

            fixInsert(newNode);
        } else {
            if (newNode.value < currentNode.value) {
                insertRecursive(newNode, currentNode.left);
            } else {
                insertRecursive(newNode, currentNode.right);
            }
        }
    }

     private Node searchTreeHelper(Node node, int key) {
        if (node == NIL || key == node.value) {
            return node;
        }

        if (key < node.value) {
            return searchTreeHelper(node.left, key);
        }
        return searchTreeHelper(node.right, key);
    }

    private void fixDelete(Node x) {
        Node s;
        while (x != root && x.color == NodeColor.BLACK) {
            if (x == x.parent.left) {
                s = x.parent.right;
                if (s.color == NodeColor.RED) {
                    s.color = NodeColor.BLACK;
                    x.parent.color = NodeColor.RED;
                    leftRotate(x.parent);
                    s = x.parent.right;
                }

                if (s.left.color == NodeColor.BLACK && s.right.color == NodeColor.BLACK) {
                    s.color = NodeColor.RED;
                    x = x.parent;
                } else {
                    if (s.right.color == NodeColor.BLACK) {
                        s.left.color = NodeColor.BLACK;
                        s.color = NodeColor.RED;
                        rightRotate(s);
                        s = x.parent.right;
                    }

                    s.color = x.parent.color;
                    x.parent.color = NodeColor.BLACK;
                    s.right.color = NodeColor.BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                s = x.parent.left;
                if (s.color == NodeColor.RED) {
                    s.color = NodeColor.BLACK;
                    x.parent.color = NodeColor.RED;
                    rightRotate(x.parent);
                    s = x.parent.left;
                }

                if (s.right.color == NodeColor.BLACK && s.right.color == NodeColor.BLACK) {
                    s.color = NodeColor.RED;
                    x = x.parent;
                } else {
                    if (s.left.color == NodeColor.BLACK) {
                        s.right.color = NodeColor.BLACK;
                        s.color = NodeColor.RED;
                        leftRotate(s);
                        s = x.parent.left;
                    }

                    s.color = x.parent.color;
                    x.parent.color = NodeColor.BLACK;
                    s.left.color = NodeColor.BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = NodeColor.BLACK;
    }

    private void rbTransplant(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    private void deleteNodeHelper(Node node, int key) {
        Node z = NIL;
        Node x, y;
        while (node != NIL) {
            if (node.value == key) {
                z = node;
            }

            if (node.value <= key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (z == NIL) {
            System.out.println("Couldn't find key in the tree");
            return;
        }

        y = z;
        NodeColor yOriginalColor = y.color;
        if (z.left == NIL) {
            x = z.right;
            rbTransplant(z, z.right);
        } else if (z.right == NIL) {
            x = z.left;
            rbTransplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                rbTransplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            rbTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == NodeColor.BLACK) {
            fixDelete(x);
        }
    }

    private void fixInsert(Node k) {
        Node u;
        while (k.parent.color == NodeColor.RED) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == NodeColor.RED) {
                    u.color = NodeColor.BLACK;
                    k.parent.color = NodeColor.BLACK;
                    k.parent.parent.color = NodeColor.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = NodeColor.BLACK;
                    k.parent.parent.color = NodeColor.RED;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;

                if (u.color == NodeColor.RED) {
                    u.color = NodeColor.BLACK;
                    k.parent.color = NodeColor.BLACK;
                    k.parent.parent.color = NodeColor.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = NodeColor.BLACK;
                    k.parent.parent.color = NodeColor.RED;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = NodeColor.BLACK;
    }

    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != NIL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != NIL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }


    private Node minimum(Node node) {
        while (node.left != NIL) {
            node = node.left;
        }
        return node;
    }

    private Node maximum(Node node) {
        while (node.right != NIL) {
            node = node.right;
        }
        return node;
    }

    // Preorder
    private void preOrderHelper(Node node) {
        if (node != NIL) {
            System.out.print(node.value + " ");
            preOrderHelper(node.left);
            preOrderHelper(node.right);
        }
    }

    // Inorder
    private void inOrderHelper(Node node) {
        if (node != NIL) {
            inOrderHelper(node.left);
            System.out.print(node.value + " ");
            inOrderHelper(node.right);
        }
    }

    // Post order
    private void postOrderHelper(Node node) {
        if (node != NIL) {
            postOrderHelper(node.left);
            postOrderHelper(node.right);
            System.out.print(node.value + " ");
        }
    }

    public void printTree() {
        preOrderHelper(this.root);
    }

    public void inOrder() {
        inOrderHelper(this.root);
    }

    public void postOrder() {
        postOrderHelper(this.root);
    }

    public Node searchTree(int k) {
        return searchTreeHelper(this.root, k);
    }

    public void deleteNode(int value) {
        deleteNodeHelper(this.root, value);
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        tree.insert(55);
        tree.insert(40);
        tree.insert(65);
        tree.insert(60);
        tree.insert(75);
        tree.insert(57);

        System.out.println("Tree after insert:");
        tree.printTree();

        tree.deleteNode(40);

        System.out.println("\nAfter delete:");
        tree.printTree();
    }
}