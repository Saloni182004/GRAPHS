import java.util.*;
public class PRIM_ALGO{
    HashMap<Integer,HashMap<Integer,Integer>> map=new HashMap<>();
    PRIM_ALGO(){
        map=new HashMap<>();
    }
    PRIM_ALGO(int n){
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
    class PrimPair{
        int v,av,cost;
        PrimPair(int v,int av,int cost){
            this.v=v;
            this.av=av;
            this.cost=cost;
        }
    }
    public void primMST(int startNode){
        PriorityQueue<PrimPair> pq=new PriorityQueue<>(Comparator.comparingInt(pair->pair.cost));
        HashSet<Integer> visited=new HashSet<>();
        List<String> mstEdges=new ArrayList<>();
        int mstCost=0;
        pq.add(new PrimPair(startNode,-1,0));
        while(!pq.isEmpty()){
            PrimPair curr=pq.poll();
            if(visited.contains(curr.v)) continue;
            visited.add(curr.v);
            mstCost+=curr.cost;
            if(curr.av!=-1){
                mstEdges.add(curr.av+" "+curr.v+" "+curr.cost);
            }
            HashMap<Integer,Integer> nei=map.get(curr.v);
            if(nei!=null){
                for(int nbr:nei.keySet()){
                    if(!visited.contains(nbr)){
                        int edgeWt=nei.get(nbr);
                        pq.add(new PrimPair(nbr,curr.v,edgeWt));
                    }
                }
            }
        }
        for(String edge:mstEdges){
            System.out.println(edge);
        }
        System.out.println(mstCost);
    }

    static void main(String[] args) {
        PRIM_ALGO g=new PRIM_ALGO(4);
        g.add_edge(1,2,10);
        g.add_edge(1,3,6);
        g.add_edge(1,4,5);
        g.add_edge(2,4,15);
        g.add_edge(3,4,4);
        g.primMST(1);
    }
}
