package com.example.pawel.hangman;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    protected String mystery_word;
    protected String guess_word;
    int gallows_state;
    String[] words;


    public void generate(View view) {
        int number = (int)(words.length * Math.random());
        mystery_word = words[number];
        Toast.makeText(getApplicationContext(), "Rozpoczynamy grę", Toast.LENGTH_SHORT).show();
        StringBuilder builder = new StringBuilder(""); // obiekt tworzacy stringa
        for(int i = 0; i < mystery_word.length(); ++i) {
            builder.append("-");
        }
        guess_word = builder.toString();
        TextView myWord = (TextView) findViewById(R.id.textShow);
        myWord.setText(guess_word);
        Button guessWord = (Button) findViewById(R.id.buttonCheckLetter);
        Button mysteryWord = (Button) findViewById(R.id.buttonCheckWord);

        guessWord.setClickable(true);
        mysteryWord.setClickable(true);
        System.out.println(mystery_word);

    }

    public void checkLetter(View view){
        EditText textPut = (EditText)findViewById(R.id.textPut);
        String tekst = textPut.getText().toString();
        if(tekst.length() == 0) {
            Toast.makeText(getApplicationContext(), "Wpisz coś", Toast.LENGTH_SHORT).show();
        }
        else if(tekst.length() > 1) {
            Toast.makeText(getApplicationContext(), "Proszę o jedną literę!", Toast.LENGTH_SHORT).show();
        }
        else {
            char letter = tekst.charAt(0);
            boolean kolejnyObraz = true; // czy kolejny obrazek

            StringBuilder tmp = new StringBuilder(guess_word);
            for(int i = 0; i < mystery_word.length(); ++i) {
                if(mystery_word.charAt(i) == letter) {
                    if(guess_word.charAt(i) == '-') {

                        kolejnyObraz = false;
                        tmp.setCharAt(i, letter);
                    }
                    else {
                        kolejnyObraz = false; // ta literka już była
                        Toast.makeText(getApplicationContext(), "Ta litera już była!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            guess_word = tmp.toString();
            TextView textShow = (TextView) findViewById(R.id.textShow);
            textShow.setText(guess_word);
            if(kolejnyObraz) {
                gallows_state++;
                zmienObrazek();
                Toast.makeText(getApplicationContext(), "Niestety! Nie udało Ci się", Toast.LENGTH_SHORT).show();
                if(gallows_state > 11) {
                    Toast.makeText(getApplicationContext(), "Przegraleś!", Toast.LENGTH_SHORT).show();
                    mystery_word = guess_word = null;
                    gallows_state = 0;
                    setContentView(R.layout.activity_main);
                }}
            else{
                Toast.makeText(getApplicationContext(), "Odgadłeś nową literkę", Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void checkWord(View view){
        EditText textPut = (EditText)findViewById(R.id.textPut);
        String tekst = textPut.getText().toString();
        if(tekst.equals(mystery_word)) {
            Toast.makeText(getApplicationContext(), "Brawo! Wygrałeś!", Toast.LENGTH_SHORT).show();
            mystery_word = guess_word = null;
            gallows_state = 0;
            setContentView(R.layout.activity_main);
        }
        else {
            Toast.makeText(getApplicationContext(), "Pudło", Toast.LENGTH_SHORT).show();
            gallows_state++;
            zmienObrazek();

        }



    }


    public void zmienObrazek() {
        ImageView obrazek = (ImageView) findViewById(R.id.imageHangman);

        switch(gallows_state) {
            case 0:
                obrazek.setImageResource(R.drawable.hangman0);
                break;
            case 1:
                obrazek.setImageResource(R.drawable.hangman1);
                break;
            case 2:
                obrazek.setImageResource(R.drawable.hangman2);
                break;
            case 3:
                obrazek.setImageResource(R.drawable.hangman3);
                break;
            case 4:
                obrazek.setImageResource(R.drawable.hangman4);
                break;
            case 5:
                obrazek.setImageResource(R.drawable.hangman5);
                break;
            case 6:
                obrazek.setImageResource(R.drawable.hangman6);
                break;
            case 7:
                obrazek.setImageResource(R.drawable.hangman7);
                break;
            case 8:
                obrazek.setImageResource(R.drawable.hangman8);
                break;
            case 9:
                obrazek.setImageResource(R.drawable.hangman9);
                break;
            case 10:
                obrazek.setImageResource(R.drawable.hangman10);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // read the string-array into words array
        words = getResources().getStringArray(R.array.words); }


        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }
