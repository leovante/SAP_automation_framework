package com.utils.sapElement;

public class By {
    public static By id(String id) {
        return new ById(id);
    }

    public static By name(String name) {
        return new ByName(name);
    }

    @Override
    public String toString() {
        // A stub to prevent endless recursion in hashCode()
        return "[unknown locator]";
    }

    static class ByName extends By {
        String name;

        ByName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static class ById extends By {
        String id;

        ById(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return id;
        }
    }
}
