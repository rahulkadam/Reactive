package com.leetcode.tree;

import com.leetcode.util.InputFactory;
import com.leetcode.util.TreeNode;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class BST {
    public static void main(String args[]) {
        TreeNode root = createBST();
        printTree(root);
        // printTree(updateNode(root,4 , 54));
        System.out.println("");
//        System.out.println(height(root));
//        printByLevel(root);

        TreeNode root1 = mirrorTree(root);
        printTree(root1);
        //System.out.println(CheckIdentical(root , root1));
        //System.out.println();
    }


    public static void printTree(TreeNode root) {
        if (root != null) {
            printTree(root.left);
            System.out.print(" -> " + root.data);
            printTree(root.right);
        }
    }

    public static TreeNode createBSTWithData(int[] arr) {
        TreeNode root = new TreeNode();
        root.data = arr[0];
        for (int i = 1; i < arr.length; i++) {
            root = insertNodeBST(root, arr[i]);
        }
        return root;
    }

    public static TreeNode createBST() {
        int[] arr = {4, 21, 3, 1, 5};
        TreeNode node = createBSTWithData(arr);
        return node;
    }

    public static TreeNode insertNodeBST(TreeNode root, int n) {
        if (root == null) {
            root = new TreeNode();
            root.data = n;
            return root;
        } else {
            if (root.data < n) {
                root.right = insertNodeBST(root.right, n);
            } else {
                root.left = insertNodeBST(root.left, n);
            }
        }
        return root;
    }

    public static TreeNode deleteNode(TreeNode node, int n) {
        if (node == null) {
            return node;
        }
        if (n < node.data)
            node.left = deleteNode(node.left, n);
        else if (n > node.data)
            node.right = deleteNode(node.right, n);

        if (node.data == n) {
            if (node.left == null && node.right == null) {
                return null;
            }
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            TreeNode inorderPredecessor = node.right;
            int minValue = 0;
            while (inorderPredecessor.left != null) {
                minValue = inorderPredecessor.left.data;
                inorderPredecessor = inorderPredecessor.left;
            }
            node.data = minValue;
            node.right = deleteNode(node.right, minValue);
        }
        return node;
    }

    public static boolean isPresent(TreeNode root, int n) {
        if (root == null) {
            return false;
        }

        if (root.data == n) {
            return true;
        }

        if (root.data > n) {
            return isPresent(root.left, n);
        } else {
            return isPresent(root.right, n);
        }
    }

    public static TreeNode updateNode(TreeNode root, int old, int newValue) {
        root = deleteNode(root, old);
        return insertNodeBST(root, newValue);
    }

    public static int countNodeFromRange(TreeNode root, int from, int to) {
        if (root == null) {
            return 0;
        }
        if (root.data < to && root.data >= from) {
            return 1 + countNodeFromRange(root.left, from, to) + countNodeFromRange(root.right, from, to);
        } else {
            return countNodeFromRange(root.left, from, to) + countNodeFromRange(root.right, from, to);
        }
    }

    public static int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + InputFactory.max(height(root.left), height(root.right));
        }
    }

    public static void printByLevel(TreeNode root) {

        Queue<TreeNode> queue = new LinkedBlockingDeque<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode pop = queue.remove();
            System.out.println(pop.data);
            if(pop.left != null) {
                queue.add(pop.left);
            }
            if(pop.right != null) {
                queue.add(pop.right);
            }
        }
    }

    public static boolean CheckIdentical(TreeNode t1, TreeNode t2) {

        if((t1 == null && t2 != null) || (t2 == null && t1 != null)) {
            return false;
        }

        if(t1== null && t2 == null) {
            return true;
        }

        if(t1.data != t2.data) {
            return false;
        }

        boolean leftcheck = CheckIdentical(t1.left , t2.left);
        boolean rightcheck =  CheckIdentical(t1.right , t2.right);

        if(leftcheck == false || rightcheck == false) {
            return false;
        }
        return true;
    }

    public static TreeNode copyTree(TreeNode tr) {

        if(tr == null) {
            return null;
        }
        TreeNode t = new TreeNode();
        TreeNode left = copyTree(tr.left);
        TreeNode right = copyTree(tr.right);
        t.data = tr.data;
        t.left = left;
        t.right = right;
        return t;
    }

    public static TreeNode mirrorTree(TreeNode tr) {

        if(tr == null) {
            return null;
        }
        TreeNode t = new TreeNode();
        TreeNode left = mirrorTree(tr.right);
        TreeNode right = mirrorTree(tr.left);
        t.data = tr.data;
        t.left = left;
        t.right = right;
        return t;
    }

    public static TreeNode LCA(TreeNode t , int data) {
        if(t == null) {
            return null;
        }
        if(t.data == data) {
            return null;
        }

        if(t.left != null && t.left.data == data) {
            return t;
        }
        if(t.right != null && t.right.data == data) {
            return t;
        }
        TreeNode left = LCA(t.left , data);
        TreeNode right = LCA(t.right , data);
        if(left != null) {
            return left;
        }
        return right;
    }
}
