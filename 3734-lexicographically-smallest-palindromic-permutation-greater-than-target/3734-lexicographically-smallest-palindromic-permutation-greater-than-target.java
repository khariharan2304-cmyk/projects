
class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        int odd = 0;
        int mid = -1;

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) {
                odd++;
                mid = i;
            }
        }

        if (odd > 1) return "";

        int len = n / 2;
        int[] half = new int[26];

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
        }

        String ans = "";

        for (int i = 0; i < len; i++) {
            int[] rem = half.clone();

            boolean valid = true;

            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';

                if (rem[c] == 0) {
                    valid = false;
                    break;
                }

                rem[c]--;
            }

            if (!valid) break;

            int start = target.charAt(i) - 'a' + 1;

            for (int c = start; c < 26; c++) {
                if (rem[c] == 0) continue;

                rem[c]--;

                StringBuilder left = new StringBuilder();

                for (int j = 0; j < i; j++) {
                    left.append(target.charAt(j));
                }

                left.append((char) ('a' + c));

                for (int x = 0; x < 26; x++) {
                    while (rem[x] > 0) {
                        left.append((char) ('a' + x));
                        rem[x]--;
                    }
                }

                String candidate = palindrome(left.toString(), mid, n);

                if (candidate.compareTo(target) > 0 &&
                    (ans.isEmpty() || candidate.compareTo(ans) < 0)) {
                    ans = candidate;
                }

                rem = half.clone();

                for (int j = 0; j < i; j++) {
                    rem[target.charAt(j) - 'a']--;
                }
            }
        }

        int[] need = new int[26];

        for (int i = 0; i < len; i++) {
            need[target.charAt(i) - 'a']++;
        }

        boolean possible = true;

        for (int i = 0; i < 26; i++) {
            if (need[i] > half[i]) {
                possible = false;
                break;
            }
        }

        if (possible) {
            String candidate = palindrome(target.substring(0, len), mid, n);

            if (candidate.compareTo(target) > 0 &&
                (ans.isEmpty() || candidate.compareTo(ans) < 0)) {
                ans = candidate;
            }
        }

        return ans;
    }

    private void appendSorted(StringBuilder sb, int[] freq) {
        for (int c = 0; c < 26; c++) {
            while (freq[c] > 0) {
                sb.append((char) ('a' + c));
                freq[c]--;
            }
        }
    }

    private String palindrome(String left, int mid, int n) {
        StringBuilder sb = new StringBuilder();

        sb.append(left);

        if ((n & 1) == 1) {
            sb.append((char) ('a' + mid));
        }

        for (int i = left.length() - 1; i >= 0; i--) {
            sb.append(left.charAt(i));
        }

        return sb.toString();
    }
}