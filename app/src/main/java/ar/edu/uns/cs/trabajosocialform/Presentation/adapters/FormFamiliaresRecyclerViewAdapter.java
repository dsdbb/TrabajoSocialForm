package ar.edu.uns.cs.trabajosocialform.Presentation.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.concurrent.RecursiveAction;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Familiar;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.RxBus;
import ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers.ContextMenuObserver;
import ar.edu.uns.cs.trabajosocialform.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FormFamiliaresRecyclerViewAdapter extends RecyclerView.Adapter<FormFamiliaresRecyclerViewAdapter.ViewHolder> {

    private List<Familiar> familiares;

    public FormFamiliaresRecyclerViewAdapter(List<Familiar> familiares){
        this.familiares = familiares;
    }

    @Override
    public FormFamiliaresRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_familiar,parent,false);
        return new FormFamiliaresRecyclerViewAdapter.ViewHolder(parent,v);
    }

    @Override
    public void onBindViewHolder(FormFamiliaresRecyclerViewAdapter.ViewHolder holder, int position) {
        Familiar familiar = familiares.get(position);
        holder.label.setText(familiar.getNombres() + " " + familiar.getApellidos());
    }

    @Override
    public int getItemCount() {
        return familiares.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

        @BindView(R.id.label)TextView label;

        public ViewHolder(ViewGroup parent, View v) {
            super(v);
            ButterKnife.bind(this,v);
            v.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            //RxBus.post(new ContextMenuObserver.ContextMenu(contextMenu));
            contextMenu.add(getAdapterPosition(), R.id.edit, 0, "Editar");//groupId, itemId, order, title
            contextMenu.add(getAdapterPosition(), R.id.delete, 0, "Eliminar");
        }
    }
}
