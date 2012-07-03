package ch11.ex11_03;

public interface Attributed<E> {
    void add(Attr<E> newAttr);
    Attr<E> find(String attrName);
    Attr<E> remove(String attrName);
    java.util.Iterator<Attr<E>> attrs();
}
