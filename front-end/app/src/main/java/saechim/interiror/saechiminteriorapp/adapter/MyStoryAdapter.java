package saechim.interiror.saechiminteriorapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import saechim.interiror.saechiminteriorapp.R;
import saechim.interiror.saechiminteriorapp.dto.ResponseMyPostDto;

public class MyStoryAdapter extends RecyclerView.Adapter<MyStoryAdapter.ItemViewHolder>{
    private ArrayList<ResponseMyPostDto> listData=new ArrayList<>();


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.mystory_listitem,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            //TODO
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void addItem(ResponseMyPostDto model){
        listData.add(model);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        private View mview;
        private TextView idText;
        private TextView userIdText;
        private TextView typeText;
        private TextView titleText;
        private TextView contentText;
        private TextView postViewText;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mview=itemView;
            idText=itemView.findViewById(R.id.postidText);
            userIdText=itemView.findViewById(R.id.userIdText);
            typeText=itemView.findViewById(R.id.TypeText);
            titleText=itemView.findViewById(R.id.TitleText);
            contentText=itemView.findViewById(R.id.textContent);
            postViewText=itemView.findViewById(R.id.PostView);
        }
        void onBind(ResponseMyPostDto data){
            idText.setText(String.valueOf(data.getId()));
            userIdText.setText(data.getUserId());
            typeText.setText(data.getType());
            titleText.setText(data.getTitle());
            contentText.setText(data.getContent());
            postViewText.setText(String.valueOf(data.getPostView()));
        }
    }
}
