package com.cray.craysamplecafe

import android.app.ActionBar.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.get
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val lv_menulist: ListView by lazy { findViewById(R.id.lv_menulist) }
    val lv_orderList : ListView by lazy { findViewById(R.id.lv_orderlist) }
    val tv_total : TextView by lazy { findViewById(R.id.tv_total) }
    var menuList = arrayListOf<menuClass>(
        menuClass(
            10001,
            R.id.iv_profile,
            "아메리카노",
            1000
        ),
        menuClass(
            10002,
            R.id.iv_profile,
            "카페라떼",
            1500
        ),
        menuClass(
            10003,
            R.id.iv_profile,
            "카푸치노",
            2000
        ),
        menuClass(
            10004,
            R.id.iv_profile,
            "카라멜 마키아또",
            2500
        ),
        menuClass(
            10005,
            R.id.iv_profile,
            "홍차",
            2500
        ),
        menuClass(
            10006,
            R.id.iv_profile,
            "쌍화차",
            3000
        ),
        menuClass(
            10007,
            R.id.iv_profile,
            "유자차",
            3000
        ),
    )
    var orderList = arrayListOf<OrderClass>()
    lateinit var orderItemAdapter: OrderItemAdapter
    lateinit var menuitemAdapter: MenuitemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menuitemAdapter = MenuitemAdapter(this, menuList)
        lv_menulist.adapter = menuitemAdapter

        lv_menulist.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectOne = parent.getItemIdAtPosition(position) as menuClass
            }

        orderItemAdapter = OrderItemAdapter(this, orderList)
        lv_orderList.adapter = orderItemAdapter

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putSerializable("orderList", orderList)
    }

    // 주문 추가
    public fun add_order(menuCode: Int) {

        // 이미 추가한 주문이면
        for(orderOne in orderList)
        {
            if(orderOne.menuCode==menuCode)
            {
                // 개수만 증가
                orderOne.count ++;

                refresh_total()
                return;
            }
        }

        // 주문 추가
        orderList.add(OrderClass(menuCode, 1))
        orderItemAdapter.notifyDataSetChanged()
        refresh_total()
    }

    // 합계 갱신
    public fun refresh_total()
    {
        var totalPrice: Int = 0

        for(orderOne in orderList)
            totalPrice += menuitemAdapter.getMenuPrice(orderOne.menuCode) * orderOne.count

        tv_total.text = "합계 : " + NumberFormat.getCurrencyInstance(Locale.KOREA).format(totalPrice) + "원"

    }


}