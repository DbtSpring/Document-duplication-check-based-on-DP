import Util.DPCompareUtil;
import Util.FileReaderUtil;
import Util.ResultOutputUtil;
import pojo.CompareResult;
import pojo.Sentence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocumentDuplicationChecker {
    public static void main(String[] args) {
        try {
            // 步骤1：读取lib.txt并解析为句子列表
            List<Sentence> libSentences = FileReaderUtil.readAndSplitSentences("resources/lib.txt");
            if (libSentences.isEmpty()) {
                System.out.println("库文件lib.txt为空或格式错误！");
                return;
            }

            // 步骤2：读取text.txt并解析为句子列表
            List<Sentence> textSentences = FileReaderUtil.readAndSplitSentences("resources/text.txt");
            if (textSentences.isEmpty()) {
                System.out.println("待查文件text.txt为空或格式错误！");
                return;
            }

            // 步骤3：逐句比对text.txt与lib.txt，存储所有结果
            List<CompareResult> allResults = new ArrayList<>();
            for (Sentence textSent : textSentences) {
                CompareResult result = DPCompareUtil.compareWithLib(textSent, libSentences);
                allResults.add(result);
            }

            // 步骤4：输出重复句子
            ResultOutputUtil.outputDuplicateSentences(allResults);

        } catch (IOException e) {
            System.out.println("文件读取异常：" + e.getMessage());
        }
    }
}
