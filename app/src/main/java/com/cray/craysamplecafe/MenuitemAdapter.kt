package com.cray.craysamplecafe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class MenuitemAdapter (
    val context: Context,
    val menuList: ArrayList<menuClass>
) : BaseAdapter () {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val menu = menuList[position];
        val view : View =
            LayoutInflater.from(context)
                .inflate(R.layout.menu_item, null)

        //view.findViewById<ImageView>(R.id.iv_profile)
        //    .setImageResource(menu.menuimg)

        view.findViewById<TextView>(R.id.tv_menu)
            .text = menu.name

        view.findViewById<TextView>(R.id.tv_price)
            .text = "￦" + menu.price.toString()

        view.findViewById<Button>(R.id.btn_add).setOnClickListener {
            // mainActivity.add_order(menu.menuCode)
            (context as MainActivity).add_order(menu.menuCode)
        }

        return view
    }

    override fun getCount(): Int {
        return menuList.size
    }

    override fun getItem(position: Int): Any {
        return menuList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0;
    }

    public fun getMenuName(menuCode: Int) : String
    {
        for (menuClass in menuList)
            if(menuClass.menuCode == menuCode)
                return menuClass.name
        return ""
    }

    public fun getMenuPrice(menuCode: Int) : Int
    {
        for (menuClass in menuList)
            if(menuClass.menuCode == menuCode)
                return menuClass.price
        return 0
    }
}