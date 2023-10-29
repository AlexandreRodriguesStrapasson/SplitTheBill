package br.edu.scl.ifsp.ads.contatospdm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.edu.scl.ifsp.ads.contatospdm.databinding.ActivityContactBinding
import br.edu.scl.ifsp.ads.contatospdm.model.Constant.EXTRA_CONTACT
import br.edu.scl.ifsp.ads.contatospdm.model.Constant.VIEW_CONTACT
import br.edu.scl.ifsp.ads.contatospdm.model.Contact

class ContactActivity : AppCompatActivity() {
    private val acb: ActivityContactBinding by lazy {
        ActivityContactBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(acb.root)

        setSupportActionBar(acb.toolbarIn.toolbar)
        supportActionBar?.subtitle = "Contact details"

        val receivedContact = intent.getParcelableExtra<Contact>(EXTRA_CONTACT)
        receivedContact?.let{_receivedContact ->
            val viewContact = intent.getBooleanExtra(VIEW_CONTACT, false)
            with(acb) {
                if (viewContact) {
                    nameEt.isEnabled = false
                    amountToPaidEt.isEnabled = false
                    receiveEt.isEnabled = false
                    paidEt.isEnabled = false
                    saveBt.visibility = View.GONE
                }
                nameEt.setText(_receivedContact.name)
                amountToPaidEt.setText(_receivedContact.amountToPaid)
            }
        }

        with(acb) {
            saveBt.setOnClickListener {
                val contact = Contact(
                    id = receivedContact?.id,
                    name = nameEt.text.toString(),
                    amountToPaid = amountToPaidEt.text.toString(),
                    receive = receiveEt.text.toString(),
                    pays = paidEt.text.toString()
                )

                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_CONTACT, contact)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}