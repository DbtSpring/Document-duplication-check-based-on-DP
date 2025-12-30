*中文介绍：* [跳转](#项目概述)

*English intro:* [GoTo](#project-overview)

---

## 项目概述

实现基于动态规划（DP）的文献查重算法：通过比对待查文本与库文本的句子级相似度，识别重复内容。相似度基于单词匹配计算，≥40% 判定为重复，并输出匹配部分的对齐结果。

## 项目结构

```plaintext
DPDuplicationCheck/
├── resources/
│   ├── lib.txt       # 库文本文件（参考语料）
│   └── text.txt      # 待查重文本文件
├── src/
│   ├── pojo/
│   │   ├── CompareResult.java  # 封装比对结果
│   │   └── Sentence.java       # 封装句子内容与单词数
│   ├── util/
│   │   ├── DPCompareUtil.java  # 核心动态规划算法工具
│   │   ├── FileReaderUtil.java # 文件读取与解析工具
│   │   └── ResultOutputUtil.java # 结果输出与对齐工具
│   └── DocumentDuplicationChecker.java # 主类
```

## 运行结果

```
相似度：100.0%
text ：algorithms exist that perform calculation data processing and automated reasoning
lib ： algorithms exist that perform calculation data processing and automated reasoning
----------------------------------------

相似度：83.3%
text ：starting from an initial state and initial input the instructions describe a computation that when executed proceeds through a finite number of well defined successive states eventually producing output and terminating at a final ending state
lib ： starting from an initial state and initial input --- ------------ -------- - ----------- ---- when executed proceeds through a finite number of well defined successive states eventually producing output and terminating at a final ending state
----------------------------------------

相似度：85.7%
text ：the transition from one state to the next is not necessarily deterministic some algorithms known as randomized algorithms incorporate random input
lib ： the transition from one state to --- ---- -- not necessarily deterministic some algorithms known as randomized algorithms incorporate random input
----------------------------------------
....
```

## 分析时间复杂度

核心 DP 矩阵构建的时间复杂度为 \(O(mn)\)（m = 待查句单词数，n = 库句单词数）。



---

## Project Overview

Implement a document duplication check algorithm based on Dynamic Programming (DP): By comparing the sentence-level similarity between the text to be checked and library texts, identify duplicate content. Similarity is calculated based on word matching—sentences with a similarity ≥ 40% are judged as duplicates, and the aligned results of matching parts are output.

## Project Structure

```plaintext
DPDuplicationCheck/
├── resources/
│   ├── lib.txt       # Library text file (reference corpus)
│   └── text.txt      # Text file to be checked for duplication
├── src/
│   ├── pojo/
│   │   ├── CompareResult.java  # Encapsulates comparison results
│   │   └── Sentence.java       # Encapsulates sentence content and word count
│   ├── util/
│   │   ├── DPCompareUtil.java  # Core Dynamic Programming algorithm tools
│   │   ├── FileReaderUtil.java # File reading and parsing tools
│   │   └── ResultOutputUtil.java # Result output and alignment tools
│   └── DocumentDuplicationChecker.java # Main class
```

## Execution Results

```plaintext
Similarity: 100.0%
text: algorithms exist that perform calculation data processing and automated reasoning
lib:  algorithms exist that perform calculation data processing and automated reasoning
----------------------------------------

Similarity: 83.3%
text: starting from an initial state and initial input the instructions describe a computation that when executed proceeds through a finite number of well defined successive states eventually producing output and terminating at a final ending state
lib:  starting from an initial state and initial input --- ------------ -------- - ----------- ---- when executed proceeds through a finite number of well defined successive states eventually producing output and terminating at a final ending state
----------------------------------------

Similarity: 85.7%
text: the transition from one state to the next is not necessarily deterministic some algorithms known as randomized algorithms incorporate random input
lib:  the transition from one state to --- ---- -- not necessarily deterministic some algorithms known as randomized algorithms incorporate random input
----------------------------------------
....
```

## Time Complexity Analysis


The time complexity of constructing the core DP matrix is \(O(mn)\) (where m = number of words in the sentence to be checked, n = number of words in the library sentence).
