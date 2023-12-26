package com.example.vaccination_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.MyViewHolder> implements Filterable {

    private ArrayList<Hospital> hospitaList;
    private OnNoteListener mOnNoteListener;
    private ArrayList<Hospital> hospitaListFull;

    public HospitalAdapter(ArrayList<Hospital> hospitaList, OnNoteListener onNoteListener) {
        this.hospitaList = hospitaList;
        this.mOnNoteListener = onNoteListener;
        this.hospitaListFull= new ArrayList<>(hospitaList);

    }


    @NonNull
    @Override
    public HospitalAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_row, parent, false);
        return new MyViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder( HospitalAdapter.MyViewHolder holder, int position) {
        String HospitalName = hospitaList.get(position).getHospitalname();
        String HospitalDescription = hospitaList.get(position).getHospitaldesc();
        int HospitalImage = hospitaList.get(position).getImage();

        holder.textTitle.setText(HospitalName);
        holder.textDescription.setText(HospitalDescription);
        holder.imgLogo.setImageResource(HospitalImage);
    }

    @Override
    public int getItemCount() {
        return hospitaList.size();
    }

    @Override
    public Filter getFilter() {
        return hospitalFilter;
    }

    private Filter hospitalFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Hospital> filteredList = new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(hospitaListFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (Hospital hospital : hospitaListFull) {
                    if (hospital.getHospitaldesc().toLowerCase().contains(filterPattern)) {
                        filteredList.add(hospital);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            hospitaList.clear();
            hospitaList.addAll((ArrayList) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textTitle, textDescription;
        ImageView imgLogo;
        OnNoteListener onNoteListener;

        public MyViewHolder(View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            this.textTitle = itemView.findViewById(R.id.txtLanguage);
            this.textDescription = itemView.findViewById(R.id.txtDesc);
            this.imgLogo = itemView.findViewById(R.id.imageLogo);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
