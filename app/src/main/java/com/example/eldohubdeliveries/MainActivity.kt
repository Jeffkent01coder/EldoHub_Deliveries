package com.example.eldohubdeliveries

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eldohubdeliveries.databinding.ActivityMainBinding
import com.example.eldohubdeliveries.databinding.EldoAddDialogBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.InternalCoroutinesApi

class MainActivity : AppCompatActivity(), EldoRVAdapter.EldoItemClickInterface {

    private lateinit var binding: ActivityMainBinding

    private lateinit var itemsRV: RecyclerView
//    lateinit var addFAB: FloatingActionButton
    lateinit var list: List<Eldoitems>
    lateinit var eldoRVAdapter: EldoRVAdapter
    lateinit var eldoViewModel: EldoViewModel

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        itemsRV = findViewById(R.id.idRVItems)
//        addFAB = findViewById(R.id.idFABAdd)
        list = ArrayList<Eldoitems>()
        eldoRVAdapter = EldoRVAdapter(list, this)
        itemsRV  = binding.idRVItems
//        itemsRV.layoutManager = LinearLayoutManager(this)
        itemsRV.adapter = eldoRVAdapter
        val eldoRepository = EldoRepository(EldoDatabase(this))
        val factory = EldoViewModelFactory(eldoRepository)
        eldoViewModel = ViewModelProvider(this,factory).get(EldoViewModel::class.java)
        eldoViewModel.getAllEldoItems().observe(this,{
            eldoRVAdapter.list = it
            eldoRVAdapter.notifyDataSetChanged()
        })

        binding.idFABAdd.setOnClickListener {
            openDialog()
        }
    }

    fun openDialog(){
        val dialogBinding = EldoAddDialogBinding.inflate(layoutInflater)
        val dialog  = Dialog(this)
        dialog.setContentView(dialogBinding.root)


        val cancelBtn = dialog.findViewById<Button>(R.id.idBtnCancel)
        val addBtn  = dialog.findViewById<Button>(R.id.idBtnAdd) as Button
        val itemEdit = dialog.findViewById<EditText>(R.id.idEditItemName)
        val itemPriceEdit = dialog.findViewById<EditText>(R.id.idEditItemPrice)
        val itemQuantityEdit = dialog.findViewById<EditText>(R.id.idEditItemQuantity)


        addBtn.setOnClickListener {
            val itemName : String = dialogBinding.idEditItemName.text.toString().trim()
            val itemPrice : String = dialogBinding.idEditItemPrice.text.toString().trim()
            val itemQuantity : String = dialogBinding.idEditItemQuantity.text.toString().trim()
            val qty :Int = itemQuantity.toInt()
            val pr : Int = itemPrice.toInt()
            if (itemName.isNotEmpty() && itemPrice.isNotEmpty() && itemQuantity.isNotEmpty()){
                val items = Eldoitems(itemName,qty,pr)
                eldoViewModel.insert(items)
                Toast.makeText(applicationContext,"Item Inserted ", Toast.LENGTH_SHORT).show()
                eldoRVAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }else{
                Toast.makeText(applicationContext,"Please Enter all the data ", Toast.LENGTH_SHORT).show()

            }
            Toast.makeText(this, "Add btn clicked", Toast.LENGTH_SHORT).show()
        }

       dialogBinding.idBtnCancel.setOnClickListener {
            dialog.dismiss()
        }
        binding.idFABAdd.setOnClickListener {


        }
        dialog.show()

    }

    override fun onItemClick(eldoitems: Eldoitems) {
        eldoViewModel.delete(eldoitems)
        eldoRVAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext,"Item Deleted",Toast.LENGTH_SHORT).show()

    }
}
