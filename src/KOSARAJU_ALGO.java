import java.util.*;
public class KOSARAJU_ALGO {
    static HashMap<Integer,HashMap<Integer,Integer>> map=new HashMap<>();
    KOSARAJU_ALGO(){
        map=new HashMap<>();
    }
    KOSARAJU_ALGO(int n){
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
    }
    public void dfs1(int node,HashSet<Integer> visited,Stack<Integer> st){
        visited.add(node);
        for(int nbr:map.get(node).keySet()){
            if(!visited.contains(nbr)){
                dfs1(nbr,visited,st);
            }
        }
        st.push(node);
    }
    public void dfs2(int node,HashMap<Integer,ArrayList<Integer>> revGraph,HashSet<Integer> visited,List<Integer> comp){
        visited.add(node);
        comp.add(node);
        for(int nbr:revGraph.get(node)){
            if(!visited.contains(nbr)){
                dfs2(nbr,revGraph,visited,comp);
            }
        }
    }
    public List<List<Integer>> kosaraju(int n){
        HashSet<Integer> visited=new HashSet<>();
        Stack<Integer> st=new Stack<>();
        for(int i=1;i<=n;i++){
            if(!visited.contains(i)){
                dfs1(i,visited,st);
            }
        }
        HashMap<Integer,ArrayList<Integer>> revGraph=new HashMap<>();
        for(int i=1;i<=n;i++) revGraph.put(i,new ArrayList<>());
        for(int u:map.keySet()){
            for(int v:map.get(u).keySet()){
                revGraph.get(v).add(u);
            }
        }
        List<List<Integer>> result=new ArrayList<>();
        visited.clear();
        while(!st.isEmpty()){
            int curr=st.pop();
            if(!visited.contains(curr)){
                List<Integer> comp=new ArrayList<>();
                dfs2(curr,revGraph,visited,comp);
                result.add(comp);
            }
        }
        return result;
    }
    public static void main(String[] args) {
        KOSARAJU_ALGO g=new KOSARAJU_ALGO(5);
        g.add_edge(1,3,0);
        g.add_edge(1,4,0);
        g.add_edge(2,1,0);
        g.add_edge(3,2,0);
        g.add_edge(4,5,0);
        System.out.println(g.kosaraju(5));
    }
}
