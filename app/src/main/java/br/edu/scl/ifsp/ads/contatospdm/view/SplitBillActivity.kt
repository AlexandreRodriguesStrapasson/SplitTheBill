package br.edu.scl.ifsp.ads.contatospdm.view

import android.os.Build
import android.os.Bundle
import br.edu.scl.ifsp.ads.contatospdm.adapter.PersonAdapter
import br.edu.scl.ifsp.ads.contatospdm.databinding.ActivitySplitBillBinding
import br.edu.scl.ifsp.ads.contatospdm.modal.Person

class SplitBillActivity : BaseActivity() {
    private val personList: MutableList<Person> = mutableListOf()

    private val asbb: ActivitySplitBillBinding by lazy {
        ActivitySplitBillBinding.inflate(layoutInflater)
    }

    private val personAdpter: PersonAdapter by lazy {
        PersonAdapter(this, personList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(asbb.root)
        supportActionBar?.subtitle = "Divisão da conta"

        val receivedPerson = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableArrayListExtra(EXTRA_SPLIT, Person::class.java)
        } else {
            intent.getParcelableArrayListExtra(EXTRA_SPLIT)
        }

        receivedPerson?.let {
            updatePersonList(it, calculateSplitBill(it))
            asbb.personsLv.adapter = personAdpter
        }
    }

    fun updatePersonList(_personList: ArrayList<Person>, value: Float) {
        personList.clear()
        for(person in _personList){
            person.value = person.value - value
            if(person.value > 0){
                person.name = person.name.plus(" deve receber: ")
            }else if (person.value < 0){
                person.name = person.name.plus(" Deve pagar: ")
            }else{
                person.name = person.name.plus(" OK! ")
            }
            person.value = Math.abs(person.value)
        }

        personList.addAll(_personList)
        personAdpter.notifyDataSetChanged()
    }

    private fun calculateSplitBill(personList: ArrayList<Person>): Float {
        val people = personList
        val qtd = people.size
        var total = 0F

        for (person in people) {
            total += person.value
        }
        total /= qtd

        return total
    }
}