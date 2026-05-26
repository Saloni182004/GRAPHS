import java.util.*;
public class DIRECTED_CYCLE {
    HashMap<Integer, HashMap<Integer, Integer>> map;

    DIRECTED_CYCLE() {
        map = new HashMap<>();
    }

    DIRECTED_CYCLE(int n) {
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

    public boolean isCycle(){
        HashSet<Integer> visited=new HashSet<>();
        HashSet<Integer> recStack=new HashSet<>();
        for(int node:map.keySet()){
            if(!visited.contains(node)){
                if(dfs(node,visited,recStack)) return true;
            }
        }
        return false;
    }

    public boolean dfs(int node,HashSet<Integer> visited,HashSet<Integer> recStack){
        visited.add(node);
        recStack.add(node);
        for(int nbr:map.get(node).keySet()){
            if(!visited.contains(nbr)){
                if(dfs(nbr,visited,recStack)) return true;
            }
            else if(recStack.contains(nbr)) return true;
        }
        recStack.remove(node);
        return false;
    }

    public static void main() {
        DIRECTED_CYCLE g = new DIRECTED_CYCLE(3);

        // Building the cycle loop: 1 -> 2 -> 3 -> 1
        g.add_edge(1, 2, 10);
        g.add_edge(2, 3, 20);
        g.add_edge(3, 1, 30);

        System.out.println("Graph layout with a cycle:");
        g.display();
        System.out.println(g.isCycle());
    }
}
