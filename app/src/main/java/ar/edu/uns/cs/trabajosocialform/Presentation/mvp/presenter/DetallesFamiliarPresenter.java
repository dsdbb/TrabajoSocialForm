package ar.edu.uns.cs.trabajosocialform.Presentation.mvp.presenter;

import ar.edu.uns.cs.trabajosocialform.Data.DataModel.Familiar;
import ar.edu.uns.cs.trabajosocialform.Presentation.mvp.view.DetallesFamiliarView;

public class DetallesFamiliarPresenter {

    private final Familiar familiar;
    private DetallesFamiliarView view;

    public DetallesFamiliarPresenter(DetallesFamiliarView view, Familiar familiar){
        this.view = view;
        this.familiar = familiar;

        view.inicializarGui(familiar);
    }
}
