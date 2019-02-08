package com.example.fooddiary

import android.app.ActionBar
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.enter_total_cal.*
import android.graphics.Color
import java.time.LocalTime
import java.util.*

data class FoodCal constructor(
    val Food : String,
    val Cal : String
)
// Testing function, hope to add sport and cal consume into the vie list
data class SportCal constructor(
    val Sport : String,
    val Cal2 : String
)
class MainActivity : AppCompatActivity(){
    private var foodList = ArrayList<FoodCal>()
    //private var sportList = ArrayList<SportCal>()
    private var totalCal = 0 // sum of all food's cal
    private var plan = 0 // set by user, today's plan
    private var adapter : FoodAdapter?=null
    private var time = LocalTime.now()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // ask for set the total cal when active
        displayDialog(R.layout.enter_total_cal)
        // button call second activity to add food and its cal
        addFood.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(this@MainActivity, Main2Activity::class.java)
                startActivityForResult(intent,1)
            }
        })
        // additional function, allowed reset the total cal plan
        reset_plan.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View) {
                displayDialog(R.layout.enter_total_cal)
            }
        })
        /*
        addSport.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View) {
                val intent2 = Intent(this@MainActivity, Main3Activity::class.java)
                startActivityForResult(intent2,1)
            }
        })
        */
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            AutoRemove(time)
            // get data from activity 2
            val cal  = data?.getStringExtra(Main2Activity.EXTRA1)
            val food = data?.getStringExtra(Main2Activity.EXTRA2)
            //val cal2  = data?.getStringExtra(Main2Activity.EXTRA1)
            //val sport = data?.getStringExtra(Main2Activity.EXTRA2)
            cal?.let {
                foodList.add(FoodCal(food.toString(), cal))
                //sportList.add(SportCal(sport.toString(), cal2.toString()))
                try {
                    // try-catch to pop a alert dialog
                    //totalCal = totalCal + cal.toInt() - cal2.toString().toInt()
                    totalCal += cal.toInt()
                } catch (e: Exception) {
                    AlertDialog.Builder(this)
                        .setTitle("Error!")
                        .setMessage("Pleas enter a number!")
                        .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                                dialog, _ -> dialog.cancel()
                        })
                        .show()
                    // let the alert dialog keep seconds
                    //Thread.sleep(2_000)
                    // When add a string instead of int, remove before show it to user
                    foodList.removeAt(foodList.size-1)
                }
                check_cal_remaing(plan,totalCal)
                findViewById<TextView>(R.id.totalCal).text = "Total Calories    " + totalCal.toString()
                //adapter = FoodAdapter(foodList,sportList,this)
                adapter = FoodAdapter(foodList,this)
                list.adapter = adapter
            }
        }
    }
    // present the text color and value
    private fun check_cal_remaing(goal: Int, totalCal: Int){
        findViewById<TextView>(R.id.calories).text =  (goal - totalCal).toString()
        if (goal - totalCal >= 0) {
            // if calories count becomes positive (after reset), turn black
            calories.setTextColor(Color.rgb(0,0,0))
        } else {
            // if calories count becomes negative, turn red
            calories.setTextColor(Color.rgb(255,0,0))
        }
    }

    private fun AutoRemove(time: LocalTime) {
        // when the system time comes to 5:00, remove all data
        if (time.hour == 5 && time.minute == 0) {
            plan = 0
            for (i in 0 .. foodList.size) {
                foodList.removeAt(i)
            }
        }
    }

    inner class FoodAdapter(val foodList: ArrayList<FoodCal>, /*val sportList: ArrayList<SportCal>,*/
                            val context: Context) : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            val view: View?
            val holder: ViewHolder
            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.foodlist, parent, false)
                holder = ViewHolder(view)
                view.tag = holder
            } else {
                view = convertView
                holder = view.tag as ViewHolder
            }
            holder.food.text = foodList[position].Food
            holder.cal.text = foodList[position].Cal

            return view
        }
        // Have to keep following func with no use
        override fun getItem(position: Int): Any {
            return foodList[position]
        }
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return foodList.size
        }
    }

    private class ViewHolder(view: View?) {
        val food: TextView
        val cal: TextView
        //val sport: TextView
        //val cal2: TextView
        init {
            this.food = view?.findViewById(R.id.food) as TextView
            this.cal = view.findViewById(R.id.cal) as TextView
            //this.sport = view?.findViewById(R.id.food) as TextView
            //this.cal2 = view.findViewById(R.id.cal) as TextView
        }
    }

    // display dialog, allow to enter plan calories
    private fun displayDialog(layout: Int) {
        val dialog = Dialog(this)
        dialog.setContentView(layout)
        val window = dialog.window
        window?.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        dialog.findViewById<Button>(R.id.confirm).setOnClickListener {
            // Java style try-catch-throw
            try {
                plan = dialog.Foods.text.toString().toInt()
            } catch (e: Exception) {
                // catch the exception to give a warning of input type
            AlertDialog.Builder(this)
                    .setTitle("Error!")
                    .setPositiveButton("Pleas enter a number!"){
                            _ , _ ->
                        displayDialog(R.layout.enter_total_cal)
                    }
                .show()
                // let the alert dialog keep seconds
                //Thread.sleep(2_000)
            }
            check_cal_remaing(plan,totalCal)
            findViewById<TextView>(R.id.calories).text =  (plan - totalCal).toString()
            findViewById<TextView>(R.id.totalCal).text = "Total Calories    " + totalCal.toString()
            dialog.dismiss()
        }
        dialog.show()
    }
}