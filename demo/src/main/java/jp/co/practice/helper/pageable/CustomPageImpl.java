package jp.co.practice.helper.pageable;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CustomPageImpl<T> extends PageImpl<T> implements CustomPage<T> {

    private String condition;

    public CustomPageImpl(List<T> content, Pageable pageable, long total, String condition) {
        super(content, pageable, total);
        this.condition = condition;
    }

    public CustomPageImpl(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public CustomPageImpl(List<T> content) {
        super(content);
    }

    @Override
    public String getCondition() {
        return this.condition;
    }

    @Override
    public void setCondition(String condition) {
        this.condition = condition;
    }
}

