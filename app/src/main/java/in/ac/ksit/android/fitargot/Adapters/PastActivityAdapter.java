package in.ac.ksit.android.fitargot.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import in.ac.ksit.android.fitargot.Network.Model.EventResult;
import in.ac.ksit.android.fitargot.Network.Model.PastEventModel;
import in.ac.ksit.android.fitargot.R;

public class PastActivityAdapter extends RecyclerView.Adapter<PastActivityAdapter.PastCard> {

    Context context;
    List<EventResult> data;
    public PastActivityAdapter(Context context, List<EventResult> models){

            this.context=context;
            this.data=models;
    }

    @NonNull
    @Override
    public PastCard onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.game_item,parent,false);

        return new PastCard(itemView);

    }

    public void setData(List<EventResult> models){
        this.data=models;
        this.notifyDataSetChanged();


    }

    @Override
    public void onBindViewHolder(@NonNull PastCard pastCard, int i) {

        pastCard.setValues(data.get(i));
    }

    @Override
    public int getItemCount() {
        if(data==null)
            return 0;
        else
            return data.size();
    }

    static class PastCard extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView sport;
        TextView address;
        Spinner spinner;
        Button resue;
        public PastCard(@NonNull View itemView) {

            super(itemView);
            sport=itemView.findViewById(R.id.sports_type);
            address=itemView.findViewById(R.id.address);
            spinner=itemView.findViewById(R.id.level);
            resue=itemView.findViewById(R.id.reuse);
            resue.setOnClickListener(this);

        }


        void setValues(EventResult model){
           sport.setText("Foot ball");
           address.setText("Trichy,India");


        }

        @Override
        public void onClick(View view) {

//            call network
        }
    }
}
