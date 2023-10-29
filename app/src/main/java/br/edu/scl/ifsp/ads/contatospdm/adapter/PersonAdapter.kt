package br.edu.scl.ifsp.ads.contatospdm.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.scl.ifsp.ads.contatospdm.R
import br.edu.scl.ifsp.ads.contatospdm.databinding.TilePersonBinding
import br.edu.scl.ifsp.ads.contatospdm.modal.Person

class PersonAdapter(context:Context, private val personList: MutableList<Person>):
    ArrayAdapter<Person>(context, R.layout.tile_person, personList) {
        private lateinit var tpb: TilePersonBinding
        override fun getView(position: Int, convertView: View?, parent: android.view.ViewGroup): View {
            val person: Person = personList[position]
            var tilePersonView = convertView
            if (tilePersonView == null) {
                tpb = TilePersonBinding.inflate(
                    context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                    parent, false
                )
                tilePersonView = tpb.root

                //criando um holder e guardando as referencias dos componentes
                val tpvh = TilePersonViewHolder(
                    tilePersonView.findViewById(R.id.nameTv),
                    tilePersonView.findViewById(R.id.valueTv)
                )
                //armazenando viewHolder na celula
                tilePersonView.tag = tpvh
            }
            //substituir os valores
            with(tilePersonView.tag as TilePersonViewHolder){
                nameTv.text = person.name
                valueTv.text = person.value.toString()
            }
            return tilePersonView
        }
        private data class TilePersonViewHolder(
            val nameTv: TextView,
            val valueTv: TextView,
        )

    }