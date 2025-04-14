package fastcampus.part0.emergencyinfo

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.DatePickerDialog
import androidx.core.view.isVisible
import fastcampus.part0.emergencyinfo.databinding.ActivityEditBinding

class EditActivity : ComponentActivity() {
    private lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("intent name",intent.getStringExtra("name").toString())
        Log.d("intent tel", intent.getStringExtra("tel").toString())
        Log.d("intent tel", intent.getIntExtra("age", 0).toString())
        binding.bloodTypeSpinner.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.blood_types,
            android.R.layout.simple_list_item_1
        )

        binding.birthLayer.setOnClickListener {
            val listener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                binding.birthValueTextView.text = "$year-${month.inc()}-$dayOfMonth"
            }
            DatePickerDialog(
                this,
                listener,
                2000,
                1,
                1
            ).show()
        }

        binding.warningCheckBox.setOnCheckedChangeListener { _, isChecked ->
            binding.warningValueEditView.isVisible = isChecked
        }

        binding.warningValueEditView.isVisible = binding.warningCheckBox.isChecked

        binding.saveButton.setOnClickListener {
            saveData()
            finish()
        }



    }
    private fun saveData() {
        with(getSharedPreferences(USER_INFOORMATION, Context.MODE_PRIVATE).edit()) {
            putString(NAME, binding.nameValueEditText.text.toString())
            putString(BLOOD_TYPE, getBloodType())
            putString(PHONE_BOOK, binding.phoneBookValueTextView.text.toString())
            putString(BIRTHDATE, binding.birthValueTextView.text.toString())
            putString(WARNING, getWarning())
            apply()
            Log.d("SaveData","Saved")
        }

        Toast.makeText(this, "Save complete", Toast.LENGTH_SHORT).show()
    }

    private fun getBloodType() : String {
        val bloodType = binding.bloodTypeSpinner.selectedItem.toString()
        val bloodSign = if(binding.bloodTypePlusRadioButton.isChecked) "+" else "-"
        return "$bloodSign$bloodType"
    }

    private fun getWarning() : String{
        if(binding.warningCheckBox.isChecked) {
            return binding.warningValueEditView.text.toString()
        }else {
            return ""
        }
    }


}