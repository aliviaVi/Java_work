package collection;



import java.util.Comparator;
import java.util.Iterator;

public class OurTreeSet<E> implements OurSet<E> {

    TreeNode root;
    Comparator<E> comparator;
    int size;

    public OurTreeSet(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public OurTreeSet() {
        this.comparator = new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                return ((Comparable<E>) o1).compareTo(o2);
            }
        };
    }

    @Override
    public boolean add(E elt) {
        if (root == null) {
            root = new TreeNode();
            root.key = elt;
            size++;
            return true;
        }

        TreeNode<E> parent = root;
        TreeNode<E> current = root;
        while (current != null && comparator.compare(current.key, elt) != 0) {
            parent = current;
            current = comparator.compare(current.key, elt) > 0 ? current.left : current.right;
        }
        if (current != null)
            return false;

        current = new TreeNode<>();
        current.key = elt;
        current.parent = parent;
        size++;
        if (comparator.compare(elt, parent.key) < 0) {
            parent.left = current;
        } else {
            parent.right = current;

        }
        return true;
    }

    @Override
    public boolean remove(E elt) {
        TreeNode<E> current = root;
        TreeNode<E> nodeParent = root;
        boolean isLeft = false;
        while (comparator.compare(current.key, elt) != 0) {
            nodeParent = current;
            if (comparator.compare(current.key, elt) > 0) {
                current = current.left;
                isLeft = true;
            } else {
                current = current.right;
                isLeft = false;
            }
            if (current == null) {
                return false;
            }
        }
        //when node has no child nodes
        if (current.left == null && current.right == null) {
            if (current == root) root = null;
            if (isLeft)
                nodeParent.left = null;
        } else {
            nodeParent.right = null;
        }
        //when node to delete has one child
          if  (current.right == null){
                if (current == root)
                    root = current.left;
            }

        else if (isLeft)
            nodeParent.left = current.left;
        else {
            nodeParent.right = current.left;
        }
        if(current.left==null){
            if (current==root)
                root=current.right;
        }else if(isLeft)
            nodeParent.left=current.right;


        else{
            nodeParent.right=current.right;

    } if(current.left!=null && current.right!=null){

            TreeNode<E> temp=current;
            TreeNode<E> minNode=getMinNode(temp.right);
            current.key=minNode.key;
            if (isLeft)
                nodeParent.left = null;
        }
        return true;
    }


    public TreeNode<E> getMinNode(TreeNode<E> node){
        if(node.left== null)
            return node;
        else{
          return getMinNode(node.left);
        }

    }


   // @Override
//    public boolean remove(E elt) {
//        TreeNode<E> nodeToRemove=getNode(elt);
//
//        if(nodeToRemove==null) return false;
//
//        if(nodeToRemove.left==null|| nodeToRemove.right==null)
//            removeByFirstCase( nodeToRemove);
//        else
//            removeBySecondCase(nodeToRemove);
//    return true;
//        return false;
//    }

    private void removeBySecondCase(TreeNode<E> nodeToRemove) {
    }

    private void removeByFirstCase(TreeNode<E> nodeToRemove) {


    }
    private TreeNode<E> getNode(E elt) {
        TreeNode<E> current = root;

        while (current != null && comparator.compare(current.key, elt) != 0) {
            current = comparator.compare(elt, current.key) < 0 ? current.left : current.right;
        }
        return current;
    }

    @Override
    public boolean contains(E elt) {
        if(root==null){
            return false;
        }
        TreeNode<E> current=root;


        while (current!=null){
            if(comparator.compare(current.key,elt)==0){
                return true;
            }else if(comparator.compare(current.key,elt)>0){
                current=current.left;
            }else{
                current=current.right;
            }
        }
        return false;
    }

    @Override
    public int size() { //return size;
        TreeNode<E> current=root;

        int size = 0;

        if (root == null) return 0;
        while (current!=null){
        size++;
        }
        return size;
    }

    @Override
    public boolean addAll(OurSet<E> other) {
        return false;
    }

    @Override
    public boolean removeAll(OurSet<E> other) {
        return false;
    }

    @Override
    public boolean retainAll(OurSet<E> other) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    private static class TreeNode<E>{
        TreeNode <E> parent;
        TreeNode<E> left;
        TreeNode<E> right;

        E key;
    }
}
