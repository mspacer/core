package com.core.inner;

public interface ILogic {
    void doLogic();

    class NestedLogic { // public static: default parameters
        public long value;
        public NestedLogic() { /* code */ }
        public static void assign() { /* code */ }
        public void accept() { /* code */ }
    }
}
