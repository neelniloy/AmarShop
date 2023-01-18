package com.braineer.amarshopbyseo

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.braineer.amarshopbyseo.databinding.FragmentLoginBinding
import com.braineer.amarshopbyseo.entities.UserModel
import com.braineer.amarshopbyseo.prefdata.LoginPreference
import com.braineer.amarshopbyseo.viewmodels.LoginViewModel
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var preference: LoginPreference
    private val loginViewModel: LoginViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar!!.show()

        preference = LoginPreference(requireContext())
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        loginViewModel.errMsgLD.observe(viewLifecycleOwner) {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        }


        val sharedPreference =  requireActivity().getSharedPreferences("CheckLogin", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()

        val saveLogin = sharedPreference.getBoolean("saveLogin",false)


        if (saveLogin) {
            binding.etEmail.setText(sharedPreference.getString("user_email", ""))
            binding.etPass.setText(sharedPreference.getString("user_password", ""))
            binding.saveLoginCheckBox.isChecked = true
        }


        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPass.text.toString()

            if (binding.saveLoginCheckBox.isChecked()) {
                editor.putBoolean("saveLogin", true)
                editor.putString("user_email", email)
                editor.putString("user_password", password)
                editor.apply()
            } else {
                editor.clear()
                editor.commit()
            }

            if (email.isEmpty() || password.isEmpty()) {
                if (email.isEmpty()) {
                    binding.editTextEmailLayout.setError("*Email required")
                    binding.etEmail.requestFocus()
                } else {
                    binding.editTextEmailLayout.setErrorEnabled(false)
                }
                if (password.isEmpty()) {
                    binding.editTextPassLayout.setError("*Password required")
                    binding.etPass.requestFocus()
                } else {
                    binding.editTextPassLayout.setErrorEnabled(false)
                }
            } else if (!(email.isEmpty() && password.isEmpty())) {

                binding.editTextEmailLayout.setErrorEnabled(false)
                binding.editTextPassLayout.setErrorEnabled(false)

                val progressDialog = ProgressDialog(requireActivity())
                progressDialog.setMessage("Logging in...")
                progressDialog.setCanceledOnTouchOutside(false)
                progressDialog.show()

                val progressbar = progressDialog!!.findViewById(android.R.id.progress) as ProgressBar
                progressbar.indeterminateDrawable.setColorFilter(Color.parseColor("#00B157"), android.graphics.PorterDuff.Mode.SRC_IN)


                loginViewModel.login(email, password) {
                    lifecycle.coroutineScope.launch {
                        preference.setLoginStatus(true, it, requireContext())
                        findNavController().popBackStack()
                    }
                }

            }


        }
        binding.btnReg.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPass.text.toString()

            if (binding.saveLoginCheckBox.isChecked()) {
                editor.putBoolean("saveLogin", true)
                editor.putString("user_email", email)
                editor.putString("user_password", password)
                editor.apply()
            } else {
                editor.clear()
                editor.commit()
            }

            if (email.isEmpty() || password.isEmpty()) {
                if (email.isEmpty()) {
                    binding.editTextEmailLayout.error = "*Email required"
                    binding.etEmail.requestFocus()
                } else {
                    binding.editTextEmailLayout.isErrorEnabled = false
                }
                if (password.isEmpty()) {
                    binding.editTextPassLayout.error = "*Password required"
                    binding.etPass.requestFocus()
                } else {
                    binding.editTextPassLayout.isErrorEnabled = false
                }
            } else if (!(email.isEmpty() && password.isEmpty())) {

                binding.editTextEmailLayout.isErrorEnabled = false
                binding.editTextPassLayout.isErrorEnabled = false

                val progressDialog = ProgressDialog(requireActivity())
                progressDialog.setMessage("Registering...")
                progressDialog.setCanceledOnTouchOutside(false)
                progressDialog.show()

                val progressbar =
                    progressDialog.findViewById(android.R.id.progress) as ProgressBar
                progressbar.indeterminateDrawable.setColorFilter(
                    Color.parseColor("#00B157"),
                    android.graphics.PorterDuff.Mode.SRC_IN
                )


                val user = UserModel(
                    email = email, password = password
                )
                loginViewModel.register(user) {
                    lifecycle.coroutineScope.launch {
                        preference.setLoginStatus(true, it, requireContext())
                        findNavController().popBackStack()
                    }
                }
            }
        }

        binding.tvForgotpass.setOnClickListener {
            Toast.makeText(requireActivity(), "Feature will be added soon", Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }
}