class Solution {
    List<String> ans = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        int part =0;
        StringBuilder sb = new StringBuilder();
        int idx =0;
        int n = s.length();
        answer(idx, s, sb,  part, n);
        return ans;
    }

    public void answer(int idx, String s, StringBuilder sb, int part, int n){
        if(idx == n && part == 4){
            String a  = sb.substring(0, sb.length()-1);
            ans.add(a);
            return;
        }
        if(part == 4 || idx == s.length()){
            return;
        }

        if(idx +1 <= n){
            int len = sb.length();
            sb.append(s.substring(idx, idx+1)).append(".");
            answer(idx+1, s,sb , part+1, n);
            sb.setLength(len);
        }
        
        if(idx +2 <= n && isValidPart(s.substring(idx, idx+2))){
            int len = sb.length();
            sb.append(s.substring(idx, idx+2)).append(".");
            answer(idx+2, s, sb, part+1, n);
            sb.setLength(len);
        }

        if(idx +3 <= n && isValidPart(s.substring(idx, idx+3))){
            int len = sb.length();
            sb.append(s.substring(idx, idx+3)).append(".");
            answer(idx+3, s, sb, part+1, n);
            sb.setLength(len);
        }
    }

    public boolean isValidPart(String part){
        if(part.charAt(0) == '0'){
            return false;
        }
        int num = Integer.parseInt(part);
        if(num > 255){
            return false;
        }
        return true;
    }
}