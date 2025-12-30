package Util;

import pojo.CompareResult;
import pojo.Sentence;

import java.util.ArrayList;
import java.util.List;

public class ResultOutputUtil {
    private static final double THRESHOLD = 0.4;

    public static void outputDuplicateSentences(List<CompareResult> allResults) {
        System.out.println("=== 文献查重结果（相似度≥40%）===\n");

        for (CompareResult result : allResults) {
            double similarity = result.getMaxSimilarity();
            if (similarity >= THRESHOLD) {
                Sentence textSent = result.getTextSentence();
                Sentence libSent = result.getLibSentence();

                System.out.printf("相似度：%.1f%%\n", similarity * 100);

                String[] textWords = textSent.getContent().split(" ");
                String[] libWords = libSent.getContent().split(" ");

                StringBuilder textLine = new StringBuilder("text ：");
                StringBuilder libLine = new StringBuilder("lib ： ");

                int[][] wordSimilarity = DPCompareUtil.buildWordSimilarityMatrix(textWords, libWords);
                int i = textWords.length, j = libWords.length;
                int[][] dp = DPCompareUtil.buildDPMatrix(wordSimilarity, i, j);

                List<String> textStack = new ArrayList<>();
                List<String> libStack = new ArrayList<>();

                while (i > 0 && j > 0) {
                    int current = dp[i][j];
                    if (current == dp[i - 1][j - 1] + wordSimilarity[i][j]) {
                        // 单词匹配
                        textStack.add(textWords[i - 1]);
                        libStack.add(libWords[j - 1]);
                        i--;
                        j--;
                    } else if (current == dp[i - 1][j]) {
                        // text 有单词，lib 没有
                        String word = textWords[i - 1];
                        textStack.add(word);
                        libStack.add("-".repeat(word.length())); // 长度匹配
                        i--;
                    } else {
                        // lib 有单词，text 没有
                        String word = libWords[j - 1];
                        textStack.add("-".repeat(word.length())); // 长度匹配
                        libStack.add(word);
                        j--;
                    }
                }

                // 剩余未匹配单词
                while (i > 0) {
                    String word = textWords[i - 1];
                    textStack.add(word);
                    libStack.add("-".repeat(word.length()));
                    i--;
                }
                while (j > 0) {
                    String word = libWords[j - 1];
                    textStack.add("-".repeat(word.length()));
                    libStack.add(word);
                    j--;
                }

                // 逆序输出
                for (int k = textStack.size() - 1; k >= 0; k--) {
                    textLine.append(textStack.get(k)).append(" ");
                    libLine.append(libStack.get(k)).append(" ");
                }

                System.out.println(textLine.toString().trim());
                System.out.println(libLine.toString().trim());
                System.out.println("----------------------------------------\n");
            }
        }
    }
}