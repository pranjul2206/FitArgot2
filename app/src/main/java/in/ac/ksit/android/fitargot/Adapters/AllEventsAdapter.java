package in.ac.ksit.android.fitargot.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import in.ac.ksit.android.fitargot.Network.Model.EventResult;
import in.ac.ksit.android.fitargot.R;

public class AllEventsAdapter extends RecyclerView.Adapter<AllEventsAdapter.GameItem> {

    Context context;
    List<EventResult> models;
    public AllEventsAdapter(Context context, List<EventResult> models){
        this.context=context;
        this.models=models;
    }
    @NonNull
    @Override
    public GameItem onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.game_item,parent,false);

        return new GameItem(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GameItem gameItem, int i) {

        gameItem.setValues(models.get(i));

    }

    public void setData(List<EventResult> models){
        this.models=models;
        this.notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        if(models==null)
            return 0;
        else
            return models.size();

    }

    public class GameItem extends RecyclerView.ViewHolder implements View.OnClickListener{

         TextView game_name;
         TextView game_address;
         Button btn;


         public GameItem(@NonNull View itemView) {
             super(itemView);
             btn=itemView.findViewById(R.id.reuse);
             btn.setText("Join");
             game_name=itemView.findViewById(R.id.sports_type);
             game_address=itemView.findViewById(R.id.address);
         }

         public void setValues(EventResult model)
         {

            game_name.setText(model.getName());
            game_address.setText(model.getAddress());

         }

        @Override
        public void onClick(View view) {
//                network call
        }
    }


}
