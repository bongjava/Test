package com.bongjava.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public final static int ROW_COUNT = 10;
    public final static int COL_COUNT = 10;

    private int[][] boards;
    private GridLayout glMineBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        glMineBoard = findViewById(R.id.gl_mine_board);

        init();
        calculate();
        display();
    }

    private void init() {
        boards = new int[ROW_COUNT][COL_COUNT];

        // row, col이 같은 경우를 지뢰로 지정함.
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col < COL_COUNT; col++) {
                if (row == col) {
                    boards[row][col] = -1;
                }
            }
        }
    }

    private void calculate() {
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col < COL_COUNT; col++) {
                if (boards[row][col] == -1) {
                    continue;
                }

                for (int n = -1; n <= 1; n++) {
                    for (int m = -1; m <= 1; m++) {
                        if (n == 0 && m == 0) {
                            continue;
                        }
                        if (row + n >= 0 && col + m >= 0
                                && row + n < ROW_COUNT  && col + m < COL_COUNT) {
                            if (boards[row + n][col + m] == -1) {
                                boards[row][col]++;
                            }
                        }
                    }
                }
            }
        }
    }

    private void display() {
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col < COL_COUNT; col++) {
                TextView tv = new TextView(this);
                if (boards[row][col] == -1) {
                    tv.setText("*");
                    glMineBoard.addView(tv);
                } else {
                    tv.setText(String.valueOf(boards[row][col]));
                    glMineBoard.addView(tv);
                }
            }
        }
    }
}
