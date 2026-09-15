package br.pucpr.table.model;


public class PagedTableData implements TableData {
  private final TableData data;
  private final int pageSize;
  private int page;

  public PagedTableData(TableData data, int pageSize, int page) {
    if (data == null) {
      throw new IllegalArgumentException("Data cannot be null");
    }
    if (pageSize <= 0) {
      throw new IllegalArgumentException("Page size must be greater than zero");
    }
    this.data = data;
    this.pageSize = pageSize;
    setPage(page);
  }

  public PagedTableData(TableData data, int pageSize) {
    this(data, pageSize, 0);
  }

  public int getPageSize() {
    return pageSize;
  }

  public int getPage() {
    return page;
  }

  /** Quantidade total de páginas, considerando o total de linhas dos dados originais. */
  public int getPageCount() {
    return Math.max(1, (int) Math.ceil(data.rowCount() / (double) pageSize));
  }

  public PagedTableData setPage(int page) {
    if (page < 0 || page >= getPageCount()) {
      throw new IllegalArgumentException(
          "Page must be between 0 and %d".formatted(getPageCount() - 1));
    }
    this.page = page;
    return this;
  }

  public boolean hasNextPage() {
    return page < getPageCount() - 1;
  }

  public boolean hasPreviousPage() {
    return page > 0;
  }

  public PagedTableData nextPage() {
    if (hasNextPage()) {
      page++;
    }
    return this;
  }

  public PagedTableData previousPage() {
    if (hasPreviousPage()) {
      page--;
    }
    return this;
  }

  private int offset() {
    return page * pageSize;
  }

  @Override
  public int rowCount() {
    return Math.max(0, Math.min(pageSize, data.rowCount() - offset()));
  }

  @Override
  public int colCount() {
    return data.colCount();
  }

  @Override
  public String header(int col) {
    return data.header(col);
  }

  @Override
  public String get(int row, int col) {
    return data.get(offset() + row, col);
  }

  @Override
  public void addListener(TableDataListener listener) {
    data.addListener(listener);
  }

  @Override
  public void removeListener(TableDataListener listener) {
    data.removeListener(listener);
  }
}
