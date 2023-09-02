package jp.co.practice.dao;

import jp.co.practice.entity.Sample;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

/**
 * Dao
 */
@Dao
@ConfigAutowireable
public interface SampleDao {

    /**
     * 主キーを指定してセッションを検索します。
     * @param id 管理ID : 管理ID
     * @return セッションエンティティ
     */
    @Select
    Sample selectById(Long id);

    /**
     * セッションを登録します。
     * @param entity セッションエンティティ
     * @return 登録された行数
     */
    @Insert
    int insert(Sample entity);

    /**
     * セッションを更新します。
     * @param entity セッションエンティティ
     * @return 更新された行数
     */
    @Update
    int update(Sample entity);

    /**
     * セッションを削除します。
     * @param entity セッションエンティティ
     * @return 削除された行数
     */
    @Delete
    int delete(Sample entity);

}
