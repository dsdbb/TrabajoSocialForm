package ar.edu.uns.cs.trabajosocialform.Presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Familiar;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.EntradasItemClickedObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.FamiliarDetallesItemClickedObserver;
import ar.edu.uns.cs.trabajosocialform.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FamiliaresDetallesRecyclerViewAdapter extends RecyclerView.Adapter<FamiliaresDetallesRecyclerViewAdapter.ViewHolder> {

    private List<Familiar> familiares;

    public FamiliaresDetallesRecyclerViewAdapter(List<Familiar> familiares){
        this.familiares = familiares;
    }

    @Override
    public FamiliaresDetallesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_familiar,parent,false);
        return new ViewHolder(parent,v);
    }

    @Override
    public void onBindViewHolder(FamiliaresDetallesRecyclerViewAdapter.ViewHolder holder, int position) {
        Familiar familiar = familiares.get(position);
        holder.setText(familiar.getNombres() + " " + familiar.getApellidos());
        holder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return familiares.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.label)TextView textView;
        private int position;

        public ViewHolder(ViewGroup parent, View v) {
            super(v);
            ButterKnife.bind(this,v);
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public TextView getTextView(){
            return textView;
        }

        public void setText(String text){
            textView.setText(text);
        }

        @OnClick
        public void clicked(){
            RxBus.post(new FamiliarDetallesItemClickedObserver.FamiliarDetallesItemClicked(position));
        }
    }
}
