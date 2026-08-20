package br.pucpr.table;
import java.util.List;

public interface TableData {
  // Monte aqui sua interface TableData
    List<String> getHeaders();
    List<List<String>> getRows();

}