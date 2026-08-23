class Solution {
    public List<Integer> grayCode(int n) {
        int total=1<<n;
        List<Integer> gray=new ArrayList<>();
        for(int i=0;i<total;i++){
            gray.add(i^(i>>1));
        }
        return gray;
    }
}