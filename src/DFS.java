import java.util.*;

public class DFS {
    HashMap<Integer, HashMap<Integer, Integer>> map;

    DFS() {
        map = new HashMap<>();
    }

     DFS(int n) {
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

    void dfs(int start){
        HashSet<Integer> visited=new HashSet<>();
        Stack<Integer> st=new Stack<>();
        st.push(start);
        while(!st.isEmpty()){
            int node=st.pop();
            if(!visited.contains(node)){
                visited.add(node);
                System.out.println(node+" ");
                for(int nbr:map.get(node).keySet()){
                    if(!visited.contains(nbr)){
                        st.push(nbr);
                    }
                }
            }
        }
    }

    public static void main() {
        DFS g = new DFS(4);

        g.add_edge(1, 2, 10); // Connects 1 and 2 with weight 10
        g.add_edge(2, 3, 20); // Connects 2 and 3 with weight 20
        g.add_edge(3, 4, 30); // Connects 3 and 4 with weight 30

        System.out.println("--- Graph Structure ---");
        g.display();

        System.out.println("\n--- BFS Output starting from 1 ---");
        g.dfs(1);
    }
}