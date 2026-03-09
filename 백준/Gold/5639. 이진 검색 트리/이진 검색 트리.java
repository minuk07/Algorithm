import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int root;

    static class Node{
        int num;
        Node left, right;

        Node(int num){
            this.num = num;
        }

        Node(int num, Node left, Node right){
            this.num = num;
            this.left = left;
            this.right = right;
        }

        void insert(int n){
            if(n < this.num){
                if(this.left == null){
                    this.left = new Node(n);
                }
                else{
                    this.left.insert(n);
                }
            }
            else{
                if(this.right == null){
                    this.right = new Node(n);
                }
                else{
                    this.right.insert(n);
                }
            }
        }
    }

    static void postOrder(Node node){
        if(node == null) return;

        postOrder(node.left);
        postOrder(node.right);

        System.out.println(node.num);
    }
    
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int root = Integer.parseInt(br.readLine());

        Node tree = new Node(root);
        while(true){
            String input = br.readLine();
            if(input == null || input.equals("")) break;

            tree.insert(Integer.parseInt(input));
        }

        postOrder(tree);
    }

    
}