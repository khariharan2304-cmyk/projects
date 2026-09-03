class Solution {
    int n;
    int topo[];
    List<List<int[]>> list;
    long dist[];
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        list= new ArrayList<>();
        int ans=-1;
        n=online.length;
        Set<Integer> set= new HashSet<>();
        int indegree[]= new int[n];
        for(int i=0;i<n;i++)list.add(new ArrayList<>());
        for(int x[]:edges){
            int u=x[0];
            int v=x[1];
            int cost=x[2];
            list.get(u).add(new int[]{v,cost});
            indegree[v]+=1;
            set.add(cost);
        }
        topo=topo(indegree);
        dist= new long[n];
        List<Integer> sorted= new ArrayList<>(set);
        Collections.sort(sorted);

        int low=0,high = sorted.size()-1;         
        while(low <=high){
            int mid = (low+high)/2;
            int t = sorted.get(mid);
            if (check(t, online, k)){
                ans = t;      
                low= mid +1; 
            } else {
                high= mid-1;
            }
        }
        return ans;
    }
    public int[] topo(int indegree[]){
        Queue<Integer> q=new LinkedList<>();
        for(int i=0;i<n;i++){
            if(indegree[i]==0)q.add(i);
        }
        int res[]= new int[n];
        int i=0;
        while(!q.isEmpty()){
            int u=q.poll();
            res[i++]=u;
            for(int x[]:list.get(u)){
                int v=x[0];
                if(--indegree[v]==0)q.add(v);
            }
        }
        return res;
    } 
    public boolean check(int t,boolean online[],long k){
        Arrays.fill(dist,Long.MAX_VALUE);
        dist[0]=0;
        for(int i:topo){
            if(!online[i] || dist[i]==Long.MAX_VALUE)continue;
            for(int x[]:list.get(i)){
                int v=x[0];
                int cost=x[1];
                if(cost<t || !online[v])continue;

                if(dist[i]+cost<dist[v] && dist[i]+cost<=k){
                    dist[v]=dist[i]+cost;
                }
            }
        }
        return dist[n-1]<=k;
    }
}


// TLE without binarysearch
// here the cost are sorted as the high  can be the bigger cost (i.e at last in sortedlist)
// best ans=0 and worst can be max cost , ans is in between must and should
// as cost is uptp 10^9 use binary search rather than (0,1,....)

