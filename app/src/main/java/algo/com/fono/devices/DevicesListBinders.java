package algo.com.fono.devices;

import java.util.List;

import algo.com.fono.data.Device;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class DevicesListBinders {

    @SuppressWarnings("unchecked")
    @BindingAdapter("app:items")
    public static void setItems(RecyclerView recyclerView, List<Device> items){
        DevicesAdapter adapter = (DevicesAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setDataSet(items);
        }
    }

}
