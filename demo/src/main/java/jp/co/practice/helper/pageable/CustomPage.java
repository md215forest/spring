package jp.co.practice.helper.pageable;

import org.springframework.data.domain.Page;

public interface CustomPage<T> extends Page<T> {

    String getCondition();

    void setCondition(String condition);

}
