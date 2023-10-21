/**
 Find the Longest Palindromic Substring in a given String
 leetcode - https://leetcode.com/problems/longest-palindromic-substring/
 */
public class Longest_Palindromic_Substring {

    public String longestPalindrome(String s) {

        boolean a[][] = new boolean[s.length()][s.length()];

        int m = 0;

        int ind1 = 0;
        int ind2 = 0;

        for(int i=0;i<s.length();i++){

            a[i][i] = true;
        }

        for(int i=0;i<s.length()-1;i++){

            if(s.charAt(i) == s.charAt(i+1)){

                a[i][i+1] = true;
                m = 2;
                ind1 = i;
                ind2 = i+1;
            }
        }

        for(int i=2;i<s.length();i++){

            for(int j=0;j<s.length()-i;j++){

                if((s.charAt(j) == s.charAt(j+i)) && (a[j+1][j+i-1] == true)){

                    a[j][j+i] = true;
                    if(m < (i+1)){
                        m = i+1;
                        ind1 = j;
                        ind2 = j+i;
                    }
                }
            }
        }

        return s.substring(ind1, ind2+1);
    }
}
