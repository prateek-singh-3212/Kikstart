package `in`.bitlogger.kikstart.ui

import `in`.bitlogger.kikstart.databinding.ActivityCommingSoonBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CommingSoon : AppCompatActivity() {
    private lateinit var commingSoonBinding: ActivityCommingSoonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        commingSoonBinding = ActivityCommingSoonBinding.inflate(layoutInflater)
        setContentView(commingSoonBinding.root)

    }
}