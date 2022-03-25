package `in`.bitlogger.kikstart.ui.fragment

import `in`.bitlogger.kikstart.R
import `in`.bitlogger.kikstart.databinding.FragmentSigninBinding
import `in`.bitlogger.kikstart.db.model.LoginResponseModel
import `in`.bitlogger.kikstart.ui.MainActivity
import `in`.bitlogger.kikstart.db.model.SigninModel
import `in`.bitlogger.kikstart.interfaces.CoroutineDataPassCallbacks
import `in`.bitlogger.kikstart.ui.vm.LoginVM
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SigninFragment : Fragment() {

    private lateinit var signInBinding: FragmentSigninBinding
    private val loginVM : LoginVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        signInBinding = FragmentSigninBinding.inflate(inflater)
        val view = signInBinding.root

        signInBinding.signinBtn.setOnClickListener {

            val callback = object : CoroutineDataPassCallbacks {
                override fun isDataLoading(dataLoading: Boolean) {
                    if (!dataLoading) {
                        signInBinding.signinIndicator.visibility = View.GONE
                    } else {
                        signInBinding.signinIndicator.visibility = View.VISIBLE
                    }
                }

                override fun <T> onLoadComplete(data: T) {
                    val res = data as LoginResponseModel
                    Toast.makeText(requireContext(), res.message, Toast.LENGTH_SHORT).show()
                    if (res.success) {
                        val intent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    }
                }

                override fun onLoadFailed(errorCode: String, errorMessage: String) {
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                }
            }

            loginVM.signInUser(
                SigninModel(
                signInBinding.signinEmailEdittext.text.toString(),
                signInBinding.signinPasswordEdittext.text.toString()
            ), callback)
        }

        signInBinding.signinSignupBtn.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.login_container, SignUpFragment())
            }
        }
        return view
    }
}