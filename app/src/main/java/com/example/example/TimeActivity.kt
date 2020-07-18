package com.example.example

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class TimeActivity : AppCompatActivity() {
    private var hourSpinner: Spinner? = null
    private var minuteSpinner: Spinner? = null
    private var amPmSpinner: Spinner? = null

    private var hour: Int = 0
    private var minute: Int = 0
    private var amPm: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)
        setUi()
    }

    private fun setUi() {
        val c = Calendar.getInstance()
        val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.US)
        findViewById<TextView>(R.id.date_text_view).text = sdf.format(c.time)
        val hours = resources.getStringArray(R.array.hours)
        hourSpinner = findViewById(R.id.hour_spinner)
        val hourAdapter = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_item,
            hours
        )
        hourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        hourSpinner?.adapter = hourAdapter
        hourSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                hour = hours[position].toInt()
            }
        }

        val minutes = resources.getStringArray(R.array.minutes)
        minuteSpinner = findViewById(R.id.minute_spinner)
        val minuteAdapter =  ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_item,
            minutes
        )
        minuteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        minuteSpinner?.adapter = minuteAdapter
        minuteSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                minute = minutes[position].toInt()
            }
        }

        amPmSpinner = findViewById(R.id.am_pm_spinner)
        val amPmAdapter =  ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.am_pm)
        )
        amPmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        amPmSpinner?.adapter = amPmAdapter
        amPmSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                amPm = position
            }
        }

        findViewById<Button>(R.id.save_button).setOnClickListener {
            c.set(Calendar.HOUR, hour)
            c.set(Calendar.MINUTE, minute)
            c.set(Calendar.AM_PM, amPm)

            val sdf = SimpleDateFormat("MM/dd/yyyy HH:mm a", Locale.US)
            Toast.makeText(this, sdf.format(c.time), Toast.LENGTH_LONG).show()
        }
    }
}
