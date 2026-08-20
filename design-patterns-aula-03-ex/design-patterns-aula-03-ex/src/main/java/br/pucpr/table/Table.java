package br.pucpr.table;

import br.pucpr.user.Theme;
import java.util.List;

public class Table {
    public void print (TableData data, boolean alignRight, Theme theme) {
        var headers = data.getHeaders();
        var rows = data.getRows();

        if (rows == null || rows.isEmpty()) {
            System.out.println("ERRO: Lista vazia ou nula");
            return;
        }

        int[] widths = new int[headers.size()];
        for (int i = 0; i < headers.size(); i++) {
            widths[i] = headers.get(i).length();
        }

        int borderWidth = 1;
        for (int w : widths) {
            borderWidth += w + 3; //"%-ws" + " | " final
        }

        var borderChar = theme.getBorderChar();
        var sb = new StringBuilder();

        sb.repeat(borderChar, borderWidth).append("\n");
        sb.append(formatRow(headers, widths)).append("\n");
        sb.repeat(borderChar, borderWidth).append("\n");

        for (var row : rows) {
            sb.append(formatRow(row, widths)).append("\n");
        }

        sb.repeat(borderChar, borderWidth).append("\n");

        if (alignRight) {
            var lines = sb.toString().split("\n");
            for (var line : lines) {
                System.out.println("                " + line);
            }
        } else {
            System.out.print(sb);
        }
    }

    private String formatRow(List<String> values, int[] widths) {
        var sb = new StringBuilder("|");
        for (int i = 0; i < values.size(); i++) {
            sb.append(String.format(" %-" + widths[i] + "s |", values.get(i)));
        }
        return sb.toString();
    }
}
