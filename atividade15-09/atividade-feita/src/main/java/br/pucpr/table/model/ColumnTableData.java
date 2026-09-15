package br.pucpr.table.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ColumnTableData<T> implements TableData {

  private final List<ColumnData<? super T>> columns;
  private final List<T> data;

  private final List<TableDataListener> listeners = new ArrayList<>();

  public ColumnTableData(
          Collection<? extends T> data,
          Collection<? extends ColumnData<? super T>> columns) {

    this.columns = new ArrayList<>(columns);
    this.data = new ArrayList<>(data);
  }

  @SafeVarargs
  public ColumnTableData(
          Collection<? extends T> data,
          ColumnData<? super T>... columns) {

    this(data, Arrays.asList(columns));
  }

  @Override
  public int rowCount() {
    return data.size();
  }

  @Override
  public int colCount() {
    return columns.size();
  }

  @Override
  public String header(int col) {
    return columns.get(col).header();
  }

  @Override
  public String get(int row, int col) {
    var line = data.get(row);
    return columns.get(col).get(line);
  }

  @Override
  public void addListener(TableDataListener listener) {
    listeners.add(listener);
  }

  @Override
  public void removeListener(TableDataListener listener) {
    listeners.remove(listener);
  }

  private void notifyListeners() {
    for (var listener : listeners) {
      listener.dataChanged();
    }
  }

  public void add(T item) {
    data.add(item);
    notifyListeners();
  }

  public void remove(T item) {
    data.remove(item);
    notifyListeners();
  }

  public void clear() {
    data.clear();
    notifyListeners();
  }
}