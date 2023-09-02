package jp.co.practice.helper.pageable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * ページング機能 Helper
 */
public class PageableHelper {

    /**
     * ページネーターを取得します。
     * @param pageable {@link Pageable}
     * @return ページネーター
     */
    public static Paginator paginator(Pageable pageable) {
        return new Paginator(pageable);
    }

    /**
     * ページネーター
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Paginator {

        private final Pageable pageable;

        /**
         * ステートメントを実行し、結果をページで返します。
         * @param selectStatement ステートメント
         * @return ページ
         */
        public <T> Page<T> find(Function<SelectOptions, List<T>> selectStatement) {
            SelectOptions selectOptions = selectOptions(pageable);
            List<T> result = selectStatement.apply(selectOptions);
            return new PageImpl<>(result, pageable, selectOptions.getCount());
        }

        /**
         * ソートを用いてステートメントを実行し、結果をページで返します。
         * @param selectStatement ステートメント
         * @return ページ
         */
        public <T> CustomPage<T> findWithSort(BiFunction<SelectOptions, String, List<T>> selectStatement) {
            SelectOptions selectOptions = selectOptions(pageable);
            List<T> result = selectStatement.apply(selectOptions, orderBy(pageable.getSort()));
            return new CustomPageImpl<>(result, pageable, selectOptions.getCount());
        }
    }

    /**
     * offset と limit を適用した検索オプションを生成します。
     * @param pageable {@link Pageable}
     * @return 検索オプション
     */
    private static SelectOptions selectOptions(Pageable pageable) {
        return SelectOptions.get()
                .offset((int) pageable.getOffset())
                .limit(pageable.getPageSize())
                .count();
    }

    /**
     * 指定のソート条件から SQL のソート文を生成します。
     * @param sort ソート条件
     * @return SQL のソート文字列
     */
    private static String orderBy(Sort sort) {
        if (ObjectUtils.isEmpty(sort)) {
            return "";
        }

        List<String> orderList = StreamSupport.stream(sort.spliterator(), false)
                .map(order -> order.getProperty() + " " + order.getDirection())
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(orderList)) {
            return "";
        }

        return "ORDER BY " + String.join(", ", orderList);
    }

    /**
     * ページ情報の設定
     * @param sort ソート情報
     * @param page ページ番号
     * @param pageableSize ページ数
     */
    public static PageRequest getPageRequest(Optional<Sort> sort, Integer page, Integer pageableSize) {
        // ページ情報の設定
        final PageRequest pageRequest;
        if (sort.isEmpty()) {
            pageRequest = PageRequest.of(page, pageableSize);
        } else {
            pageRequest = PageRequest.of(page, pageableSize, sort.get());
        }
        return pageRequest;
    }
}
