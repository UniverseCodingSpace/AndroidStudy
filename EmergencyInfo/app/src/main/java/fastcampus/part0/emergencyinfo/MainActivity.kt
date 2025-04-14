package fastcampus.part0.emergencyinfo

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.isVisible
import fastcampus.part0.emergencyinfo.databinding.ActivityMainBinding
import fastcampus.part0.emergencyinfo.ui.theme.EmergencyInfoTheme

class MainActivity : ComponentActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goInputActivityButton.setOnClickListener {
            val intent =  Intent(this, EditActivity::class.java)
            intent.putExtra("name", binding.nameValueTextView.text.toString())
            intent.putExtra("tel", binding.phoneBookValueTextView.text.toString())
            intent.putExtra("age", 40)
            startActivity(intent)
        }

        binding.deleteButton.setOnClickListener {
            deleteData()
        }

        binding.phoneBookLayer.setOnClickListener{
            val intent = with(Intent(Intent.ACTION_VIEW)) {
                val phoneNumber = binding.phoneBookValueTextView.text.toString()
                    .replace("-", "")
                data = Uri.parse("sms:$phoneNumber")
                startActivity(this)
            }
        }


    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    private fun deleteData() {
        with(getSharedPreferences(USER_INFOORMATION, MODE_PRIVATE).edit()) {
            clear()
            apply()
            getData()
        }
        Toast.makeText(this, "Init complete", Toast.LENGTH_SHORT).show()

    }
    private fun getData() {
        with(getSharedPreferences(USER_INFOORMATION, Context.MODE_PRIVATE)) {
            binding.nameValueTextView.setText(getString(NAME, "TBD"))
            binding.birthValueTextView.setText(getString(BIRTHDATE, "TBD"))
            binding.bloodValueTextView.setText(getString(BLOOD_TYPE, "TBD"))
            binding.phoneBookValueTextView.setText(getString(PHONE_BOOK, "TBD"))

            val warning  = getString(WARNING, "TBD")
            binding.warningTextView.isVisible = warning.isNullOrEmpty().not()
            binding.warningValueTextView.isVisible = warning.isNullOrEmpty().not()

            if(!warning.isNullOrEmpty()) {
                binding.warningValueTextView.setText(warning)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EmergencyInfoTheme {
        Greeting("Android")
    }
}