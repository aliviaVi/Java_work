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
            if (isLeft) {
                nodeParent.left = null;
            } else {
                nodeParent.right = null;
            }
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
        size--;
        return true;
    }


    public TreeNode<E> getMinNode(TreeNode<E> node){
        if(node.left== null)
            return node;
        else{
          return getMinNode(node.left);
        }

    }



    public boolean remove2(E elt) {
        TreeNode<E> nodeToRemove=getNode(elt);

        if(nodeToRemove==null) return false;

        if(nodeToRemove.left==null|| nodeToRemove.right==null)
            linealRemove( nodeToRemove);
        else
            junctionRemove(nodeToRemove);
        size--;
    return true;

    }

    private void junctionRemove(TreeNode<E> nodeToRemove) {
        TreeNode<E> needle=nodeToRemove.right;
        while (needle.left!=null)
            needle=needle.left;

        nodeToRemove.key=needle.key;
        linealRemove(needle);
    }

    private void linealRemove(TreeNode<E> nodeToRemove) {
        TreeNode<E> parent=nodeToRemove.parent;
        TreeNode<E> child=nodeToRemove.left==null?nodeToRemove.right:nodeToRemove.left;

        if(parent==null){
            nodeToRemove.key=null;
            root=child;
        }else if(parent.right==nodeToRemove){
            parent.right=child;
        }else {
            parent.left=child;
        }
         if(child!=null)
             child.parent=parent;

         clearNode(nodeToRemove);

    }

    private void clearNode(TreeNode<E> nodeToRemove){
        nodeToRemove.key=null;
        nodeToRemove.left=null;
        nodeToRemove.right=null;
        nodeToRemove.parent=null;
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
    public int size() {
        return size;
    }

    @Override
    public boolean addAll(OurSet<E> other) {

        boolean res=false;

        for(E elt: other){
            res = this.add(elt) | res;

        }
        return res;
    }

    @Override
    public boolean removeAll(OurSet<E> other) {
        boolean res= false;
        for (E elt:other) {
            res=this.remove2(elt) | res;

        }
        return res;
    }

    @Override
    public boolean retainAll(OurSet<E> other) {
        OurSet<E> thisSubtractedOther = new OurTreeSet<>();

        for (E elt : this) {
            if (!other.contains(elt))
                thisSubtractedOther.add(elt);
        }

        return this.removeAll(thisSubtractedOther);
    }

    @Override
    public Iterator<E> iterator() {
        return new OurTreeSetIterator<>(this);

    }

   static class TreeNode<E>{
        TreeNode <E> parent;
        TreeNode<E> left;
        TreeNode<E> right;

        E key;
    }
}

class OurTreeSetIterator<E> implements Iterator<E> {
    OurTreeSet<E> treeSet;
    OurTreeSet.TreeNode<E> current;

    public OurTreeSetIterator(OurTreeSet<E> treeSet) {
        this.treeSet = treeSet;
        this.current = treeSet.root == null ? null : getLeast(treeSet.root);
    }

    private OurTreeSet.TreeNode<E> getLeast(OurTreeSet.TreeNode<E> root) {
        OurTreeSet.TreeNode<E> needle = root;

        while (needle.left != null)
            needle = needle.left;

        return needle;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public E next() {
        E res = current.key;

        if (current.right != null) {
            current = getLeast(current.right);
        } else {
            current = firstRightParent(current);
        }
        return res;
    }

    /**
     * the method finds the first parent which is to the right from current
     *
     * @param current element
     * @return next element by order if exists or null, if current is the most right elt in
     * the treeSet
     */
    private OurTreeSet.TreeNode<E> firstRightParent(OurTreeSet.TreeNode<E> current) {

        OurTreeSet.TreeNode<E> needle = current;
        while ( needle.parent != null && needle.parent.left != needle)
            needle = needle.parent;
        return needle.parent;
    }
}
