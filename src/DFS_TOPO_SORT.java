import java.util.*;
public class DFS_TOPO_SORT {
    static HashMap<Integer,HashMap<Integer,Integer>> map=new HashMap<>();
    DFS_TOPO_SORT(){
        map=new HashMap<>();
    }
    DFS_TOPO_SORT(int n){
        map=new HashMap<>();
        for(int i=0;i<=n;i++){
            this.add_vertex(i);
        }
    }
    public void add_vertex(int v){
        map.put(v,new HashMap<>());
    }
    public void add_edge(int v1,int v2,int wt){
        map.get(v1).put(v2,wt);
    }
    public static void dfs(int node,HashSet<Integer> visited,Stack<Integer> st){
        visited.add(node);
        for(int nbr:map.get(node).keySet()){
            if(!visited.contains(nbr)){
                dfs(nbr,visited,st);
            }
        }
        st.push(node);
    }
    public static List<Integer> topoSort(int n){
        HashSet<Integer> visited=new HashSet<>();
        Stack<Integer> st=new Stack<>();
        for(int i=1;i<n;i++){
            if(!visited.contains(i)){
                dfs(i,visited,st);
            }
        }
        List<Integer> res=new ArrayList<>();
        while(!st.isEmpty()){
            res.add(st.pop());
        }
        return res;
    }
    static void main(String[] args) {
        DFS_TOPO_SORT g=new DFS_TOPO_SORT(5);
        g.add_edge(1,2,1);
        g.add_edge(1,3,1);
        g.add_edge(2,4,1);
        g.add_edge(3,4,1);
        g.add_edge(4,5,1);
        List<Integer> res=g.topoSort(5);
        System.out.println(res);
    }
}
