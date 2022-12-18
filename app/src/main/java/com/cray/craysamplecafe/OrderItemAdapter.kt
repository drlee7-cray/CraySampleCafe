package com.cray.craysamplecafe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class OrderItemAdapter (
    val context: Context,
    val orderList: ArrayList<OrderClass>
) : BaseAdapter(){

    lateinit var mainActivity: MainActivity

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val order = orderList[position] as OrderClass
        val view :View =
            LayoutInflater.from(context)
                .inflate(R.layout.order_item, null)

        mainActivity = context as MainActivity

        val menuName = mainActivity.menuitemAdapter.getMenuName(order.menuCode)
        var menuPrice = mainActivity.menuitemAdapter.getMenuPrice(order.menuCode)

        view.findViewById<TextView>(R.id.tv_order_name)
            .text = menuName + " x " + order.count.toString()+ " 개"

        view.findViewById<TextView>(R.id.tv_order_count)
            .text = "￦" + (menuPrice * order.count).toString()

        // 갯수 증가 버튼
        view.findViewById<ImageView>(R.id.btn_order_up)
            .setOnClickListener {
                order.count++
                this.notifyDataSetChanged()
                mainActivity.refresh_total()
            }

        // 갯수 감소 버튼
        view.findViewById<ImageView>(R.id.btn_order_down)
            .setOnClickListener {
                order.count--
                if(order.count==0)orderList.removeAt(position)
                this.notifyDataSetChanged()
                mainActivity.refresh_total()
            }

        // 휴지통 버튼
        view.findViewById<ImageView>(R.id.btn_order_delete)
            .setOnClickListener {
                orderList.removeAt(position)
                this.notifyDataSetChanged()
                mainActivity.refresh_total()
            }

        return view
    }

    override fun getCount(): Int {
        return orderList.size
    }

    override fun getItem(position: Int): Any {
        return orderList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

}