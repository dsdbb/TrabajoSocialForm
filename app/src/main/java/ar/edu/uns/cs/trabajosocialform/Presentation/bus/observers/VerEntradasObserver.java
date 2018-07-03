package ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers;

public abstract class VerEntradasObserver extends BusObserver<VerEntradasObserver.VerEntradas> {
    public VerEntradasObserver(){ super(VerEntradas.class); }

    public static class VerEntradas{

    }
}
