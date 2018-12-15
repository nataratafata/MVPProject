package firstproject2.mcs.com.mvpproject.data;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import firstproject2.mcs.com.mvpproject.R;


public class dataAdapter extends RecyclerView.Adapter<dataAdapter.ViewHolder> {

        private List<CakesModel> mObjectList;

        public dataAdapter(List<CakesModel> mObjectList) {
            this.mObjectList = mObjectList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_row, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.mTvName.setText(mObjectList.get(position).getName());
            holder.mTvPrice.setText(mObjectList.get(position).getPrice().toString());
            holder.mTvInstru.setText(mObjectList.get(position).getInstructions());
            String url = "http://services.hanselandpetal.com/photos/" + mObjectList.get(position).getPhoto();

            Picasso.get().load(url).resize(50, 50).centerCrop().into(holder.mTvCake);
        }

        @Override
        public int getItemCount() {
            return mObjectList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            private TextView mTvName,mTvPrice,mTvInstru;
            private ImageView mTvCake;
            public ViewHolder(View view) {
                super(view);

                mTvName = view.findViewById(R.id.tv_name);
                mTvInstru = view.findViewById(R.id.tv_instructions);
                mTvPrice = view.findViewById(R.id.tv_price);
                mTvCake = view.findViewById(R.id.flower_Img);
            }
        }
    }

