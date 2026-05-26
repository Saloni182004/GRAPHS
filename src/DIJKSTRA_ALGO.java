import java.util.*;
public class DIJKSTRA_ALGO {
    static HashMap<Integer,HashMap<Integer,Integer>> map=new HashMap<>();
    DIJKSTRA_ALGO(){
        map=new HashMap<>();
    }
    DIJKSTRA_ALGO(int n){
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
    public static HashMap<Integer,Integer> dij(int src){
        HashMap<Integer,Integer> dist=new HashMap<>();
        for(int node:map.keySet()){
            dist.put(node,Integer.MAX_VALUE);
        }
        dist.put(src,0);
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->(a[1]-b[1]));
        pq.add(new int[]{src,0});
        HashSet<Integer> visited=new HashSet<>();
        while(!pq.isEmpty()){
            int[] top=pq.poll();
            int node=top[0];
            int nodeDist=top[1];
            if(visited.contains(node)) continue;
            visited.add(node);
            for(int nbr:map.get(node).keySet()){
                int weight=map.get(node).get(nbr);
                if(nodeDist+weight<dist.get(nbr)){
                    dist.put(nbr,nodeDist+weight);
                    pq.add(new int[]{nbr,dist.get(nbr)});
                }
            }
        }
        return dist;
    }
    public static void main(String[] args) {
        DIJKSTRA_ALGO g=new DIJKSTRA_ALGO(5);
        g.add_edge(1,2,4);
        g.add_edge(1,3,1);
        g.add_edge(1,4,3);
        g.add_edge(2,5,2);
        g.add_edge(3,4,5);
        g.add_edge(4,5,3);
        g.display();
        System.out.println(dij(1));
    }
}
