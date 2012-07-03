package ch12.ex12_01;

public interface Attributed<E> {
    void add(Attr<E> newAttr);
    Attr<E> find(String attrName);
    Attr<E> remove(String attrName);
    java.util.Iterator<Attr<E>> attrs();
}
