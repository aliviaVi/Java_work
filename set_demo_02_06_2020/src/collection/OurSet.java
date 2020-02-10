package collection;

public interface OurSet<E> extends Iterable<E> {

    /*
    adds a new element to the set
    * @param elt to add
    * @return true if the set was changed
    * */
    boolean add(E elt);
        /*
        * removes the element from the set
        * @param elt to remove
        * @return if the set was changed*/


    boolean remove(E elt);

    boolean contains(E elt);
    int size();

    /*
    * adds all the elements from the other to this set
    * @param other elemets of from to be added
    * @return true if the set was changed
    * */

    boolean addAll(OurSet<E> other);

    boolean removeAll(OurSet<E> other);

    boolean retainAll(OurSet<E> other);
}
