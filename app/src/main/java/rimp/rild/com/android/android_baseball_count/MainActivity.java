package rimp.rild.com.android.android_baseball_count;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int strike;
    int ball;
    int out;

    TextView strikeTextView;
    TextView ballTextView;
    TextView outTextView;

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
        strikeTextView = (TextView) findViewById(R.id.count_strike);
        ballTextView = (TextView) findViewById(R.id.count_ball);
        outTextView = (TextView) findViewById(R.id.count_out);
        strikeButton = (Button) findViewById(R.id.button_strike);
        ballButton = (Button) findViewById(R.id.button_ball);
        outButton = (Button) findViewById(R.id.button_out);
    }

    private void setOnClickToButtons() {
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
        }
    }

    private void plusOutCount() {
        if (out < 2) {
            out++;
        } else {
            out = 0;
        }
    }

    private void updateTextViews() {
        strikeTextView.setText(String.valueOf(strike));
        ballTextView.setText(String.valueOf(ball));
        outTextView.setText(String.valueOf(out));
    }

    private void resetValues() {
        strike = 0;
        ball = 0;
        out = 0;
    }

}
