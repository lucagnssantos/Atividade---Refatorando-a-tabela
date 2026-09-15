package br.pucpr.user;

import br.pucpr.table.model.ColumnData;

public class NameColumn implements ColumnData<User> {
  @Override
  public String header() {
    return "          NOME           ";
  }

  @Override
  public String get(User user) {
    return user.name() == null || user.name().isEmpty() ? "NÃO INFORMADO" : user.name();
  }
}
