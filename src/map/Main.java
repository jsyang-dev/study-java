package map;

import java.util.HashMap;
import java.util.Map;

public class Main {

    static class Foo {
        private static final Foo[] Foos = new Foo[10];
        private final int value;

        private Foo(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        static Foo getInstance(int value) {
            if (Foos[value] == null) {
                Foos[value] = new Foo(value);
            }
            return Foos[value];
        }
    }

    public static void main(String[] args) {
        Map<Foo, Integer> map = new HashMap<>();
        map.put(Foo.getInstance(1), 1);
        map.put(Foo.getInstance(2), 2);
        map.put(Foo.getInstance(2), 3);

        System.out.println(map.size());
        System.out.println(Foo.getInstance(1).getValue());
    }
}

