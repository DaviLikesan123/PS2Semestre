package br.com.fiap.mobilefiap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import br.com.fiap.mobilefiap.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = findViewById<EditText>(R.id.email)
        val senha = findViewById<EditText>(R.id.senha)


        //Sendo to register Activity
        binding.register.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.registerButton.setOnClickListener {
            when {
                TextUtils.isEmpty(email.text.toString().trim() { it <= ' ' }) -> {
                    Toast.makeText(
                        this,
                        "Enter the email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(senha.text.toString().trim() { it <= ' ' }) -> {
                    Toast.makeText(
                        this,
                        "Enter the password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val email : String = email.text.toString().trim() { it <= ' ' }
                    val senha: String = senha.text.toString().trim() { it <= ' ' }

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful){

                            val firebaseUser: FirebaseUser = task.result!!.user!!

                            Toast.makeText(
                                this,
                                "You are registered Sucessfully",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent = Intent(this, MainActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id", firebaseUser.uid)
                            intent.putExtra("email_id", email)
                            startActivity(intent)
                            finish()

                            } else {
                              Toast.makeText(
                                  this,
                                  task.exception!!.message.toString(),
                                  Toast.LENGTH_SHORT
                              ).show()
                            }
                        }
                }
            }

        }
    }
}
