package `in`.bitlogger.kikstart.ui

import `in`.bitlogger.kikstart.databinding.ActivityLoginBinding
import `in`.bitlogger.kikstart.ui.fragment.SigninFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var loginActivity: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginActivity = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginActivity.root)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(loginActivity.loginContainer.id, SigninFragment())
        transaction.commit()
    }
}