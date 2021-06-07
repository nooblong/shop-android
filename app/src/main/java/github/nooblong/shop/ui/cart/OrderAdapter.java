package github.nooblong.shop.ui.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import github.nooblong.shop.R;
import github.nooblong.shop.entity.Order;
import github.nooblong.shop.entity.OrderDetail;
import github.nooblong.shop.ui.order.OrderFragment;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    List<OrderDetail> orderDetails;

    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemLayout = layoutInflater.inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(itemLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderDetail orderDetail = orderDetails.get(position);
        holder.orderName.setText("名字"+orderDetail.getName());
        holder.orderAddress.setText("地址"+orderDetail.getAddress());
        holder.orderPrice.setText("价格"+String.valueOf(orderDetail.getPrice()));
        holder.orderRemark.setText("备注"+orderDetail.getRemark());
        holder.orderTele.setText("手机号"+orderDetail.getTele());
        holder.orderTime.setText("时间"+String.valueOf(orderDetail.getTime().getTime()));
        Map<Integer, Integer> goods = orderDetail.getGood();
        Map<String, Integer> products = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : goods.entrySet()) {
            String name = OrderFragment.Util.getName(entry.getKey());
            products.put(name, entry.getValue());
        }
        holder.orderProducts.setText(products.toString());
//        holder.orderProducts.setText(orderDetail.getGood().toString());
        String state = "";
        if (orderDetail.getState() == 0)
            state = "待配送";
        if (orderDetail.getState() == 1)
            state = "配送中";
        if (orderDetail.getState() == 2)
            state = "配送完成";
        holder.orderState.setText("状态"+state);
    }

    @Override
    public int getItemCount() {
        if (orderDetails == null)
            return 0;
        return orderDetails.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder{

        TextView orderName, orderTele, orderRemark, orderAddress, orderTime, orderPrice, orderState, orderProducts;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderName = itemView.findViewById(R.id.orderName);
            orderTele = itemView.findViewById(R.id.orderTele);
            orderRemark = itemView.findViewById(R.id.orderRemark);
            orderAddress = itemView.findViewById(R.id.orderAddress);
            orderTime = itemView.findViewById(R.id.orderTime);
            orderPrice = itemView.findViewById(R.id.orderPrice);
            orderState = itemView.findViewById(R.id.orderState);
            orderProducts = itemView.findViewById(R.id.orderProducts);
        }
    }
}
