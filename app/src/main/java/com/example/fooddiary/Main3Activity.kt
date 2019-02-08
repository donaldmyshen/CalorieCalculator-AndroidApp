package com.example.fooddiary

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.enter_sport_and_cal.*

class Main3Activity : AppCompatActivity(){
    companion object {
        const val EXTRA1 = "cal2"
        const val EXTRA2 = "sport"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enter_sport_and_cal)
        confirm3.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View) {
                val cal2 = Calories2.text.toString()
                val sport = sport_and_cal.text.toString()
                val res2 = Intent()
                res2.putExtra(Main3Activity.EXTRA1,cal2)
                res2.putExtra(Main3Activity.EXTRA2,sport)
                setResult(Activity.RESULT_OK, res2)
                finish()
            }
        })
    }
}