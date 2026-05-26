import java.util.*;
public class UNDIRECTED_CYCLE {
    HashMap<Integer,HashMap<Integer,Integer>> map=new HashMap<>();
    UNDIRECTED_CYCLE(){
        map=new HashMap<>();
    }
    UNDIRECTED_CYCLE(int n){
        map=new HashMap<>();
        for(int i=1;i<=n;i++){
            this.add_vertex(i);
        }
    }
    public void add_vertex(int v){
        map.put(v,new HashMap<>());
    }
    public void add_edge(int v1,int v2,int wt){
        map.get(v1).put(v2,wt);
        map.get(v2).put(v1,wt);
    }
    public void display(){
        System.out.println(map);
    }
    public boolean hasCycle(){
        HashSet<Integer> visited=new HashSet<>();
        for(int node:map.keySet()){
            if(!visited.contains(node)){
                if(dfs(node,-1,visited)) return true;
            }
        }
        return false;
    }
    public boolean dfs(int node,int parent,HashSet<Integer> visited){
        visited.add(node);
        for(int nbr:map.get(node).keySet()){
            if(!visited.contains(nbr)){
                if(dfs(nbr,node,visited)) return true;
            }
            else if(nbr!=parent) return true;
        }
        return false;
    }
    public static void main(String[] args) {
        UNDIRECTED_CYCLE g=new UNDIRECTED_CYCLE(4);
        g.add_edge(1,2,1);
        g.add_edge(2,3,1);
        g.add_edge(3,4,1);
        g.display();
        System.out.println(g.hasCycle());
    }
}
