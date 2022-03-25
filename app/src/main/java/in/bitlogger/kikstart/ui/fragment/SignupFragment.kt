package `in`.bitlogger.kikstart.ui.fragment

import `in`.bitlogger.kikstart.R
import `in`.bitlogger.kikstart.databinding.FragmentSignUpBinding
import `in`.bitlogger.kikstart.db.model.LoginResponseModel
import `in`.bitlogger.kikstart.ui.MainActivity
import `in`.bitlogger.kikstart.db.model.SignupModel
import `in`.bitlogger.kikstart.interfaces.CoroutineDataPassCallbacks
import `in`.bitlogger.kikstart.ui.vm.LoginVM
import `in`.bitlogger.kikstart.utils.Constants
import `in`.bitlogger.studentsolutions.utils.PreferenceManager
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var signupBinding: FragmentSignUpBinding
    private val loginVM: LoginVM by viewModels()
    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        signupBinding = FragmentSignUpBinding.inflate(inflater)
        val view = signupBinding.root
        setGenderList()


        signupBinding.signupBtn.setOnClickListener {
            if (signupBinding.signupIndicator.visibility == View.VISIBLE) {
                signupBinding.signupIndicator.visibility = View.GONE
            } else if (signupBinding.signupIndicator.visibility == View.GONE){
                signupBinding.signupIndicator.visibility = View.VISIBLE
            }
        }

        signupBinding.signupMobileEdittext.setOnClickListener {
            selectDate()
        }

        signupBinding.signupSigninBtn.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.login_container, SigninFragment())
            }
        }

        signupBinding.signupBtn.setOnClickListener {
            val callback = object : CoroutineDataPassCallbacks {
                override fun isDataLoading(dataLoading: Boolean) {
                    if (!dataLoading) {
                        signupBinding.signupIndicator.visibility = View.GONE
                    } else {
                        signupBinding.signupIndicator.visibility = View.VISIBLE
                    }
                }

                override fun <T> onLoadComplete(data: T) {
                    val result = data as LoginResponseModel
                    Toast.makeText(requireContext(), result.message + " " + result.success, Toast.LENGTH_SHORT).show()
                    if (result.success) {
                        preferenceManager.setBoolean(Constants.LOGIN_STATUS, result.success)
                        preferenceManager.setString(Constants.USER_ID, result.message)
                        val intent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    }
                }

                override fun onLoadFailed(errorCode: String, errorMessage: String) {
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                }
            }

            loginVM.createUser(
                SignupModel(
                    signupBinding.signupEmailEdittext.text.toString(),
                    signupBinding.signupFirstNameEdittext.text.toString(),
                    signupBinding.signupMobileEdittext.text.toString(),
                    signupBinding.signupGenderAutocompletetv.text.toString(),
                    signupBinding.signupPasswordEdittext.text.toString(),
                ), callback)
        }

        return view
    }

    private fun selectDate() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()
        datePicker.show(parentFragmentManager, "tag");
        datePicker.addOnPositiveButtonClickListener {
            signupBinding.signupMobileEdittext.setText(Date(it*1000).toString())
        }
        datePicker.addOnNegativeButtonClickListener {
            Toast.makeText(requireContext(),
                "Select Date",
                Toast.LENGTH_SHORT).show()
        }
        datePicker.addOnCancelListener {
            Toast.makeText(requireContext(),
                "Select Date",
                Toast.LENGTH_SHORT).show()
        }
        datePicker.addOnDismissListener {
            Toast.makeText(requireContext(),
                "Select Date",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun setGenderList() {
        val items = listOf("Male", "Female", "Trans-Gender")
        val adapter = ArrayAdapter(requireContext(), R.layout.listitem, items)
        (signupBinding.signupGender.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }
}