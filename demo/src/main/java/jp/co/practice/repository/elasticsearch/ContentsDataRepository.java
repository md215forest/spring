package jp.co.practice.repository.elasticsearch;

/**
 * 書籍コンテンツ Repository
 */
public interface ContentsDataRepository {

    /**
     * 閲覧数をカウントする。
     * @param indexName インデックス名
     * @param elasticsearchKey Elasticsearchのid
     */
    void countPvTotal(String indexName, String elasticsearchKey);
}
