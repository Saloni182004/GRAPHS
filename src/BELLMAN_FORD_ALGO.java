import java.util.*;
public class BELLMAN_FORD_ALGO {
    static HashMap<Integer,HashMap<Integer,Integer>> map=new HashMap<>();
    BELLMAN_FORD_ALGO(){
        map=new HashMap<>();
    }
    BELLMAN_FORD_ALGO(int n){
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
    public void display(){
        System.out.println(map);
    }
    public static int[] bell(int n,int src){
        int[] dist=new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src]=0;
        for(int i=1;i<=n-1;i++){
            for(int u:map.keySet()){
                for(int v:map.get(u).keySet()){
                    int weight=map.get(u).get(v);
                    if(dist[u]!=Integer.MAX_VALUE && dist[u]+weight<dist[v]){
                        dist[v]=dist[u]+weight;
                    }
                }
            }
        }
        return dist;
    }
    static void main(String[] args) {
        BELLMAN_FORD_ALGO g=new BELLMAN_FORD_ALGO(5);
        g.add_edge(1,2,4);
        g.add_edge(1,3,1);
        g.add_edge(1,4,3);
        g.add_edge(2,5,2);;
        g.add_edge(3,4,5);
        g.add_edge(4,5,3);
        g.display();
        int[] arr=bell(5,1);
        for(int i=1;i<=5;i++){
            System.out.print(arr[i]+" ");
        }
    }
}
