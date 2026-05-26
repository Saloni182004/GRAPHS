import java.util.*;

class KruskalEdge implements Comparable<KruskalEdge>{
    int src,dest,wt;
    KruskalEdge(int src,int dest,int wt){
        this.src=src;
        this.dest=dest;
        this.wt=wt;
    }
    @Override
    public int compareTo(KruskalEdge other){
        return this.wt-other.wt;
    }
}
class DisjoinSet{
    int[] parent;
    int[] rank;
    public DisjoinSet(int n){
        parent=new int[n+1];
        rank=new int[n+1];
        for(int i=1;i<=n;i++){
            parent[i]=i;
            rank[i]=0;
        }
    }
    public int find(int x){
        if(parent[x]==x) return x;
        return parent[x]=find(parent[x]);
    }
    public boolean union(int i,int j){
        int rootI=find(i);
        int rootJ=find(j);
        if(rootI==rootJ) return false; //cycle
        if(rank[rootI]<rank[rootJ]){
            parent[rootI]=rootJ;
        }
        else if(rank[rootI]>rank[rootJ]){
            parent[rootJ]=rootI;
        }else{
            parent[rootJ]=rootI;
            rank[rootI]++;
        }
        return true;
    }
}
public class KRUSKAL {
    HashMap<Integer,HashMap<Integer,Integer>> map=new HashMap<>();
    KRUSKAL(){
        map=new HashMap<>();
    }
    KRUSKAL(int n){
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
    public void krus(int n){
        List<KruskalEdge> allEdges=new ArrayList<>();
        HashSet<String> seen=new HashSet<>();
        for(int u:map.keySet()){
            for(int v:map.get(u).keySet()){
                String edgeKey=Math.min(u,v)+"-"+Math.max(u,v);
                if(!seen.contains(edgeKey)){
                    seen.add(edgeKey);
                    allEdges.add(new KruskalEdge(u,v,map.get(u).get(v)));
                }
            }
        }
        Collections.sort(allEdges);
        DisjoinSet dsu=new DisjoinSet(n);
        List<KruskalEdge> res=new ArrayList<>();
        int mstCost=0;
        for(KruskalEdge edge:allEdges){
            if(dsu.union(edge.src,edge.dest)){
                res.add(edge);
                mstCost+=edge.wt;
            }
            if(res.size()==n-1) break;
        }
        for(KruskalEdge e:res){
            System.out.println(e.src+" "+e.dest+" "+e.wt);
        }
        System.out.println(mstCost);
    }
    static void main(String[] args) {
        KRUSKAL g=new KRUSKAL(4);
        g.add_edge(1,2,10);
        g.add_edge(1,3,6);
        g.add_edge(1,4,5);
        g.add_edge(2,4,15);
        g.add_edge(3,4,4);
        g.display();
        g.krus(4);
    }
}
