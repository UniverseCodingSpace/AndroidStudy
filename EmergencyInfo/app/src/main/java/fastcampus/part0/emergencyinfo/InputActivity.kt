package fastcampus.part0.emergencyinfo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import fastcampus.part0.emergencyinfo.databinding.ActivityMainBinding
import fastcampus.part0.emergencyinfo.databinding.InputActivityBinding

class InputActivity : ComponentActivity() {
    private lateinit var binding : InputActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = InputActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val message = intent.getStringExtra("intentMessage")
        Log.d("intentMessage", "$message")
//        binding.goInputActivityButton.setOnClickListener {
//            val intent = Intent(this, Input)
//        }

    }
}