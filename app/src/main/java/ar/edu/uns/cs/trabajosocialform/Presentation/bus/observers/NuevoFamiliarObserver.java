package ar.edu.uns.cs.trabajosocialform.Presentation.bus.observers;

public abstract class NuevoFamiliarObserver extends BusObserver<NuevoFamiliarObserver.NuevoFamiliar> {
    public NuevoFamiliarObserver(){ super(NuevoFamiliar.class); }

    public static class NuevoFamiliar{
    }
}
