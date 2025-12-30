package pojo;

/**
 * 存储text.txt中单个句子的比对结果
 */
public class CompareResult {
    private Sentence textSentence;    // text中待查句子
    private Sentence libSentence;     // lib中最相似句子
    private double maxSimilarity;     // 最高相似度（0~1）

    // 构造器
    public CompareResult(Sentence textSentence, Sentence libSentence, double maxSimilarity) {
        this.textSentence = textSentence;
        this.libSentence = libSentence;
        this.maxSimilarity = maxSimilarity;
    }

    // getter/setter
    public Sentence getTextSentence() { return textSentence; }
    public Sentence getLibSentence() { return libSentence; }
    public double getMaxSimilarity() { return maxSimilarity; }
}
