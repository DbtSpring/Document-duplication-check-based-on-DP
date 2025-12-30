package pojo;

/**
 * 存储单句文本及单词数，用于lib.txt和text.txt的句子存储
 */
public class Sentence {
    private String content; // 句子内容（单词间用空格分隔）
    private int wordCount;  // 句子中单词总数（去标点后）


    public Sentence(String content, int wordCount) {
        this.content = content;
        this.wordCount = wordCount;
    }


    public String getContent() { return content; }
    public int getWordCount() { return wordCount; }
}
