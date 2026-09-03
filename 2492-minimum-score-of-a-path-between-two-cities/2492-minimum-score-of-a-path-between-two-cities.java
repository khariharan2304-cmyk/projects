class Solution {
    public int minScore(int n, int[][] roads) {
        List<List<int[]>>list=new ArrayList<>();
        for(int i=0;i<=n;i++)list.add(new ArrayList<>());
        for(int[]row:roads){
            list.get(row[0]).add(new int[]{row[1],row[2]});
            list.get(row[1]).add(new int[]{row[0],row[2]});
        }
        int ans=Integer.MAX_VALUE;
        boolean[]visited=new boolean[n+1];
        Queue<Integer>queue=new LinkedList<>();
        queue.offer(1);
        while(!queue.isEmpty()){
            int curNode=queue.poll();
            for(int[]node:list.get(curNode)){
                ans=Math.min(ans,node[1]);
                if(!visited[node[0]]){
                    visited[node[0]]=true;
                    queue.offer(node[0]);
                }
            }
        }
        return ans;
    }
}