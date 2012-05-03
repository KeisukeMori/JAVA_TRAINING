package ch01.ex01_16;

import java.io.IOException;

class BadDataSetException extends Exception {
    public String file;
    public IOException e;
}