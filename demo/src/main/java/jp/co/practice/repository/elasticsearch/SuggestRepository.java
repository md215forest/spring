package jp.co.practice.repository.elasticsearch;

import java.io.IOException;
import java.util.List;

/**
 * サジェスト Repository
 */
public interface SuggestRepository {

    /**
     * サジェストワードの取得
     * @param word 入力文字
     * @return サジェストワードリスト
     */
    List<String> getSuggestList(String word) throws IOException;
}
