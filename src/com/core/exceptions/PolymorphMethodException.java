package com.core.exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PolymorphMethodException {
    public void buildHouse(Stone stone) {
        try {
            stone.accept("some info");
        } catch (ResourceException e) {
            // handling of ResourceException and its subclasses
            System.err.print(e);
        }
    }
}

class Stone {
    public void accept(String data) throws ResourceException {
        /* more code */
    }
}

class GreenStone extends Stone {
    @Override
    public void accept(String data) {
        //some code
    }
}

class WhiteStone extends Stone {
    @Override
    public void accept(String data) throws ResourceException {
        super.accept(data);
    }
}

class GreyStone extends Stone {
    @Override
    public void accept(String data) /*throws IOException*/ {//compile error
        //FileWriter writer = new FileWriter("data.txt");
    }
}

class Resource { // old class
    public Resource(String filename) throws FileNotFoundException {
        // more code
    }
}

class ConcreteResource extends Resource { // old class
    public ConcreteResource(String name) throws FileNotFoundException {
        super(name);
        // more code
    }
    public ConcreteResource() throws IOException {
        super("file.txt");
        // more code
    }
}