package by.itransition.game.main;

import com.bethecoder.ascii_table.ASCIITable;

public class Table {

    static String[] gettingHeader(String[] moves) {
        String[] header = new String[moves.length + 1];
        header[0] = "PC \\ User";
        for (int i = 1; i <= moves.length; i++) {
            header[i] = moves[i - 1];
        }
        return header;
    }

    static String[][] gettingData(String[] header) {
        String[][] data = new String[header.length][header.length + 1];
        for (int i = 0; i < header.length; i++) {
            data[i][0] = header[i];
        }
        for (int i = 0; i < header.length; i++) {
            for (int j = 1; j <= header.length; j++) {
                data[i][j] = Rules.gettingWinner(header.length, j, i + 1);
            }
        }
        return data;
    }

    static void printingTable(String[] header, String[][] data) {
        ASCIITable.getInstance().printTable(header, data);
    }

}
