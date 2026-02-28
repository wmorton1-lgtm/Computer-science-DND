public class ChocolateHashMapTester {
    public static void main(String[] args) {
        ChocolateHashMap<String, String> m = new ChocolateHashMap<>();
        System.out.println("empty? " + m.isEmpty() + " size=  " + m.size()); // true, 0
        System.out.println("get(x)=" + m.get("x")); // null
        System.out.println("containsKey(x)=" + m.containsKey("x")); // false
        System.out.println("containsValue(v)=" + m.containsValue("v")); // false

        System.out.println("put(x,v)=" + m.put("x", "v")); // true
        System.out.println("empty? " + m.isEmpty() + " size=" + m.size()); // false, 1
        System.out.println("get(x)=" + m.get("x")); // v
        System.out.println("containsKey(x)=" + m.containsKey("x")); // true
        System.out.println("containsValue(v)=" + m.containsValue("v")); // true
        System.out.println("put(x,other)=" + m.put("x", "other")); // false (duplicate key)

        System.out.println("remove(x)=" + m.remove("x")); // true
        System.out.println("size=" + m.size() + " get(x)=" + m.get("x")); // 0, null
        System.out.println("remove(x) again=" + m.remove("x")); // false
        System.out.println(m.toString()); // prints map
        m.rehash(m.getBuckets().length);
        System.out.println(m.currentLoadFactor()); // 
    }
}
