package com.super_rabbit.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.super_rabbit.demo.wheel_picker_adapters.WPDayPickerAdapter
import com.super_rabbit.wheel_picker.OnValueChangeListener
import com.super_rabbit.wheel_picker.WheelPicker
import kotlinx.android.synthetic.main.activity_normal_number_picker.*

class NormalNumberPicker : AppCompatActivity() {
    private var mIsRoundedWrapPreferred = false
    private var mWheelItemCount = 5
    private var mCurSelectedTextColor = R.color.color_4_blue
    private var mIsDayPicker = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal_number_picker)

        //Set rounded wrap enable
        numberPicker.setSelectorRoundedWrapPreferred(true)
        //Set wheel item count
        numberPicker.setWheelItemCount(5)
        //Set wheel max index
        numberPicker.setMax(1000)
        //Set wheel min index
        numberPicker.setMin(-1000)
        //Set selected text color
        numberPicker.setSelectedTextColor(R.color.color_4_blue)
        //Set unselected text color
        numberPicker.setUnselectedTextColor(R.color.color_3_dark_blue)
        //Set user defined adapter
        numberPicker.setAdapter(WPDayPickerAdapter())

        // OnValueChangeListener
        val context = this
        numberPicker.setOnValueChangeListener(object : OnValueChangeListener{
            override fun onValueChange(picker: WheelPicker, oldVal: String, newVal: String) {
                val out = String.format("Current: %s", newVal)
                Toast.makeText(context, out, Toast.LENGTH_SHORT).show()
            }
        })

        set_wrap.setOnClickListener({
            mIsRoundedWrapPreferred = !mIsRoundedWrapPreferred
            numberPicker.setSelectorRoundedWrapPreferred(mIsRoundedWrapPreferred)
            set_wrap.text = String.format("Set wrap (current = %s)", mIsRoundedWrapPreferred.toString())
            numberPicker.reset()
        })

         set_wheel_item_count.setOnClickListener({
             mWheelItemCount = if (mWheelItemCount == 5){
                 3
             } else {
                 5
             }
             numberPicker.setWheelItemCount(mWheelItemCount)
             set_wheel_item_count.text = String.format("Set wheel item count (current = %s)", mWheelItemCount.toString())
         })

        set_selected_color.setOnClickListener({
            if (mCurSelectedTextColor == R.color.color_4_blue){
                mCurSelectedTextColor = R.color.color_7_yellow
                set_selected_color.text = String.format("set selected color (current = yellow)")
            } else {
                mCurSelectedTextColor = R.color.color_4_blue
                set_selected_color.text = String.format("set selected color (current = blue)")
            }
            numberPicker.setSelectedTextColor(mCurSelectedTextColor)
        })
        set_style.setOnClickListener{
            mIsDayPicker = !mIsDayPicker
            if (mIsDayPicker) {
                set_style.text = "set picker style (current = day picker)"
                numberPicker.setAdapter(WPDayPickerAdapter())
                numberPicker.requestLayout()
            } else {
                set_style.text = "set picker style (current = normal number picker)"
                numberPicker.setAdapter(null)
                numberPicker.setMax(9)
                numberPicker.setMin(0)
                numberPicker.reset()
            }
        }
    }
}
