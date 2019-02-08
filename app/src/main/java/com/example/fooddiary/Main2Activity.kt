package com.example.fooddiary

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.enter_food_and_cal.*

class Main2Activity : AppCompatActivity(){
    companion object {
        const val EXTRA1 = "cal"
        const val EXTRA2 = "food"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enter_food_and_cal)
        // when click confirm, read and store the user's entered value
        confirm2.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View) {
                val cal = Calories.text.toString()
                val food = food_and_cal.text.toString()
                val res = Intent()
                res.putExtra(Main2Activity.EXTRA1,cal)
                res.putExtra(Main2Activity.EXTRA2,food)
                setResult(Activity.RESULT_OK, res)
                finish()
            }
        })
    }
}