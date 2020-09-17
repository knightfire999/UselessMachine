package com.example.uselessmachine

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.os.CountDownTimer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //the one function that View.onClickListener has is onClick(v:View!)
        //this lambda below is implementing that one function onClick without really mentioning it
        //explicitly. The one parameter is referenced by "it". So to access that view, I can use "it"
        //lambda → anonymous function
        //onClick(view : View) is the function being implemented by the lambda
        //when there's one parameter in the function, "it" is used to refer to that parameter
        //${} is a string template

        button_main_look_busy.setOnClickListener {
            Toast.makeText(this, "Hello, this is the text on the ${(it as Button).text.toString()}", Toast.LENGTH_SHORT).show()
        }

        //to listen to a swtich, you can use the onCheckChangedListener

        switch_main_useless.setOnCheckedChangeListener { compoundButton, isChecked ->
            val message = if(isChecked) "Switch is ON" else "Switch is OFF"
            val millis = (Math.random() * 10000 + 1000).toInt()
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            val uncheckTimer = object: CountDownTimer(millis.toLong(), 1000) {
                override fun onFinish() {
                    layout_main.setBackgroundColor(Color.rgb(255, 255, 255))
                    switch_main_useless.isChecked = false
                }

                override fun onTick(millisRemaining: Long) {
                    layout_main.setBackgroundColor(Color.rgb((0..255).random(), (0..255).random(), (0..255).random()))
                    if(!switch_main_useless.isChecked) {
                        cancel()
                        layout_main.setBackgroundColor(Color.rgb(255, 255, 255))
                    }
                }
            }
            uncheckTimer.start()
        }

        button_main_self_destruct.setOnClickListener {
            Toast.makeText(this, "Activating self-destruct.", Toast.LENGTH_LONG).show()
            val selfDestructTimer = object: CountDownTimer(10000, 500) {
                override fun onTick(millisRemaining: Long) {
                    val timeLeft = millisRemaining.toInt()
                    button_main_self_destruct.text = (millisRemaining/1000).toString()
                    if((millisRemaining/1000) % 2 == 0.toLong()) {
                        layout_main.setBackgroundColor(Color.rgb(255, 0, 0))
                    }
                    else if((millisRemaining/500) % 2 == 0.toLong() && millisRemaining < 4000) {
                        layout_main.setBackgroundColor(Color.rgb(255, 0, 0))
                    }
                    else {
                        layout_main.setBackgroundColor(Color.rgb(255, 255, 255))
                    }
                }
                override fun onFinish() {
                    finish()
                }
            }
            selfDestructTimer.start()
        }
    }
}