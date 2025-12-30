package Util;

import pojo.CompareResult;
import pojo.Sentence;

import java.util.List;

/**
 * 动态规划比对工具类
 */
public class DPCompareUtil {
    /**
     * 比对单个待查句子与库中所有句子，返回最高相似度结果
     * @param textSentence 待查句子（来自text.txt）
     * @param libSentences 库句子列表（来自lib.txt）
     * @return 最高相似度的CompareResult
     */

    public static CompareResult compareWithLib(Sentence textSentence, List<Sentence> libSentences) {
        double maxSimilarity = 0.0;
        Sentence bestMatchLibSentence = null;


        String[] textWords = textSentence.getContent().split(" ");
        int m = textSentence.getWordCount();

        // 遍历库中所有句子，逐一比对
        for (Sentence libSentence : libSentences) {
            //每一个lib的句子是一个DP
            String[] libWords = libSentence.getContent().split(" ");
            int n = libSentence.getWordCount();

            // 步骤1：构建wordSimilarity矩阵
            int[][] wordSimilarity = buildWordSimilarityMatrix(textWords, libWords);

            // 步骤2：构建dp矩阵并计算最大匹配数
            int[][] dp = buildDPMatrix(wordSimilarity, m, n);
            int maxMatchCount = dp[m][n];

            // 步骤3：计算相似度 = 匹配数 / 待查句单词数
            double similarity = (double) maxMatchCount / m;

            // 更新最高相似度
            if (similarity > maxSimilarity) {
                maxSimilarity = similarity;
                bestMatchLibSentence = libSentence;
            }
        }

        return new CompareResult(textSentence, bestMatchLibSentence, maxSimilarity);
    }

    /**
     * 构建单词匹配矩阵：textWords[i]与libWords[j]相同则为1，否则为0
     */
    public static int[][] buildWordSimilarityMatrix(String[] textWords, String[] libWords) {
        int m = textWords.length;
        int n = libWords.length;
        int[][] matrix = new int[m + 1][n + 1]; // 下标从1开始，便于dp初始化 ([i][0]和[0][j])

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (textWords[i - 1].equals(libWords[j - 1])) {
                    matrix[i][j] = 1;
                } else {
                    matrix[i][j] = 0;
                }
            }
        }
        return matrix;
    }

    /**
     * 构建DP矩阵，计算最大匹配数
     */
    public static int[][] buildDPMatrix(int[][] wordSimilarity, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        // 初始化：dp[0][j]和dp[i][0]均为0（空句子匹配数为0）
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = 0;
        }

        // 填充DP矩阵
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 三种情况取最大值
                int case1 = dp[i - 1][j]; // 待查句单词比对空格
                int case2 = dp[i][j - 1]; // 库句单词比对空格
                int case3 = dp[i - 1][j - 1] + wordSimilarity[i][j]; // 单词匹配.wordSimilarity[i][j]=1或0
                dp[i][j] = Math.max(Math.max(case1, case2), case3);
            }
        }
        return dp;
    }
}
