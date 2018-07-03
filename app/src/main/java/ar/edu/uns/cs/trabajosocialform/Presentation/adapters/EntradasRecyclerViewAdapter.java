package ar.edu.uns.cs.trabajosocialform.Presentation.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Solicitante;
import ar.edu.uns.cs.trabajosocialform.Data.Database.DatabaseAcces;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.ContextMenuObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.EntradasItemClickedObserver;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.EntradasView;
import ar.edu.uns.cs.trabajosocialform.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EntradasRecyclerViewAdapter extends RecyclerView.Adapter<EntradasRecyclerViewAdapter.ViewHolder>{

    private List<Solicitante> solicitantes;

    public EntradasRecyclerViewAdapter(List<Solicitante> solicitantes){
        this.solicitantes = solicitantes;
    }

    @NonNull
    @Override
    public EntradasRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(parent,v);
    }

    @Override
    public void onBindViewHolder(@NonNull EntradasRecyclerViewAdapter.ViewHolder holder, int position) {
        Solicitante solicitante = solicitantes.get(position);
        holder.setText(solicitante.getNombres()+" "+solicitante.getApellidos());
        holder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return solicitantes.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        @BindView(R.id.label) TextView textView;
        private int position;

        public ViewHolder(ViewGroup parent, View v) {
            super(v);
            ButterKnife.bind(this,v);
            v.setOnCreateContextMenuListener(this);
        }

        public void setPosition(int position){
            this.position = position;
        }

        public void setText(String text){
            textView.setText(text);
        }

        public TextView getTextView(){
            return textView;
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            Log.i("View id",view.getId()+"");
            RxBus.post(new ContextMenuObserver.ContextMenu(contextMenu));
        }

        @OnClick
        public void clicked(){
            RxBus.post(new EntradasItemClickedObserver.EntradasItemClicked(position));
        }

    }


}
