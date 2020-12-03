package app.service;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

/**
 * @Author Fizz Pu
 * @Date 2020/12/2 下午7:03
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

// 1.利用敏感词建立有限状态自动机器
// 2.识别优先状态自动即可


@Component
public class SensitiveWorldFilter {

    private  Node startNode;

    private static class Node{
        private static int id = 0;
        // 节点的id, 从0开始
        private final int nodeId = id++;
        // 遇到字符转向的状态, key为遇到的字符, value为转向状态的id
        private Map<Character, Node> next;
        // 表示节点状态, 0为开始状态,1为普通状态, 2为终止态
        private int status = 0;

        public  boolean isEnd(){
            return status == 2;
        }

        public Node(int status) {
            this.status = status;
        }
    }

    public SensitiveWorldFilter(String path) throws IOException {
        init(path);
    }

    /**
     * 将敏感词替换
     * @param txt 将要过滤的敏感词
     * @param replaceWord 敏感词替换字符
     * @return 替换后的字符串
     */
    public String filter(String txt, String replaceWord){
        if(startNode == null || txt == null)return txt;
        int chPoint = 0, txtLen = txt.length();
        Node curNode;
        StringBuilder sb = new StringBuilder();

        while (chPoint < txtLen){
            char curChar = txt.charAt(chPoint);
            int startPoint;
            // 1.curChar 不是根节点的下一个字符, 直接加入sb
            if(!startNode.next.containsKey(curChar)){
                sb.append(curChar);
                chPoint++;
                continue;
            }

            // 2. 开始识别
            startPoint = chPoint; // 标记开始识别的位置,便于后面回退
            int offset = 1; // 识别的token的长度
            curNode = startNode.next.get(curChar);
            while (!curNode.isEnd() && chPoint < txtLen) {
                chPoint++;
                curChar = txt.charAt(chPoint);
                if(!curNode.next.containsKey(curChar)){
                    offset = 0; // 无法识别
                    break;
                }
                curNode = curNode.next.get(curChar);
                offset++;
            }


            if(offset != 0){
                sb.append(replaceWord);
                chPoint = startPoint + offset;
            } else {
                sb.append(txt.charAt(startPoint));
                chPoint = startPoint + 1;
            }

        }

        return sb.toString();
    }

    private void init(String path) throws IOException {
        List<String> words = new LinkedList<>();
        String word;
        try(
                InputStream inputStream = Objects.requireNonNull(getClass().getClassLoader().getResource(path)).openStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        ) {
                while ((word = br.readLine()) != null){words.add(word);}
        }
        setUpDfa(words);
    }

    // 构建DFA
    private void setUpDfa(List<String> words){
        int len, status;
        char ch;
        Node currentNode, nextNode;
        this.startNode = new Node(0);

        for(String word : words){
            len = word.length();
            currentNode = startNode;

            for(int i = 0; i < len; ++i){
                ch = word.charAt(i);
                // 最后一个字符是终态2, 否则是一般态1
                status = (i == len-1) ? 2 : 1;
                if( currentNode.next == null)currentNode.next = new HashMap<>();

                if(!currentNode.next.containsKey(ch)){
                    nextNode = new Node(status);
                    currentNode.next.put(ch, nextNode);
                }
                currentNode = currentNode.next.get(ch);
            }
        }
    }

}
