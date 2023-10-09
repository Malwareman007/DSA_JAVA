// Leetcode Problem link-> https://leetcode.com/problems/maximum-score-words-formed-by-letters/description/ 

//Problem --------------------------------------------------------------------------------------->
/*Given a list of words, list of  single letters (might be repeating) and score of every character.

Return the maximum score of any valid set of words formed by using the given letters (words[i] cannot be used two or more times).

It is not necessary to use all characters in letters and each letter can only be used once.
Score of letters 'a', 'b', 'c', ... ,'z' is given by score[0], score[1], ... , score[25] respectively. 
-------------------------------------------------------------------------------------------------------
*/


/* Approach to solve it --------------------------------------------------------------------------------------->:
 *  (0/1 - knapsack used here)
 * 1. Calculate the frequency of each character in the given letters array.
 * 2. Use backtracking to calculate the score for each word.
 *    a. Don't take string and just move ahead
 * 
 *    b. Take it's score if it is possicle to take it :
 *       I im using a for loop to check :
 *       i. if the frequency of each character in the word is == 0 ,We can't take this string make flage as false,
 *       ii. take score and add it and decrease it's frequence by one
 * 
 *    c. if flage is true then add in picked variable and call for again same function
 * 
 *    d. make all the frequence of fre to it's initial value 
 *    
 *    e. return Maximum of pick and not pick
 *  -------------------------------------------------------------------------------------------------------
 */


public class leetcode1255 {

    public static void main(String[] args) {
        //Set variables  Input 
        String[] words = { "dog", "cat", "dad", "good" };
        char[] letters = { 'a', 'a', 'c', 'd', 'd', 'd', 'g','o','o' };
        int[] score = { 1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

        //Call for funtion to get answer 
        int maxScore = maxScoreWords(words, letters, score);

        // Print answer for maximum score
        System.out.println("Maximum score: " + maxScore);
    }

    public static int maxScoreWords(String[] words, char[] letters, int[] score) {
        
        //calculating frequecy for each character and strore it in array
        int[] fre=new int[26];
        for (char ch : letters) {
            fre[ch - 'a']++;
        }
        
        //calculate for all the possible answers and getting maxinmum value from it (it's a recursive function )
        return f(0,fre,words,score);

    }
    static int f(int i,int[] fre,String[] words, int[] score){
        if (i >= words.length) {
            return 0;
        }

        // score of not picked is 0, and move ahead for next string of words
        int nopick = f(i + 1, fre, words, score);
        
        int pickscore=0;
        boolean f = true;

        //calculating picked score and also checking if we can pick it or not,by checking it's frequence
        for (char ch : words[i].toCharArray()) {

            //if any character have frequency 0,then we can't pick string  , means make flag false
            if (fre[ch - 'a'] == 0) {
                f = false;
            }

            //add score of character to pick score
            pickscore += score[ch - 'a'];

            // decrease frequency of character by one
            fre[ch - 'a']--;
        }
        
        int pick = 0;

        // if flag is true then add score of character 
        if (f) {
            pick += pickscore + f(i + 1, fre, words, score);
        }

        //Making  all the frequency equal to it's initial frequency
        for (char ch : words[i].toCharArray()) {
            fre[ch - 'a']++;
        }

        // return maximum of pick and not pick
        return Math.max(pick,nopick);
    }
}



/* Complexity Analysis --------------------------------------------------------------------------------------->
 * It  takes 0ms to run on leetcode
 * Time Complexity : O(2 ^ (words.length)) * max(words[i].length )
 * Space Complexity : O(words.length)
 * -------------------------------------------------------------------------------------------------------
 */
