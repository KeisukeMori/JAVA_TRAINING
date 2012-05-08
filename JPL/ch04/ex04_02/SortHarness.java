package ch04.ex04_02;

public interface SortHarness {
    SortMetrics sort(Object[] data);
    SortMetrics getMetrics();
}

