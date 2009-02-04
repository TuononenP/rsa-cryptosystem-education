//Binary Search Tree

import java.*;
import java.lang.*;
//node of the binary tree
class bnode {   
  String key;
  bnode left;
  bnode right;

  bnode() {           
    key = null;
    left = null;
    right = null;
  }

  bnode(String key) {

    this.key = key;
    left = null;
    right = null;
  }
};
   //binary tree
class btree {                
  bnode root;
  btree() {
    root = null;
  }
//insert a node into the tree
  void put(String key) {
    bnode current = root;
    bnode prev = current;
    if (root == null) {
      root = new bnode(key);
    }
    else {
      boolean insert = false;
      while (insert == false) {

        prev = current;
        if (key.compareTo(current.key) < 0) {
          if (current.left == null) {
            current.left = new bnode(key);
            insert = true;
          }
          current = current.left;

        }
        else {
          if (current.right == null) {
            current.right = new bnode(key);
            insert = true;
          }
          current = current.right;
        }

      }

    }

  }
  //delete a node with a key
  boolean delete(String key) {        
    boolean deleted = true;
    bnode current = root;
    bnode prev = current;
    while (current != null) {
      if (key.compareTo(current.key) > 0) {
        prev = current;
        current = current.right;
      }
      else if (key.compareTo(current.key) < 0) {
        prev = current;
        current = current.left;
      }
      else if (key.compareTo(current.key) == 0) {
        deleted = false;
        break;
      }

    }

    if (check(current) == 0) {
      if (current.key.compareTo(prev.key) > 0) {
        prev.right = null;
      }
      else {
        prev.left = null;
      }
    }
    else if (check(current) == 1) {

      if (current.key.compareTo(prev.key) > 0) {
        if (current.left != null) {
          prev.right = current.left;
        }
        else {
          prev.right = current.right;
        }
      }
      else {
        if (current.left != null) {
          prev.left = current.left;
        }
        else {
          prev.left = current.right;
        }
      }
    }
    else if (check(current) == 2) {
      bnode temp = inord(current);
      if (current == root) {
        root.key = temp.key;

      }
      else {
        if (current.key.compareTo(prev.key) > 0) {
          prev.right.key = temp.key;
        }
        else {
          prev.left.key = temp.key;
        }
      }
    }

    return deleted;
  }
//in order
  bnode inord(bnode a) {                    
    int t = 0;
    bnode ret, prev = new bnode();
    prev = a;
    a = a.right;
    while (a.left != null) {
      prev = a;
      a = a.left;
      t = 1;
    }
    ret = a;
    if (t == 0) {
      prev.right = null;
    }
    else {
      prev.left = null;
    }
    a = null;
    return ret;
  }
//check if a node is there
  int check(bnode a) { 
    int ret;
    if ( (a.left != null) && (a.right != null)) {
      ret = 2;
    }
    else if ( (a.left == null) && (a.right == null)) {
      ret = 0;
    }
    else {
      ret = 1;
    }
    return ret;
  }
//print the node
  void printIn(bnode oot) {                  
    if (oot.left != null) {
      printIn(oot.left);
    }
    System.out.println("--------" + oot.key + "----------");
    if (oot.right != null) {
      printIn(oot.right);
    }
  }

  public static void main(String[] args) {

    btree a = new btree();
    a.put("h");
    a.put("g");
    a.put("e");
    a.put("d");
    a.put("f");
    a.put("c");
    a.put("b");
    a.put("a");
    a.put("i");

    a.printIn(a.root);

    a.delete("h");
    a.delete("e");

    a.printIn(a.root);
  }
}