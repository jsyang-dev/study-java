package me.study.java.java;

public class Outer {

    public void outerPublic() {
    }

    private void outerPrivate() {
    }

    class Inner {

        public void innerPublic() {
            outerPrivate();
        }
    }
}
