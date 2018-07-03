package ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers;

public abstract class NuevaEntradaObserver extends BusObserver<NuevaEntradaObserver.NuevaEntrada> {
    public NuevaEntradaObserver(){ super(NuevaEntrada.class); }

    public static class NuevaEntrada{

    }
}
