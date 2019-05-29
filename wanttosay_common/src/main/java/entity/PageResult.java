package entity;

import java.util.List;

/**
 *
 * @param <T>
 */
public class PageResult<T> {
    private long total;

    private List<T> rows;

    public PageResult(long total, List<T> rows) {
        super();
        this.total = total;
        this.rows = rows;
    }
}
