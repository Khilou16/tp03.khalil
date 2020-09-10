package com.shaveshan.tictoctac;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView scoreP1 , scoreP2 , turnView ;
    Button btn1 , btn2 , btn3 , btn4 , btn5 , btn6 , btn7 , btn8 , btn9 , reset , newGame ;
    boolean turn , winner ;
    int numberTurn , c1 , c2 , c3 , c4 , c5 , c6 , c7 , c8 , c9 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play();
    }
    private void control(){
        turn = true;
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        reset = findViewById(R.id.reset);
        scoreP1 = findViewById(R.id.score1);
        scoreP2 = findViewById(R.id.score2);
        turnView = findViewById(R.id.turnView);
        newGame = findViewById(R.id.newGame);
    }
    private void editControl(){
        winner = false;
        btn1.setText(" ");
        btn2.setText(" ");
        btn3.setText(" ");
        btn4.setText(" ");
        btn5.setText(" ");
        btn6.setText(" ");
        btn7.setText(" ");
        btn8.setText(" ");
        btn9.setText(" ");

        btn1.setTextColor( Color.BLACK );
        btn2.setTextColor( Color.BLACK );
        btn3.setTextColor( Color.BLACK );
        btn4.setTextColor( Color.BLACK );
        btn5.setTextColor( Color.BLACK );
        btn6.setTextColor( Color.BLACK );
        btn7.setTextColor( Color.BLACK );
        btn8.setTextColor( Color.BLACK );
        btn9.setTextColor( Color.BLACK );

        numberTurn = c1 = c2 = c3 = c4 = c5 = c6 = c7 = c8 = c9 = 0;
        turnView.setText(turn ? "X Turn" : "O Turn");
        Toast.makeText( this, turn ? "X Turn" : "O Turn", Toast.LENGTH_SHORT).show();
    }

    private int[] getVide(){
        int [] vide = new int[9];
        vide[0] = c1 ;
        vide[1] = c2 ;
        vide[2] = c3 ;
        vide[3] = c4 ;
        vide[4] = c5 ;
        vide[5] = c6 ;
        vide[6] = c7 ;
        vide[7] = c8 ;
        vide[8] = c9 ;
        return vide ;
        }
    private void bot(){
        if(true) {
            int vide[] = getVide();
            while (true){
                int bot = (int) Math.floor(Math.random() * 10 + 1);
                if(vide[bot] == 0){
                    btn9.setText("bot");
                    break;
                }
            }
        }
    }
    private void resetGame(){
        Toast.makeText( this, "Reset Game", Toast.LENGTH_SHORT ).show();
        turn = true;
        scoreP1.setText("00");
        scoreP2.setText("00");
        editControl();
    }
    private void validation(){
        numberTurn ++ ;
        turn = !turn;
        // Game Simple
        if(numberTurn < 5) {
            Toast.makeText(this, turn ? "X Turn" : "O Turn" , Toast.LENGTH_SHORT).show();
            turnView.setText(turn ? "X Turn" : "O Turn" );
        }
        // test win
        else{
            // tq turn = true => X turn => test pour O win
            // 2 : test pour O win --- 1 : test pour X win
            int n = turn ? 2 : 1 ;
            // 8 cas
            boolean t1 = c1 == c2 && c2 == c3 && c3 == n ? true : false ,
                    t2 = c4 == c5 && c5 == c6 && c6 == n ? true : false ,
                    t3 = c7 == c8 && c8 == c9 && c9 == n ? true : false ,
                    t4 = c1 == c4 && c4 == c7 && c7 == n ? true : false ,
                    t5 = c2 == c5 && c5 == c8 && c8 == n ? true : false ,
                    t6 = c3 == c6 && c6 == c9 && c9 == n ? true : false ,
                    t7 = c1 == c5 && c5 == c9 && c9 == n ? true : false ,
                    t8 = c3 == c5 && c5 == c7 && c7 == n ? true : false ;

            if( t1 || t2 || t3 || t4 || t5 || t6|| t7 || t8 ) {
                win(t1,t2,t3,t4,t5,t6,t7,t8, Color.GREEN);
                c1 = Integer.parseInt( n == 1 ? scoreP1.getText().toString() : scoreP2.getText().toString()) + 1 ;
                (n == 1 ? scoreP1 : scoreP2).setText( c1 <10 ? "0" +  c1  : String.valueOf(c1));
                winner = true;
                blockAll();
                Toast.makeText(this, n==1 ? " X Win " : " O Win " , Toast.LENGTH_LONG).show();
                turnView.setText( n==1 ? " X Win " :" O Win " );
                turn = !turn;
            }
            else {
                Toast.makeText(this, turn ? "X Turn" : "O Turn" , Toast.LENGTH_SHORT).show();
                turnView.setText(turn ? "X Turn" : "O Turn" );
            }
        }

        // Null
        if(numberTurn > 8 && !winner){
            blockAll();
            Toast.makeText( this, "Null",Toast.LENGTH_LONG ).show();
            turnView.setText("Null");
            turn = ( Math.floor(  Math.random() * 10 ) % 2 == 0 )  ? true : false ;
        }
        //bot();
    }
    private void caseColor( int caseA , int caseB , int caseC, int colorWin){
        // 3 case
        for (int i = 0; i < 3; i++) {
            switch ( i == 1 ? caseA : ( i == 2 ? caseB : caseC) ) {
                case 1:
                    btn1.setTextColor(colorWin);
                    break;
                case 2:
                    btn2.setTextColor(colorWin);
                    break;
                case 3:
                    btn3.setTextColor(colorWin);
                    break;
                case 4:
                    btn4.setTextColor(colorWin);
                    break;
                case 5:
                    btn5.setTextColor(colorWin);
                    break;
                case 6:
                    btn6.setTextColor(colorWin);
                    break;
                case 7:
                    btn7.setTextColor(colorWin);
                    break;
                case 8:
                    btn8.setTextColor(colorWin);
                    break;
                case 9:
                    btn9.setTextColor(colorWin);
                    break;
            }
        }
    }
    private void win(boolean t1,boolean t2,boolean t3,boolean t4,boolean t5,boolean t6,boolean t7,boolean t8, int colorWin){
        switch (t1 ? 1 : (t2 ? 2 : (t3 ? 3 : (t4 ? 4 : (t5? 5 : (t6 ? 6: (t7 ? 7 : 8))))))){
            case 1:
                caseColor(1,2,3,colorWin);
                break;
            case 2:
                caseColor(4,5,6,colorWin);
                break;
            case 3:
                caseColor(7,8,9,colorWin);
                break;
            case 4:
                caseColor(1,4,7,colorWin);
                break;
            case 5:
                caseColor(2,5,8,colorWin);
                break;
            case 6:
                caseColor(3,6,9,colorWin);
                break;
            case 7:
                caseColor(1,5,9,colorWin);
                break;
            case 8:
                caseColor(3,5,7,colorWin);
                break;
        }


    }

    private  void  blockAll(){
        c1 = c2 = c3 = c4 = c5 = c6 = c7 = c8 = c9  = 3;
    }

    private void play(){
        control();
        resetGame();
        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetGame();
            }
        });
        newGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editControl();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(c1 == 0){
                    btn1.setText( turn ? "X" : "O");
                    c1 = turn ? 1 : 2 ;
                    validation();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(c2 == 0){
                    btn2.setText( turn ? "X" : "O");
                    c2 = turn ? 1 : 2 ;
                    validation();
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(c3 == 0){
                    btn3.setText( turn ? "X" : "O");
                    c3 = turn ? 1 : 2 ;
                    validation();
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(c4 == 0){
                    btn4.setText( turn ? "X" : "O");
                    c4 = turn ? 1 : 2 ;
                    validation();
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(c5 == 0){
                    btn5.setText( turn ? "X" : "O");
                    c5 = turn ? 1 : 2 ;
                    validation();
                }
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(c6 == 0){
                    btn6.setText( turn ? "X" : "O");
                    c6 = turn ? 1 : 2 ;
                    validation();
                }
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(c7 == 0){
                    btn7.setText( turn ? "X" : "O");
                    c7 = turn ? 1 : 2 ;
                    validation();
                }
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(c8 == 0){
                    btn8.setText( turn ? "X" : "O");
                    c8 = turn ? 1 : 2 ;
                    validation();
                }
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(c9 == 0){
                    btn9.setText( turn ? "X" : "O");
                    c9 = turn ? 1 : 2 ;
                    validation();
                }
            }
        });
    }
}
