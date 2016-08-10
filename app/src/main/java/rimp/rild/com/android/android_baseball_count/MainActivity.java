package rimp.rild.com.android.android_baseball_count;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    int[] points = new int[2];
    TextView[] pointTexeViews = new TextView[2];
    ImageView[] nextButton = new ImageView[4];

    int strike;
    int ball;
    int out;

    int inningNumber2;
    int inningNumber;
    String inningString = "";
    String[] inningStringSample = {"表", "裏"};

    TextView inningNumberTextView;
    TextView inningTextView;

    TextView strikeTextView;
    TextView ballTextView;
    TextView outTextView;

    ToggleButton[] baseViews = new ToggleButton[3];

    Button strikeButton;
    Button ballButton;
    Button outButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();

        resetValues();
        updateTextViews();

        setOnClickToButtons();
    }

    private void getViews() {
        pointTexeViews[0] = (TextView) findViewById(R.id.text_team_point_a);
        pointTexeViews[1] = (TextView) findViewById(R.id.text_team_point_b);
        nextButton[0] = (ImageView) findViewById(R.id.button_home_to_1);
        nextButton[1] = (ImageView) findViewById(R.id.button_1_to_2);
        nextButton[2] = (ImageView) findViewById(R.id.button_2_to_3);
        nextButton[3] = (ImageView) findViewById(R.id.button_3_to_home);

        strikeTextView = (TextView) findViewById(R.id.count_strike);
        ballTextView = (TextView) findViewById(R.id.count_ball);
        outTextView = (TextView) findViewById(R.id.count_out);
        strikeButton = (Button) findViewById(R.id.button_strike);
        ballButton = (Button) findViewById(R.id.button_ball);
        outButton = (Button) findViewById(R.id.button_out);
        inningNumberTextView = (TextView) findViewById(R.id.text_inning_number);
        inningTextView = (TextView) findViewById(R.id.text_top_and_bottom);

        baseViews[0] = (ToggleButton) findViewById(R.id.first_base);
        baseViews[1] = (ToggleButton) findViewById(R.id.second_base);
        baseViews[2] = (ToggleButton) findViewById(R.id.third_base);
    }

    private void setOnClickToButtons() {
        pointTexeViews[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                points[0]++;
                updateTextViews();
            }
        });
        pointTexeViews[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                points[1]++;
                updateTextViews();
            }
        });
        nextButton[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveHomeTo1st();
                changeBatter();
            }
        });
        nextButton[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baseViews[0].isChecked()) {
                    baseViews[0].setChecked(false);
                    moveRunner1stTo2nd();
                }
            }
        });
        nextButton[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baseViews[1].isChecked()) {
                    baseViews[1].setChecked(false);
                    moveRunner2ndTo3rd();
                }
            }
        });
        nextButton[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(baseViews[2].isChecked()) {
                    baseViews[2].setChecked(false);
                    moveRunner3rdToHome();
                }
            }
        });

        strikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusStrikeCount();
                updateTextViews();
            }
        });
        ballButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusBallCount();
                updateTextViews();
            }
        });
        outButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusOutCount();
                updateTextViews();
            }
        });
    }

    private void plusStrikeCount() {
        if (strike < 2) {
            strike++;
        } else {
            plusOutCount();
            strike = 0;
        }
    }

    private void plusBallCount() {
        if (ball < 3) {
            ball++;
        } else {
            ball = 0;
            moveHomeTo1st();
            changeBatter();
        }
    }

    private void plusOutCount() {
        if (out < 2) {
            out++;
        } else {
            out = 0;
            plusInning();
        }
    }

    private void plusInning() {
        inningNumber2++;
        updateInning();
    }

    private void changeBatter() {
        strike = 0;
        updateTextViews();
    }

    private void moveHomeTo1st() {
        if (!baseViews[0].isChecked()) {
            baseViews[0].setChecked(true);
        } else {
            moveRunner1stTo2nd();
        }
    }

    private void moveRunner1stTo2nd() {
        if (!baseViews[1].isChecked()) {
            baseViews[1].setChecked(true);
        } else {
            moveRunner2ndTo3rd();
        }
    }

    private void moveRunner2ndTo3rd() {
        if (!baseViews[2].isChecked()) {
            baseViews[2].setChecked(true);
        } else {
           moveRunner3rdToHome();
        }
    }

    private void moveRunner3rdToHome() {
        if (inningString.equals(inningStringSample[0])) {
            points[0]++;
            updateTextViews();
        } else {
            points[1]++;
            updateTextViews();
        }
    }

    private void updateInning() {
        inningNumber = inningNumber2 / 2 + 1;
        if (inningNumber2 % 2 == 0) {
            inningString = inningStringSample[0];
        } else {
            inningString = inningStringSample[1];
        }
    }

    private void updateTextViews() {
        pointTexeViews[0].setText(String.valueOf(points[0]));
        pointTexeViews[1].setText(String.valueOf(points[1]));

        strikeTextView.setText(String.valueOf(strike));
        ballTextView.setText(String.valueOf(ball));
        outTextView.setText(String.valueOf(out));
        inningNumberTextView.setText(String.valueOf(inningNumber));
        inningTextView.setText(inningString);
    }

    private void resetValues() {
        points[0] = 0;
        points[1] = 0;

        inningNumber2 = 0;
        inningNumber = 1;

        inningString = inningStringSample[0];

        strike = 0;
        ball = 0;
        out = 0;
    }

}
