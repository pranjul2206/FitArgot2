package in.ac.ksit.android.fitargot.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in.ac.ksit.android.fitargot.Network.Model.DietData;
import in.ac.ksit.android.fitargot.R;

public class HorAdapter extends RecyclerView.Adapter<HorAdapter.Holder> {
    List<DietData> data;
    public HorAdapter(List<DietData> data){
        this.data=data;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.diet_list,viewGroup,false);

        return new Holder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.setValues(data.get(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class Holder extends RecyclerView.ViewHolder{

    TextView food1;
    TextView header;
    TextView food2;

    public Holder(@NonNull View itemView) {
        super(itemView);
        header=itemView.findViewById(R.id.header);
        food1=itemView.findViewById(R.id.option1);
        food2=itemView.findViewById(R.id.option2);
    }

    public void setValues(DietData data){
        header.setText(data.header);
        food1.setText(data.food1);
        food2.setText(data.food2);
    }
}
}
