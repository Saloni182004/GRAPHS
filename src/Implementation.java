import java.util.HashMap;

public class Implementation {
    HashMap<Integer, HashMap<Integer, Integer>> map;

    Implementation() {
        map = new HashMap<>();
    }

     Implementation(int n) {
        map = new HashMap<>();   // FIXED
        for (int i = 1; i <= n; i++) {
            this.add_vertex(i);
        }
    }

    public void add_vertex(int v) {
        map.put(v, new HashMap<>());
    }

    public void add_edge(int v1, int v2, int wt) {
        map.get(v1).put(v2, wt);
        map.get(v2).put(v1, wt); // undirected
    }

    public void remove_edge(int v1, int v2) {
        map.get(v1).remove(v2);
        map.get(v2).remove(v1);
    }

    public void remove_vertex(int v) {
        for (int i : new HashMap<>(map.get(v)).keySet()) { // FIXED
            this.remove_edge(v, i);
        }
        map.remove(v);
    }

    public int getWeight(int v1, int v2) {
        return map.get(v1).get(v2);
    }

    public void setWeight(int v1, int v2, int wt) {
        map.get(v1).put(v2, wt);
        map.get(v2).put(v1, wt);
    }

    public void display() {
        System.out.println(map);
    }

    public static void main(String[] args) {
        Implementation g = new Implementation(7);

        g.add_edge(1,3,3);
        g.add_edge(1,2,2);
        g.add_edge(2,4,4);
        g.add_edge(4,3,1);
        g.add_edge(4,5,6);
        g.add_edge(5,6,8);
        g.add_edge(5,7,9);
        g.add_edge(6,7,2);

        g.display();
    }
}