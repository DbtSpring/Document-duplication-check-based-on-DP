package Util;

import pojo.Sentence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件读取工具类：读取文本并分割为Sentence列表
 */
public class FileReaderUtil {
    // 句子终止符（按实验约定）
    private static final char[] TERMINATORS = {'.', '?', '!'};

    /**
     * 读取文件并解析为句子列表
     */
    public static List<Sentence> readAndSplitSentences(String filePath) throws IOException {
        List<Sentence> sentences = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        StringBuilder sb = new StringBuilder();
        int c; //字符unicode

        // 逐字符读取文件
        while ((c = br.read()) != -1) {
            char ch = (char) c;
            if (isTerminator(ch)) {
                // 遇到句子终止符，解析当前句子
                String sentenceStr = sb.toString().trim();
                if (!sentenceStr.isEmpty()) {
                    String processed = processSentence(sentenceStr);
                    int wordCount = countWords(processed);
                    sentences.add(new Sentence(processed, wordCount));
                }
                sb.setLength(0); // 重置StringBuilder
            } else if (isLetterOrSpace(ch)) {
                // 遇到letter or space， 拼接
                sb.append(Character.toLowerCase(ch));
            }
            // 句子内部忽略逗号
        }
        br.close();
        return sentences;
    }

    // 判断是否为句子终止符
    private static boolean isTerminator(char ch) {
        for (char t : TERMINATORS) {
            if (ch == t) return true;
        }
        return false;
    }

    // 判断是否为字母或空格
    private static boolean isLetterOrSpace(char ch) {
        return Character.isLetter(ch) || ch == ' ';
    }

    // 处理句子：多个连续空格替换为单个
    private static String processSentence(String str) {
        return str.replaceAll("\\s+", " ");
        //regex： 正则表达式
        // s+：1-n个space
    }

    // 统计句子中单词数（按空格分割）
    private static int countWords(String str) {
        if (str.isEmpty()) return 0;
        return str.split(" ").length;
    }
}
